package com.modeln.generator;

/**
 * Created by IntelliJ IDEA.
 * class that creates the context Initializer
 * test class and formats the act script with
 * parameters passed in the call (e.g. DEBUGGING_LEVEL
 */

import java.io.File;
import java.io.File;
import java.io.File;

import java.io.IOException;
import java.io.*;

import org.apache.commons.io.*;

/**
 * File that generates test dynamically based on ACT that are in the directory and the base template for better integration with jenkins reporting and
 * maveen. The build in Jenkins has the following steps.
 *
 * generate the test files to leverage jenkins test reporting structure
 * mvn exec:java -Dexec.mainClass="com.modeln.generator.ModelNContextInitializerGenerator" -Dexec.args="/home/jbpringuey/temp/act/"
 *
 * Execute test using maveen
 * mvn test
 */

public class ModelNContextManagementWrapperGenerator {

    /**
     * mvn exec:java -Dexec.mainClass="com.modeln.generator.ModelNContextInitializerGenerator"
     *
     * class level template  {ClassName} (Needs to end with Test) {TestMethods}
     * method level template  {TestMethodName} (Needs to start with test) {actPath}
     */
    public static void main(String[] args) throws Exception {

        String action = args[0];

        String workingDir = System.getProperty("user.dir");

        String testDir = workingDir + "/src/test/java/modeln";
        delete(new File(testDir));

        createFolderIfNeeded(workingDir + "/src/test/java/modeln");

        //The context initialize root is where the "script" and "template" folders
        //for the initiaize script are located
        String rootPath = null;
        if (action.equals("INITIALIZE")) {
            rootPath = System.getenv("SQIN_CONTEXT_INTIALIZE_ROOT_PATH");
        }

        if (action.equals("TERMINATE")) {
            rootPath = System.getenv("SQIN_CONTEXT_TERMINATE_ROOT_PATH");
        }

        System.out.println("Root path is " + rootPath);

        String loggingLevel = System.getenv("LOGGING_LEVEL");
        String runMode = System.getenv("RUN_MODE");
        String reportingEnabled = System.getenv("SQIN_BATAM_REPORTING_ENABLED");
        String buildName = System.getenv("SQIN_BUILD_NAME");
        String buildId = System.getenv("BATAM_BUILD_ID");
        String stopOnAssertions = System.getenv("SQIN_STOP_ON_ASSERTIONS");

        if (buildName == null) {
            reportingEnabled = "false";
        }

        String replayContextId = System.getenv("SQIN_REPLAY_CONTEXT");
        String batamClientHost = System.getenv("SQIN_CLIENT_HOST");
        String buildNo = System.getenv("SQIN_BUILD_NUMBER");

        String buildUniqueId = batamClientHost.concat("_" + buildName).concat("_" + buildNo);

        String scriptTemplatePath = rootPath + "template";
        String scriptPath = rootPath + "script";

        /**
         * deletes files existing in the "script" folder to that new ones
         * can be built from the template, using the parameters for the current run
         */
        delete(new File(scriptPath));

        createFolderIfNeeded(scriptPath);

        /**
         * replaces content in the template acts with the values passed as arguments
         * before copying the updated act from the template into the script folder
         */
        try {
            String content = FileUtils.readFileToString(new File(scriptTemplatePath + "/" + "SQIN_" + action + "_CONTEXT.template"), "UTF-8");

            if (loggingLevel != null) {
                content = content.replaceAll("@@@loggingLevel@@@", loggingLevel);
            }

            if (runMode != null) {
                content = content.replaceAll("@@@runMode@@@", runMode);
            }

            if (reportingEnabled != null) {
                content = content.replaceAll("@@@reportingEnabled@@@", reportingEnabled);
            }

            if (buildNo != null) {
                content = content.replaceAll("@@@buildId@@@", buildNo);
            } else {
                content = content.replaceAll("@@@buildId@@@", "");
            }

            if (buildName != null) {
                content = content.replaceAll("@@@buildName@@@", buildName);
                content = content.replaceAll("@@@buildUniqueId@@@", buildUniqueId);
            }

            if (replayContextId != null) {
                content = content.replaceAll("@@@replayContextId@@@", replayContextId);
            } else {
                content = content.replaceAll("@@@replayContextId@@@", "");
            }

            if (stopOnAssertions != null )
                content = content.replaceAll("@@@stopOnAssertions@@@", stopOnAssertions);
            else
                content = content.replaceAll("@@@stopOnAssertions@@@", "false");

            File tempFile = new File(scriptPath + "/" + action+"_CONTEXT.act");
            FileUtils.writeStringToFile(tempFile, content, "UTF-8");
        } catch (IOException e) {
            //Simple exception handling, replace with what's necessary for your use case!
            throw new RuntimeException("Generating file failed", e);
        }

        String fileName;

        File folder = new File(scriptPath);
        File[] listOfFiles = folder.listFiles();

        int batchSize = 10;
        int start = 0;
        int end = batchSize;
        int j = 1;
        while (true) {
            String baseClassTemplate = readFile(workingDir + "/src/main/java/com/modeln/generator/ClassTestTemplate.tmpl");
            String baseMethodTemplate = readFile(workingDir + "/src/main/java/com/modeln/generator/MethodTestTemplate.tmpl");
            String testName = "CMnSmoke" + j + "Test";
            System.out.println(" testName is " + testName);
            baseClassTemplate = baseClassTemplate.replace("{ClassName}", testName);
            StringBuilder methods = new StringBuilder();
            int i = start;
            while (i < listOfFiles.length && i < end) {
                if (listOfFiles[i].isFile()) {
                    fileName = listOfFiles[i].getName();
                    System.out.println(scriptPath + "/" + fileName);
                    String methodName = fileName.replace(".act", "");
                    String testMethodName = "test" + methodName;
                    String methodTemplate = baseMethodTemplate.replace("{TestMethodName}", testMethodName);
                    methodTemplate = methodTemplate.replace("{actPath}", scriptPath + "/" + fileName);
                    methods.append(methodTemplate);
                    methods.append("\n");
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


}
