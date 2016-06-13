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
public class CMnSQIN_5_Test {

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
    public void testjnj_38280_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_38280_config.xml");
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
       sb.append("var scriptName = 'jnj_38280_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_38280_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_38280_config.xml', '', runContext.runContextId);\n");
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
       sb.append("      jnj_38280_config(sqin, runContext);\n");
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
       
       sb.append("function jnj_38280_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_38280_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_38280_config, \"jnj_38280_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_38280_config.xml ");
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
    public void testjnj_38419_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_38419_config.xml");
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
       sb.append("var scriptName = 'jnj_38419_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_38419_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_38419_config.xml', '', runContext.runContextId);\n");
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
       sb.append("      jnj_38419_config(sqin, runContext);\n");
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
       
       sb.append("function jnj_38419_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_38419_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_38419_config, \"jnj_38419_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_38419_config.xml ");
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
    public void testjnj_39032_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_39032_config.xml");
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
       sb.append("var scriptName = 'jnj_39032_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_39032_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_39032_config.xml', '', runContext.runContextId);\n");
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
       sb.append("      jnj_39032_config(sqin, runContext);\n");
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
       
       sb.append("function jnj_39032_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_39032_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_39032_config, \"jnj_39032_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_39032_config.xml ");
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
    public void testjnj_39483_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_39483_config.xml");
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
       sb.append("var scriptName = 'jnj_39483_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_39483_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_39483_config.xml', '', runContext.runContextId);\n");
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
       sb.append("      jnj_39483_config(sqin, runContext);\n");
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
       
       sb.append("function jnj_39483_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_39483_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_39483_config, \"jnj_39483_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_39483_config.xml ");
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
    public void testjnj_39816_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_39816_config.xml");
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
       sb.append("var scriptName = 'jnj_39816_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_39816_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_39816_config.xml', '', runContext.runContextId);\n");
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
       sb.append("      jnj_39816_config(sqin, runContext);\n");
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
       
       sb.append("function jnj_39816_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_39816_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_39816_config, \"jnj_39816_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_39816_config.xml ");
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
    public void testjnj_42816_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42816_config.xml");
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
       sb.append("var scriptName = 'jnj_42816_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_42816_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_42816_config.xml', '', runContext.runContextId);\n");
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
       sb.append("      jnj_42816_config(sqin, runContext);\n");
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
       
       sb.append("function jnj_42816_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_42816_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_42816_config, \"jnj_42816_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42816_config.xml ");
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
    public void testjnj_42838_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42838_config.xml");
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
       sb.append("var scriptName = 'jnj_42838_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_42838_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_42838_config.xml', '', runContext.runContextId);\n");
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
       sb.append("      jnj_42838_config(sqin, runContext);\n");
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
       
       sb.append("function jnj_42838_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_42838_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_42838_config, \"jnj_42838_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42838_config.xml ");
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
    public void testjnj_42862_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42862_config.xml");
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
       sb.append("var scriptName = 'jnj_42862_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_42862_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_42862_config.xml', '', runContext.runContextId);\n");
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
       sb.append("      jnj_42862_config(sqin, runContext);\n");
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
       
       sb.append("function jnj_42862_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_42862_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_42862_config, \"jnj_42862_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42862_config.xml ");
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
    public void testjnj_42863_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42863_config.xml");
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
       sb.append("var scriptName = 'jnj_42863_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_42863_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_42863_config.xml', '', runContext.runContextId);\n");
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
       sb.append("      jnj_42863_config(sqin, runContext);\n");
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
       
       sb.append("function jnj_42863_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_42863_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_42863_config, \"jnj_42863_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42863_config.xml ");
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
    public void testjnj_42941_config() throws Exception {

       //reading the ACT file that was checked in perforce
       FileInputStream fis = new FileInputStream("C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42941_config.xml");
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
       sb.append("var scriptName = 'jnj_42941_config';\n");
       sb.append("var scriptDescription = '';\n\n");
       sb.append("var stepHashTable = new Object();\n");
       sb.append("var scriptInfo = { name : undefined, description : undefined, expectedRunTime: undefined, \n");
       sb.append("                   startTime : undefined , endTime : undefined,\n");
       sb.append("                   errors : 0, result: 'Pending', tags: undefined };\n");
       sb.append("var stepInfo = { name : 'initalStep', description : undefined, \n");
       sb.append("                   modelHolder : undefined , actionResult : undefined,\n");
       sb.append("                   startTime : undefined , endTime : undefined , errors : 0, errMsgs : undefined };\n");
       sb.append("var stepsCollection = new java.util.ArrayList(); \n");
       sb.append("scriptInfo.name = 'jnj_42941_config';\n");

       sb.append("var stepSummary = '';\n\n");
       sb.append("var runContext = sqin.common_util.getActiveContextOrCreateNew('NONE','Description','ACTUAL');\n");
       sb.append("var lkpLibraryName = 'Contracts';\n");
       sb.append("var shouldRunOnreplay = sqin.common_util.shouldScriptRunOnReplay('jnj_42941_config.xml', '', runContext.runContextId);\n");
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
       sb.append("      jnj_42941_config(sqin, runContext);\n");
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
       
       sb.append("function jnj_42941_config(sqin, runContext) {\n");

       String fileExtension = ".xml";

       if(fileExtension == ".act"){
          while ((line = br.readLine()) != null) {
             sb.append(line + "\n");
          }
       }else if(fileExtension == ".xml"){

          sb.append("sqin.script_runner.runXmlConfig('jnj_42941_config.xml', false);");
       }
       
       sb.append("};\n");

       sb.append("runSQINTest(jnj_42941_config, \"jnj_42941_config\");\n");

       br.close();

       System.out.println("\n\nExecuting C:/Users/vjunnuri/Desktop/sqin/SQINLibrary/sqinlib/scripts_config/Contracts/jnj_42941_config.xml ");
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

