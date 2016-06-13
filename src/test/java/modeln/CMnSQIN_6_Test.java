package modeln;

/**
 * Created by IntelliJ IDEA.
 * User: jbpringuey
 * Date: Nov 24, 2012
 * Time: 7:21:53 PM
 * This is a generated class, change ClassTestTemplate.tmpl if needed to modify this class and
 * MethodTestTemplate.tmpl if needed to modify the unit test method.
 * In order to leverage the parallel run feature of maveen surefire, we need to have all test in the same suite.
 */

import org.junit.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import com.modeln.ws.remoteact.MnRemoteActWebSvcService;

import javax.xml.ws.Holder;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import javax.xml.ws.WebServiceException;
import java.util.Iterator;
import javax.xml.soap.*;
import javax.xml.ws.soap.SOAPFaultException;

@FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class CMnSQIN_6_Test {

      /**
        * Initialize the WebService only once to avoid multiple connections to the app server
        * and increase the risk of hitting a connection refused error.
        * Tests within the class will run in series
        */

        private static MnRemoteActWebSvcService init(int retrycount) {
          try{
                return new MnRemoteActWebSvcService();
             }catch(WebServiceException e){
                if(retrycount>0){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        throw new RuntimeException(e1);
                    }
                    System.out.println(" Failed to connect to the webservice, double check the URL, username and password ");
                    return init(retrycount-1);
                }else{
                    throw e;
                }
             }
        }

        private static MnRemoteActWebSvcService  mnWebService = init(30);

        
    @Test
    public void testjnj_42954_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42954_config.xml");
       InputStreamReader in = new InputStreamReader(fis, "UTF-8");
       BufferedReader br = new BufferedReader(in);
       StringBuilder sb = new StringBuilder();
       String line;

       sb.append("var globalScriptTimeOut = 600000;\n\n");
       sb.append("var stopOnAssertions = false; \n");
       sb.append("if(stopOnAssertions === true)\n");
       sb.append("   stopOnAssertionFailure();\n");
       sb.append("else \n");
       sb.append("   continueOnAssertionFailure();\n\n");
       sb.append("var failureReason = '';\n");

       sb.append("var sqin = new SQIN();\n");
       sb.append("var scriptName = 'jnj_42954_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_42954_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_42954_config.xml', '', runContext.runContextId);\n");
       sb.append("//scriptName will be fetched from reading config script \n");
       sb.append("scriptName = scriptInfo.name;\n");

       //Needed for Refresh Data strategy
       sb.append("var paramsModules = 'common,compliance,contracts,drm,gp,managed_care,masterdata,medicaid,pricing,rebates,sales,sv';\n");   
       sb.append("var dataStrategyRefreshMode = 'ALL';\n");

       sb.append("runSQINTest = function(testFunc, scriptName) {\n");
       sb.append("  if(shouldRunOnreplay){\n");
       sb.append("    try {\n");
       sb.append("      beginTx();\n");
       sb.append("      scriptInfo.startTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.beginScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext) \n");
       sb.append("      jnj_42954_config(sqin, runContext);\n");
       sb.append("    }catch(err){\n");
       sb.append("      if(stopOnAssertions === true){\n");
       sb.append("         scriptInfo.result = 'Fail';\n");
       sb.append("         failureReason = err;\n");
       sb.append("      }\n");
       sb.append("      assertTrue('Script Execution Failed:*** '+ err);\n");
       sb.append("      rollbackTx();\n");
       sb.append("    } finally {\n");
       sb.append("      scriptInfo.endTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.endScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext);\n");
       sb.append("    }\n");
       sb.append("  }\n");
       sb.append("};\n\n");
       
       sb.append("function jnj_42954_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_42954_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_42954_config, \"jnj_42954_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42954_config.xml ");
       //running the test
       Holder output = new Holder();
       Holder error = new Holder();
       Holder results = new Holder();
       try{
          mnWebService.getMnRemoteActWebSvcPort().executeAct(sb.toString(),output,error,results);
          System.out.println("\nDebug  logs : " + output.value + "\n");
          System.out.println("\nError logs : " + error.value + "\n");
          System.out.println("\nResults logs : " + results.value + "\n");
          assert (results.value.equals("true"));
       }catch(SOAPFaultException sfe){
            SOAPFault fault=sfe.getFault();
            System.out.println("Error/Run Exception occurred -> "+ fault.getFaultCode());
            Detail detail = fault.getDetail();
            if(detail != null){
            Iterator it=detail.getChildElements();
            System.out.println("Details:");
            while(it.hasNext()){
               SOAPElement se=(SOAPElement)it.next();
               System.out.println(se.getTextContent());
            }
            }
            assert (results.value.equals("false"));
       }
    }


    @Test
    public void testjnj_42980_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42980_config.xml");
       InputStreamReader in = new InputStreamReader(fis, "UTF-8");
       BufferedReader br = new BufferedReader(in);
       StringBuilder sb = new StringBuilder();
       String line;

       sb.append("var globalScriptTimeOut = 600000;\n\n");
       sb.append("var stopOnAssertions = false; \n");
       sb.append("if(stopOnAssertions === true)\n");
       sb.append("   stopOnAssertionFailure();\n");
       sb.append("else \n");
       sb.append("   continueOnAssertionFailure();\n\n");
       sb.append("var failureReason = '';\n");

       sb.append("var sqin = new SQIN();\n");
       sb.append("var scriptName = 'jnj_42980_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_42980_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_42980_config.xml', '', runContext.runContextId);\n");
       sb.append("//scriptName will be fetched from reading config script \n");
       sb.append("scriptName = scriptInfo.name;\n");

       //Needed for Refresh Data strategy
       sb.append("var paramsModules = 'common,compliance,contracts,drm,gp,managed_care,masterdata,medicaid,pricing,rebates,sales,sv';\n");   
       sb.append("var dataStrategyRefreshMode = 'ALL';\n");

       sb.append("runSQINTest = function(testFunc, scriptName) {\n");
       sb.append("  if(shouldRunOnreplay){\n");
       sb.append("    try {\n");
       sb.append("      beginTx();\n");
       sb.append("      scriptInfo.startTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.beginScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext) \n");
       sb.append("      jnj_42980_config(sqin, runContext);\n");
       sb.append("    }catch(err){\n");
       sb.append("      if(stopOnAssertions === true){\n");
       sb.append("         scriptInfo.result = 'Fail';\n");
       sb.append("         failureReason = err;\n");
       sb.append("      }\n");
       sb.append("      assertTrue('Script Execution Failed:*** '+ err);\n");
       sb.append("      rollbackTx();\n");
       sb.append("    } finally {\n");
       sb.append("      scriptInfo.endTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.endScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext);\n");
       sb.append("    }\n");
       sb.append("  }\n");
       sb.append("};\n\n");
       
       sb.append("function jnj_42980_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_42980_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_42980_config, \"jnj_42980_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42980_config.xml ");
       //running the test
       Holder output = new Holder();
       Holder error = new Holder();
       Holder results = new Holder();
       try{
          mnWebService.getMnRemoteActWebSvcPort().executeAct(sb.toString(),output,error,results);
          System.out.println("\nDebug  logs : " + output.value + "\n");
          System.out.println("\nError logs : " + error.value + "\n");
          System.out.println("\nResults logs : " + results.value + "\n");
          assert (results.value.equals("true"));
       }catch(SOAPFaultException sfe){
            SOAPFault fault=sfe.getFault();
            System.out.println("Error/Run Exception occurred -> "+ fault.getFaultCode());
            Detail detail = fault.getDetail();
            if(detail != null){
            Iterator it=detail.getChildElements();
            System.out.println("Details:");
            while(it.hasNext()){
               SOAPElement se=(SOAPElement)it.next();
               System.out.println(se.getTextContent());
            }
            }
            assert (results.value.equals("false"));
       }
    }


    @Test
    public void testjnj_43004_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_43004_config.xml");
       InputStreamReader in = new InputStreamReader(fis, "UTF-8");
       BufferedReader br = new BufferedReader(in);
       StringBuilder sb = new StringBuilder();
       String line;

       sb.append("var globalScriptTimeOut = 600000;\n\n");
       sb.append("var stopOnAssertions = false; \n");
       sb.append("if(stopOnAssertions === true)\n");
       sb.append("   stopOnAssertionFailure();\n");
       sb.append("else \n");
       sb.append("   continueOnAssertionFailure();\n\n");
       sb.append("var failureReason = '';\n");

       sb.append("var sqin = new SQIN();\n");
       sb.append("var scriptName = 'jnj_43004_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_43004_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_43004_config.xml', '', runContext.runContextId);\n");
       sb.append("//scriptName will be fetched from reading config script \n");
       sb.append("scriptName = scriptInfo.name;\n");

       //Needed for Refresh Data strategy
       sb.append("var paramsModules = 'common,compliance,contracts,drm,gp,managed_care,masterdata,medicaid,pricing,rebates,sales,sv';\n");   
       sb.append("var dataStrategyRefreshMode = 'ALL';\n");

       sb.append("runSQINTest = function(testFunc, scriptName) {\n");
       sb.append("  if(shouldRunOnreplay){\n");
       sb.append("    try {\n");
       sb.append("      beginTx();\n");
       sb.append("      scriptInfo.startTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.beginScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext) \n");
       sb.append("      jnj_43004_config(sqin, runContext);\n");
       sb.append("    }catch(err){\n");
       sb.append("      if(stopOnAssertions === true){\n");
       sb.append("         scriptInfo.result = 'Fail';\n");
       sb.append("         failureReason = err;\n");
       sb.append("      }\n");
       sb.append("      assertTrue('Script Execution Failed:*** '+ err);\n");
       sb.append("      rollbackTx();\n");
       sb.append("    } finally {\n");
       sb.append("      scriptInfo.endTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.endScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext);\n");
       sb.append("    }\n");
       sb.append("  }\n");
       sb.append("};\n\n");
       
       sb.append("function jnj_43004_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_43004_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_43004_config, \"jnj_43004_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_43004_config.xml ");
       //running the test
       Holder output = new Holder();
       Holder error = new Holder();
       Holder results = new Holder();
       try{
          mnWebService.getMnRemoteActWebSvcPort().executeAct(sb.toString(),output,error,results);
          System.out.println("\nDebug  logs : " + output.value + "\n");
          System.out.println("\nError logs : " + error.value + "\n");
          System.out.println("\nResults logs : " + results.value + "\n");
          assert (results.value.equals("true"));
       }catch(SOAPFaultException sfe){
            SOAPFault fault=sfe.getFault();
            System.out.println("Error/Run Exception occurred -> "+ fault.getFaultCode());
            Detail detail = fault.getDetail();
            if(detail != null){
            Iterator it=detail.getChildElements();
            System.out.println("Details:");
            while(it.hasNext()){
               SOAPElement se=(SOAPElement)it.next();
               System.out.println(se.getTextContent());
            }
            }
            assert (results.value.equals("false"));
       }
    }


    @Test
    public void testjnj_43161_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_43161_config.xml");
       InputStreamReader in = new InputStreamReader(fis, "UTF-8");
       BufferedReader br = new BufferedReader(in);
       StringBuilder sb = new StringBuilder();
       String line;

       sb.append("var globalScriptTimeOut = 600000;\n\n");
       sb.append("var stopOnAssertions = false; \n");
       sb.append("if(stopOnAssertions === true)\n");
       sb.append("   stopOnAssertionFailure();\n");
       sb.append("else \n");
       sb.append("   continueOnAssertionFailure();\n\n");
       sb.append("var failureReason = '';\n");

       sb.append("var sqin = new SQIN();\n");
       sb.append("var scriptName = 'jnj_43161_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_43161_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_43161_config.xml', '', runContext.runContextId);\n");
       sb.append("//scriptName will be fetched from reading config script \n");
       sb.append("scriptName = scriptInfo.name;\n");

       //Needed for Refresh Data strategy
       sb.append("var paramsModules = 'common,compliance,contracts,drm,gp,managed_care,masterdata,medicaid,pricing,rebates,sales,sv';\n");   
       sb.append("var dataStrategyRefreshMode = 'ALL';\n");

       sb.append("runSQINTest = function(testFunc, scriptName) {\n");
       sb.append("  if(shouldRunOnreplay){\n");
       sb.append("    try {\n");
       sb.append("      beginTx();\n");
       sb.append("      scriptInfo.startTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.beginScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext) \n");
       sb.append("      jnj_43161_config(sqin, runContext);\n");
       sb.append("    }catch(err){\n");
       sb.append("      if(stopOnAssertions === true){\n");
       sb.append("         scriptInfo.result = 'Fail';\n");
       sb.append("         failureReason = err;\n");
       sb.append("      }\n");
       sb.append("      assertTrue('Script Execution Failed:*** '+ err);\n");
       sb.append("      rollbackTx();\n");
       sb.append("    } finally {\n");
       sb.append("      scriptInfo.endTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.endScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext);\n");
       sb.append("    }\n");
       sb.append("  }\n");
       sb.append("};\n\n");
       
       sb.append("function jnj_43161_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_43161_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_43161_config, \"jnj_43161_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_43161_config.xml ");
       //running the test
       Holder output = new Holder();
       Holder error = new Holder();
       Holder results = new Holder();
       try{
          mnWebService.getMnRemoteActWebSvcPort().executeAct(sb.toString(),output,error,results);
          System.out.println("\nDebug  logs : " + output.value + "\n");
          System.out.println("\nError logs : " + error.value + "\n");
          System.out.println("\nResults logs : " + results.value + "\n");
          assert (results.value.equals("true"));
       }catch(SOAPFaultException sfe){
            SOAPFault fault=sfe.getFault();
            System.out.println("Error/Run Exception occurred -> "+ fault.getFaultCode());
            Detail detail = fault.getDetail();
            if(detail != null){
            Iterator it=detail.getChildElements();
            System.out.println("Details:");
            while(it.hasNext()){
               SOAPElement se=(SOAPElement)it.next();
               System.out.println(se.getTextContent());
            }
            }
            assert (results.value.equals("false"));
       }
    }


    @Test
    public void testjnj_44043_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_44043_config.xml");
       InputStreamReader in = new InputStreamReader(fis, "UTF-8");
       BufferedReader br = new BufferedReader(in);
       StringBuilder sb = new StringBuilder();
       String line;

       sb.append("var globalScriptTimeOut = 600000;\n\n");
       sb.append("var stopOnAssertions = false; \n");
       sb.append("if(stopOnAssertions === true)\n");
       sb.append("   stopOnAssertionFailure();\n");
       sb.append("else \n");
       sb.append("   continueOnAssertionFailure();\n\n");
       sb.append("var failureReason = '';\n");

       sb.append("var sqin = new SQIN();\n");
       sb.append("var scriptName = 'jnj_44043_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_44043_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_44043_config.xml', '', runContext.runContextId);\n");
       sb.append("//scriptName will be fetched from reading config script \n");
       sb.append("scriptName = scriptInfo.name;\n");

       //Needed for Refresh Data strategy
       sb.append("var paramsModules = 'common,compliance,contracts,drm,gp,managed_care,masterdata,medicaid,pricing,rebates,sales,sv';\n");   
       sb.append("var dataStrategyRefreshMode = 'ALL';\n");

       sb.append("runSQINTest = function(testFunc, scriptName) {\n");
       sb.append("  if(shouldRunOnreplay){\n");
       sb.append("    try {\n");
       sb.append("      beginTx();\n");
       sb.append("      scriptInfo.startTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.beginScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext) \n");
       sb.append("      jnj_44043_config(sqin, runContext);\n");
       sb.append("    }catch(err){\n");
       sb.append("      if(stopOnAssertions === true){\n");
       sb.append("         scriptInfo.result = 'Fail';\n");
       sb.append("         failureReason = err;\n");
       sb.append("      }\n");
       sb.append("      assertTrue('Script Execution Failed:*** '+ err);\n");
       sb.append("      rollbackTx();\n");
       sb.append("    } finally {\n");
       sb.append("      scriptInfo.endTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.endScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext);\n");
       sb.append("    }\n");
       sb.append("  }\n");
       sb.append("};\n\n");
       
       sb.append("function jnj_44043_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_44043_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_44043_config, \"jnj_44043_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_44043_config.xml ");
       //running the test
       Holder output = new Holder();
       Holder error = new Holder();
       Holder results = new Holder();
       try{
          mnWebService.getMnRemoteActWebSvcPort().executeAct(sb.toString(),output,error,results);
          System.out.println("\nDebug  logs : " + output.value + "\n");
          System.out.println("\nError logs : " + error.value + "\n");
          System.out.println("\nResults logs : " + results.value + "\n");
          assert (results.value.equals("true"));
       }catch(SOAPFaultException sfe){
            SOAPFault fault=sfe.getFault();
            System.out.println("Error/Run Exception occurred -> "+ fault.getFaultCode());
            Detail detail = fault.getDetail();
            if(detail != null){
            Iterator it=detail.getChildElements();
            System.out.println("Details:");
            while(it.hasNext()){
               SOAPElement se=(SOAPElement)it.next();
               System.out.println(se.getTextContent());
            }
            }
            assert (results.value.equals("false"));
       }
    }


    @Test
    public void testjnj_44106_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_44106_config.xml");
       InputStreamReader in = new InputStreamReader(fis, "UTF-8");
       BufferedReader br = new BufferedReader(in);
       StringBuilder sb = new StringBuilder();
       String line;

       sb.append("var globalScriptTimeOut = 600000;\n\n");
       sb.append("var stopOnAssertions = false; \n");
       sb.append("if(stopOnAssertions === true)\n");
       sb.append("   stopOnAssertionFailure();\n");
       sb.append("else \n");
       sb.append("   continueOnAssertionFailure();\n\n");
       sb.append("var failureReason = '';\n");

       sb.append("var sqin = new SQIN();\n");
       sb.append("var scriptName = 'jnj_44106_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_44106_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_44106_config.xml', '', runContext.runContextId);\n");
       sb.append("//scriptName will be fetched from reading config script \n");
       sb.append("scriptName = scriptInfo.name;\n");

       //Needed for Refresh Data strategy
       sb.append("var paramsModules = 'common,compliance,contracts,drm,gp,managed_care,masterdata,medicaid,pricing,rebates,sales,sv';\n");   
       sb.append("var dataStrategyRefreshMode = 'ALL';\n");

       sb.append("runSQINTest = function(testFunc, scriptName) {\n");
       sb.append("  if(shouldRunOnreplay){\n");
       sb.append("    try {\n");
       sb.append("      beginTx();\n");
       sb.append("      scriptInfo.startTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.beginScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext) \n");
       sb.append("      jnj_44106_config(sqin, runContext);\n");
       sb.append("    }catch(err){\n");
       sb.append("      if(stopOnAssertions === true){\n");
       sb.append("         scriptInfo.result = 'Fail';\n");
       sb.append("         failureReason = err;\n");
       sb.append("      }\n");
       sb.append("      assertTrue('Script Execution Failed:*** '+ err);\n");
       sb.append("      rollbackTx();\n");
       sb.append("    } finally {\n");
       sb.append("      scriptInfo.endTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.endScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext);\n");
       sb.append("    }\n");
       sb.append("  }\n");
       sb.append("};\n\n");
       
       sb.append("function jnj_44106_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_44106_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_44106_config, \"jnj_44106_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_44106_config.xml ");
       //running the test
       Holder output = new Holder();
       Holder error = new Holder();
       Holder results = new Holder();
       try{
          mnWebService.getMnRemoteActWebSvcPort().executeAct(sb.toString(),output,error,results);
          System.out.println("\nDebug  logs : " + output.value + "\n");
          System.out.println("\nError logs : " + error.value + "\n");
          System.out.println("\nResults logs : " + results.value + "\n");
          assert (results.value.equals("true"));
       }catch(SOAPFaultException sfe){
            SOAPFault fault=sfe.getFault();
            System.out.println("Error/Run Exception occurred -> "+ fault.getFaultCode());
            Detail detail = fault.getDetail();
            if(detail != null){
            Iterator it=detail.getChildElements();
            System.out.println("Details:");
            while(it.hasNext()){
               SOAPElement se=(SOAPElement)it.next();
               System.out.println(se.getTextContent());
            }
            }
            assert (results.value.equals("false"));
       }
    }


    @Test
    public void testjnj_44107_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_44107_config.xml");
       InputStreamReader in = new InputStreamReader(fis, "UTF-8");
       BufferedReader br = new BufferedReader(in);
       StringBuilder sb = new StringBuilder();
       String line;

       sb.append("var globalScriptTimeOut = 600000;\n\n");
       sb.append("var stopOnAssertions = false; \n");
       sb.append("if(stopOnAssertions === true)\n");
       sb.append("   stopOnAssertionFailure();\n");
       sb.append("else \n");
       sb.append("   continueOnAssertionFailure();\n\n");
       sb.append("var failureReason = '';\n");

       sb.append("var sqin = new SQIN();\n");
       sb.append("var scriptName = 'jnj_44107_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_44107_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_44107_config.xml', '', runContext.runContextId);\n");
       sb.append("//scriptName will be fetched from reading config script \n");
       sb.append("scriptName = scriptInfo.name;\n");

       //Needed for Refresh Data strategy
       sb.append("var paramsModules = 'common,compliance,contracts,drm,gp,managed_care,masterdata,medicaid,pricing,rebates,sales,sv';\n");   
       sb.append("var dataStrategyRefreshMode = 'ALL';\n");

       sb.append("runSQINTest = function(testFunc, scriptName) {\n");
       sb.append("  if(shouldRunOnreplay){\n");
       sb.append("    try {\n");
       sb.append("      beginTx();\n");
       sb.append("      scriptInfo.startTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.beginScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext) \n");
       sb.append("      jnj_44107_config(sqin, runContext);\n");
       sb.append("    }catch(err){\n");
       sb.append("      if(stopOnAssertions === true){\n");
       sb.append("         scriptInfo.result = 'Fail';\n");
       sb.append("         failureReason = err;\n");
       sb.append("      }\n");
       sb.append("      assertTrue('Script Execution Failed:*** '+ err);\n");
       sb.append("      rollbackTx();\n");
       sb.append("    } finally {\n");
       sb.append("      scriptInfo.endTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.endScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext);\n");
       sb.append("    }\n");
       sb.append("  }\n");
       sb.append("};\n\n");
       
       sb.append("function jnj_44107_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_44107_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_44107_config, \"jnj_44107_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_44107_config.xml ");
       //running the test
       Holder output = new Holder();
       Holder error = new Holder();
       Holder results = new Holder();
       try{
          mnWebService.getMnRemoteActWebSvcPort().executeAct(sb.toString(),output,error,results);
          System.out.println("\nDebug  logs : " + output.value + "\n");
          System.out.println("\nError logs : " + error.value + "\n");
          System.out.println("\nResults logs : " + results.value + "\n");
          assert (results.value.equals("true"));
       }catch(SOAPFaultException sfe){
            SOAPFault fault=sfe.getFault();
            System.out.println("Error/Run Exception occurred -> "+ fault.getFaultCode());
            Detail detail = fault.getDetail();
            if(detail != null){
            Iterator it=detail.getChildElements();
            System.out.println("Details:");
            while(it.hasNext()){
               SOAPElement se=(SOAPElement)it.next();
               System.out.println(se.getTextContent());
            }
            }
            assert (results.value.equals("false"));
       }
    }


    @Test
    public void testjnj_44108_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_44108_config.xml");
       InputStreamReader in = new InputStreamReader(fis, "UTF-8");
       BufferedReader br = new BufferedReader(in);
       StringBuilder sb = new StringBuilder();
       String line;

       sb.append("var globalScriptTimeOut = 600000;\n\n");
       sb.append("var stopOnAssertions = false; \n");
       sb.append("if(stopOnAssertions === true)\n");
       sb.append("   stopOnAssertionFailure();\n");
       sb.append("else \n");
       sb.append("   continueOnAssertionFailure();\n\n");
       sb.append("var failureReason = '';\n");

       sb.append("var sqin = new SQIN();\n");
       sb.append("var scriptName = 'jnj_44108_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_44108_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_44108_config.xml', '', runContext.runContextId);\n");
       sb.append("//scriptName will be fetched from reading config script \n");
       sb.append("scriptName = scriptInfo.name;\n");

       //Needed for Refresh Data strategy
       sb.append("var paramsModules = 'common,compliance,contracts,drm,gp,managed_care,masterdata,medicaid,pricing,rebates,sales,sv';\n");   
       sb.append("var dataStrategyRefreshMode = 'ALL';\n");

       sb.append("runSQINTest = function(testFunc, scriptName) {\n");
       sb.append("  if(shouldRunOnreplay){\n");
       sb.append("    try {\n");
       sb.append("      beginTx();\n");
       sb.append("      scriptInfo.startTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.beginScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext) \n");
       sb.append("      jnj_44108_config(sqin, runContext);\n");
       sb.append("    }catch(err){\n");
       sb.append("      if(stopOnAssertions === true){\n");
       sb.append("         scriptInfo.result = 'Fail';\n");
       sb.append("         failureReason = err;\n");
       sb.append("      }\n");
       sb.append("      assertTrue('Script Execution Failed:*** '+ err);\n");
       sb.append("      rollbackTx();\n");
       sb.append("    } finally {\n");
       sb.append("      scriptInfo.endTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.endScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext);\n");
       sb.append("    }\n");
       sb.append("  }\n");
       sb.append("};\n\n");
       
       sb.append("function jnj_44108_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_44108_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_44108_config, \"jnj_44108_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_44108_config.xml ");
       //running the test
       Holder output = new Holder();
       Holder error = new Holder();
       Holder results = new Holder();
       try{
          mnWebService.getMnRemoteActWebSvcPort().executeAct(sb.toString(),output,error,results);
          System.out.println("\nDebug  logs : " + output.value + "\n");
          System.out.println("\nError logs : " + error.value + "\n");
          System.out.println("\nResults logs : " + results.value + "\n");
          assert (results.value.equals("true"));
       }catch(SOAPFaultException sfe){
            SOAPFault fault=sfe.getFault();
            System.out.println("Error/Run Exception occurred -> "+ fault.getFaultCode());
            Detail detail = fault.getDetail();
            if(detail != null){
            Iterator it=detail.getChildElements();
            System.out.println("Details:");
            while(it.hasNext()){
               SOAPElement se=(SOAPElement)it.next();
               System.out.println(se.getTextContent());
            }
            }
            assert (results.value.equals("false"));
       }
    }


    @Test
    public void testjnj_48776_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_48776_config.xml");
       InputStreamReader in = new InputStreamReader(fis, "UTF-8");
       BufferedReader br = new BufferedReader(in);
       StringBuilder sb = new StringBuilder();
       String line;

       sb.append("var globalScriptTimeOut = 600000;\n\n");
       sb.append("var stopOnAssertions = false; \n");
       sb.append("if(stopOnAssertions === true)\n");
       sb.append("   stopOnAssertionFailure();\n");
       sb.append("else \n");
       sb.append("   continueOnAssertionFailure();\n\n");
       sb.append("var failureReason = '';\n");

       sb.append("var sqin = new SQIN();\n");
       sb.append("var scriptName = 'jnj_48776_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_48776_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_48776_config.xml', '', runContext.runContextId);\n");
       sb.append("//scriptName will be fetched from reading config script \n");
       sb.append("scriptName = scriptInfo.name;\n");

       //Needed for Refresh Data strategy
       sb.append("var paramsModules = 'common,compliance,contracts,drm,gp,managed_care,masterdata,medicaid,pricing,rebates,sales,sv';\n");   
       sb.append("var dataStrategyRefreshMode = 'ALL';\n");

       sb.append("runSQINTest = function(testFunc, scriptName) {\n");
       sb.append("  if(shouldRunOnreplay){\n");
       sb.append("    try {\n");
       sb.append("      beginTx();\n");
       sb.append("      scriptInfo.startTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.beginScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext) \n");
       sb.append("      jnj_48776_config(sqin, runContext);\n");
       sb.append("    }catch(err){\n");
       sb.append("      if(stopOnAssertions === true){\n");
       sb.append("         scriptInfo.result = 'Fail';\n");
       sb.append("         failureReason = err;\n");
       sb.append("      }\n");
       sb.append("      assertTrue('Script Execution Failed:*** '+ err);\n");
       sb.append("      rollbackTx();\n");
       sb.append("    } finally {\n");
       sb.append("      scriptInfo.endTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.endScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext);\n");
       sb.append("    }\n");
       sb.append("  }\n");
       sb.append("};\n\n");
       
       sb.append("function jnj_48776_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_48776_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_48776_config, \"jnj_48776_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_48776_config.xml ");
       //running the test
       Holder output = new Holder();
       Holder error = new Holder();
       Holder results = new Holder();
       try{
          mnWebService.getMnRemoteActWebSvcPort().executeAct(sb.toString(),output,error,results);
          System.out.println("\nDebug  logs : " + output.value + "\n");
          System.out.println("\nError logs : " + error.value + "\n");
          System.out.println("\nResults logs : " + results.value + "\n");
          assert (results.value.equals("true"));
       }catch(SOAPFaultException sfe){
            SOAPFault fault=sfe.getFault();
            System.out.println("Error/Run Exception occurred -> "+ fault.getFaultCode());
            Detail detail = fault.getDetail();
            if(detail != null){
            Iterator it=detail.getChildElements();
            System.out.println("Details:");
            while(it.hasNext()){
               SOAPElement se=(SOAPElement)it.next();
               System.out.println(se.getTextContent());
            }
            }
            assert (results.value.equals("false"));
       }
    }


    @Test
    public void testjnj_48777_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_48777_config.xml");
       InputStreamReader in = new InputStreamReader(fis, "UTF-8");
       BufferedReader br = new BufferedReader(in);
       StringBuilder sb = new StringBuilder();
       String line;

       sb.append("var globalScriptTimeOut = 600000;\n\n");
       sb.append("var stopOnAssertions = false; \n");
       sb.append("if(stopOnAssertions === true)\n");
       sb.append("   stopOnAssertionFailure();\n");
       sb.append("else \n");
       sb.append("   continueOnAssertionFailure();\n\n");
       sb.append("var failureReason = '';\n");

       sb.append("var sqin = new SQIN();\n");
       sb.append("var scriptName = 'jnj_48777_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_48777_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_48777_config.xml', '', runContext.runContextId);\n");
       sb.append("//scriptName will be fetched from reading config script \n");
       sb.append("scriptName = scriptInfo.name;\n");

       //Needed for Refresh Data strategy
       sb.append("var paramsModules = 'common,compliance,contracts,drm,gp,managed_care,masterdata,medicaid,pricing,rebates,sales,sv';\n");   
       sb.append("var dataStrategyRefreshMode = 'ALL';\n");

       sb.append("runSQINTest = function(testFunc, scriptName) {\n");
       sb.append("  if(shouldRunOnreplay){\n");
       sb.append("    try {\n");
       sb.append("      beginTx();\n");
       sb.append("      scriptInfo.startTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.beginScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext) \n");
       sb.append("      jnj_48777_config(sqin, runContext);\n");
       sb.append("    }catch(err){\n");
       sb.append("      if(stopOnAssertions === true){\n");
       sb.append("         scriptInfo.result = 'Fail';\n");
       sb.append("         failureReason = err;\n");
       sb.append("      }\n");
       sb.append("      assertTrue('Script Execution Failed:*** '+ err);\n");
       sb.append("      rollbackTx();\n");
       sb.append("    } finally {\n");
       sb.append("      scriptInfo.endTime = new Date().getTime();\n\n");
       sb.append("      sqin.common.endScript('AF_SQIN Automated Testing End to End_null', 'SQIN Automated Testing End to End', 'ContractManagement', 'ContractManagement_AF_null', false, runContext);\n");
       sb.append("    }\n");
       sb.append("  }\n");
       sb.append("};\n\n");
       
       sb.append("function jnj_48777_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_48777_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_48777_config, \"jnj_48777_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_48777_config.xml ");
       //running the test
       Holder output = new Holder();
       Holder error = new Holder();
       Holder results = new Holder();
       try{
          mnWebService.getMnRemoteActWebSvcPort().executeAct(sb.toString(),output,error,results);
          System.out.println("\nDebug  logs : " + output.value + "\n");
          System.out.println("\nError logs : " + error.value + "\n");
          System.out.println("\nResults logs : " + results.value + "\n");
          assert (results.value.equals("true"));
       }catch(SOAPFaultException sfe){
            SOAPFault fault=sfe.getFault();
            System.out.println("Error/Run Exception occurred -> "+ fault.getFaultCode());
            Detail detail = fault.getDetail();
            if(detail != null){
            Iterator it=detail.getChildElements();
            System.out.println("Details:");
            while(it.hasNext()){
               SOAPElement se=(SOAPElement)it.next();
               System.out.println(se.getTextContent());
            }
            }
            assert (results.value.equals("false"));
       }
    }



}

