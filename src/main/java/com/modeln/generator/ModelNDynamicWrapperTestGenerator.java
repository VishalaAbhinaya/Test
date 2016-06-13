package com.modeln.generator;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.soap.*;
import javax.xml.ws.soap.SOAPFaultException;
/**
 * File that generates test dynamically based on ACT that are in the directory and the base template for better integration with jenkins reporting and
 * maveen. The build in Jenkins has the following steps.
 * <p/>
 * generate the test files to leverage jenkins test reporting structure
 * mvn exec:java -Dexec.mainClass="com.modeln.generator.ModelNDynamicTestGenerator"
 * <p/>
 * Execute test using maveen
 * mvn test
 */


public class ModelNDynamicWrapperTestGenerator {

    /**
     * mvn exec:java -Dexec.mainClass="com.modeln.generator.ModelNDynamicTestGenerator" -Dexec.args=""
     * <p/>
     * class level template  {ClassName} (Needs to end with Test) {TestMethods}
     * method level template  {TestMethodName} (Needs to start with test) {actPath}
     */
    public static void main(String[] args) throws Exception {
        String workingDir = System.getProperty("user.dir");

        String testDir = workingDir + "/src/test/java/modeln";
        delete(new File(testDir));
        createFolderIfNeeded(workingDir + "/src/test/java/modeln");

        String actRoot = System.getenv("SQIN_ACT_ROOT");
        String scriptPath = System.getenv("SQIN_SCRIPT_PATH");
        String stopOnAssertions = System.getenv("SQIN_STOP_ON_ASSERTIONS");


        String path = actRoot + "/" + scriptPath + "/";

        //Script file to be loaded while executing SQIN test
        String lkpLibraryName = scriptPath;
        if (lkpLibraryName == null) {
            lkpLibraryName = "NA";
        }

        String logLevel = System.getenv("LOGGING_LEVEL");
        String testTimeout = System.getenv("SQIN_TEST_TIMEOUT");
        String runFilter = System.getenv("RUN_FILTER");
        String paramsModules = System.getenv("SQIN_PARAMS_MODULES");
        String dataStrategyRefreshMode = System.getenv("SQIN_DATASTRATEGY_REFRESH_MODE");
        if (dataStrategyRefreshMode == null) {
            dataStrategyRefreshMode = "ALL";
        }

        //Variables used for BATAM reporting integration
        String reportingEnabled = System.getenv("SQIN_BATAM_REPORTING_ENABLED");
        String reportName = System.getenv("JOB_NAME");
        String buildName = System.getenv("SQIN_BUILD_NAME");
        String batamClientHost = System.getenv("SQIN_CLIENT_HOST");

        String buildNo = System.getenv("SQIN_BUILD_NUMBER");
        String buildUniqueId = batamClientHost.concat("_" + buildName).concat("_" + buildNo);
        String reportUniqueId = reportName.concat("_" + batamClientHost).concat("_" + buildNo);

        //if no build in course, reportingEnabled will be forced to false
        if (buildName == null) {
            reportingEnabled = "false";
            buildUniqueId = "";
            buildName = "";
        }

        if (logLevel == null) {
            logLevel = "NONE";
        }

        HashMap<String, String> scriptNames = new HashMap<String, String>();
        String tags = "";
        if (runFilter != null) {
            String[] stringParameter = runFilter.split(";");
            for (int i = 0; i < stringParameter.length; i++) {
                String[] keyValue = stringParameter[i].split("=");
                scriptNames.put(keyValue[0], keyValue[1]);
            }

            if (scriptNames.containsKey("TAG")) {
                tags = ((String) scriptNames.get("TAG"));
            }
        }

        String singleTestName = null;
        if (args.length > 0) {
            singleTestName = args[0];
            System.out.println(" singleTestName " + singleTestName);
        }
        String fileName;

        String[] fileExtensionToInclude = {".act", ".xml"};

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        //Sort the test files to gaurantee alphabetical order
        java.util.Arrays.sort(listOfFiles);

        String testBatchSize = System.getenv("SQIN_TEST_BATCH_SIZE");
        int batchSize = (testBatchSize != null ? java.lang.Integer.parseInt(testBatchSize) : 10);
        int start = 0;
        int end = batchSize;
        int j = 1;

        while (true) {
            String baseClassTemplate = readFile(workingDir + "/src/main/java/com/modeln/generator/ClassTestTemplate.tmpl");
            String baseMethodTemplate = readFile(workingDir + "/src/main/java/com/modeln/generator/WrapperMethodTestTemplate.tmpl");
            String testName = "CMnSQIN_" + j + "_Test";
            System.out.println(" Test Name is : " + testName);
            baseClassTemplate = baseClassTemplate.replace("{ClassName}", testName);
            StringBuilder methods = new StringBuilder();
            int i = start;

            while (i < listOfFiles.length && i < end) {

                if (listOfFiles[i].isFile()) {

                    fileName = listOfFiles[i].getName();

                    for (int x = 0; x < fileExtensionToInclude.length; x++) {

                        String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());

                        //only files with the desired extension are included
                        //if(fileName.endsWith(fileExtensionToInclude)){
                        if (extension.equals(fileExtensionToInclude[x]) && (shouldFilterForRun(fileName, scriptNames))) {

                            //String methodName = fileName.replace(".act","");
                            String methodName = fileName.replace(extension, "");

                            if (singleTestName == null || singleTestName.equalsIgnoreCase(methodName)) {
                                String testMethodName = "test" + methodName;
                                String methodTemplate = baseMethodTemplate.replace("{TestMethodName}", testMethodName);
                                methodTemplate = methodTemplate.replace("{fileExtension}", extension);
                                methodTemplate = methodTemplate.replace("{fileName}", fileName);
                                methodTemplate = methodTemplate.replace("{timeout}", testTimeout);
                                methodTemplate = methodTemplate.replace("{scriptName}", methodName);
                                methodTemplate = methodTemplate.replace("{scriptTags}", tags);
                                methodTemplate = methodTemplate.replace("{actPath}", path + fileName);
                                methodTemplate = methodTemplate.replace("{lkpLibraryName}", lkpLibraryName);
                                methodTemplate = methodTemplate.replace("{logLevel}", logLevel);
                                methodTemplate = methodTemplate.replace("{paramsModules}", paramsModules);
                                methodTemplate = methodTemplate.replace("{dataStrategyRefreshMode}", dataStrategyRefreshMode);

                                methodTemplate = methodTemplate.replace("{reportingEnabled}", reportingEnabled);
                                methodTemplate = methodTemplate.replace("{reportName}", reportName);
                                methodTemplate = methodTemplate.replace("{reportUniqueId}", reportUniqueId);
                                methodTemplate = methodTemplate.replace("{buildName}", buildName);
                                methodTemplate = methodTemplate.replace("{buildUniqueId}", buildUniqueId);

                                if (stopOnAssertions != null)
                                    methodTemplate = methodTemplate.replace("{stopOnAssertions}", stopOnAssertions);
                                else
                                    methodTemplate = methodTemplate.replace("{stopOnAssertions}", "false");


                                methods.append(methodTemplate);
                                methods.append("\n");
                            }
                        }

                    }
                }
                i++;
            }
            baseClassTemplate = baseClassTemplate.replace("{TestMethods}", methods.toString());
            File testFile = new File(testDir + "/" + testName + ".java");
            System.out.println("Creating file " + testDir + "/" + testName + ".java");
            FileWriter fw = new FileWriter(testFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(baseClassTemplate);
            bw.close();
            System.out.println(" i = " + i);
            System.out.println(" end = " + end);
            if (i == end) {
                start = end;
                j++;
                end = batchSize * j;
            } else {
                break;
            }
        }
    }

    public static String readFile(String path) throws IOException {
        File file = new File(path);
        InputStreamReader in = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader br = new BufferedReader(in);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        return sb.toString();
    }

    public static void delete(File file)
            throws IOException {
        if (file.isDirectory()) {
            //directory is empty, then delete it
            if (file.list().length == 0) {
                //file.delete();
                System.out.println("Directory is deleted : " + file.getAbsolutePath());
            } else {
                //list all the directory contents
                String files[] = file.list();
                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);
                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        } else {
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }


    public static void createFolderIfNeeded(String directoryName) {
        File theDir = new File(directoryName);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + directoryName);
            boolean result = theDir.mkdirs();
            if (result) {
                System.out.println("DIR created");
            }

        }
    }


    public static boolean shouldFilterForRun(String scriptName, HashMap parameterMap) {

        boolean included = true;
        boolean excluded = false;
        if (!parameterMap.isEmpty()) {
            if (parameterMap.containsKey("EXCLUDE")) {
                String[] exclValues = ((String) parameterMap.get("EXCLUDE")).split("\\|");
                for (int i = 0; i < exclValues.length; i++) {
                    if (scriptName.toLowerCase().startsWith(exclValues[i].toLowerCase())) {
                        excluded = true;
                        included = false;
                        break;
                    }
                }
            }
            if ((!excluded) && parameterMap.containsKey("INCLUDE")) {
                String[] inclValues = ((String) parameterMap.get("INCLUDE")).split("\\|");
                for (int i = 0; i < inclValues.length; i++) {
                    if (scriptName.toLowerCase().startsWith(inclValues[i].toLowerCase())) {
                        included = true;
                        break;
                    }
                    else
                    {
                        included = false;
                    }
                }
            }
        }
        return included;
    }

}
