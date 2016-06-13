package com.modeln.sqin.reporting;

import com.modeln.batam.connector.wrapper.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQINBatamConnector {

    /**
     * mvn exec:java -Dexec.mainClass="com.modeln.sqin.reporting.SQINBatamConnector" -Dexec.args=""
     */
    public static void main(String[] args) throws Exception {

        String action = args[0];
        String batamBuildName = System.getenv("SQIN_BUILD_NAME");
        boolean reportingEnabled = Boolean.parseBoolean(System.getenv("SQIN_BATAM_REPORTING_ENABLED"));

        //only if BATAM reporting enabled actions will be executed
        if (reportingEnabled && batamBuildName != null) {
            String reportName = System.getenv("JOB_NAME");
            String buildUrl = System.getenv("BUILD_URL");
            String batamClientHost = System.getenv("SQIN_CLIENT_HOST");
            String description = System.getenv("JOB_NAME");
            String buildNo = System.getenv("SQIN_BUILD_NUMBER");

            String buildUniqueId = batamClientHost.concat("_" + batamBuildName).concat("_" + buildNo);
            String reportUniqueId = reportName.concat("_" + batamClientHost).concat("_"+buildNo);
            String hyperLink = "<a href=\"" + buildUrl + "\" target=\"_blank\" >Log URL</a>";

            if (action.toUpperCase().equals("START")) {
                startModuleReport(reportName, reportUniqueId, batamBuildName, buildUniqueId, hyperLink, description);
            } else if (action.toUpperCase().equals("END")) {
                endModuleReport(reportName, reportUniqueId, batamBuildName, buildUniqueId);
            } else if (action.toUpperCase().equals("START_BUILD")) {
                startBuild(batamBuildName, buildUniqueId, hyperLink, description);
            } else if (action.toUpperCase().equals("END_BUILD")) {
                endBuild(batamBuildName, buildUniqueId);
            }
        }
    }


    public static void startModuleReport(String reportName, String reportUniqueId, String buildName,
                                         String buildUniqueId, String buildUrl, String description) {

        List<String> logs = new java.util.ArrayList<String>();
        if (buildUrl != null)
            logs.add(buildUrl);

        try {
            String msg = com.modeln.batam.connector.ConnectorHelper.createReport(
                    /*reportId*/ reportUniqueId,
                    /*reportName*/ reportName,
                    /*buildId*/ buildUniqueId,
                    /*buildName*/ buildName,
                    /*description*/ description,
                    /*startDate*/ new Date(),
                    /*endDate*/ null,
                    /*status*/ "pending",
                    /*logsList*/ logs);

            System.out.println("response for createBuild for" + reportName + " : " + msg);

        } catch (IOException ioe) {
            System.out.println("startModuleReport failed with IOException : " + ioe.getMessage());
        } catch (NullPointerException npe) {
            System.out.println("startModuleReport failed with NullPointerException : " + npe.getMessage());
        }
    }

    public static void endModuleReport(String reportName, String reportUniqueId, String buildName, String buildUniqueId) {

        try {

            String msg = com.modeln.batam.connector.ConnectorHelper.updateReportStatus(
                    /*reportId*/ reportUniqueId,
                    /*reportName*/ reportName,
                    /*buildId*/ buildUniqueId,
                    /*buildName*/ buildName,
                    /*status*/ "completed");
            System.out.println("response for updateReportStatus for" + reportName + " : " + msg);

            msg = com.modeln.batam.connector.ConnectorHelper.updateReportEndDate(
                /*reportId*/ reportUniqueId,
                /*reportName*/ reportName,
                /*buildId*/ buildUniqueId,
                /*buildName*/ buildName,
                /*endDate*/ new Date());
            System.out.println("response for updateReportEndDate for" + reportName + " : " + msg);

        } catch (IOException ioe) {
            System.out.println("endModuleReport failed with IOException : " + ioe.getMessage());
        } catch (NullPointerException npe) {
            System.out.println("endModuleReport failed with NullPointerException : " + npe.getMessage());
        }
    }

    public static void startBuild(String buildName, String buildUniqueId, String buildUrl, String description) {

        List<Pair> infoPairs = new ArrayList<Pair>();
        if (buildUrl != null)
            infoPairs.add(new Pair("Jenkins Log", buildUrl));

        String appVersion = System.getenv("APPLICATION_VERSION");
        if(appVersion == null)
            appVersion = "<enter application version>";

        List<Pair> criteriaPairs = new ArrayList<Pair>();
        criteriaPairs.add (new Pair("branch", appVersion));

        try {
            String msg = com.modeln.batam.connector.ConnectorHelper.createBuild(
                /*buildId*/ buildUniqueId,
                /*buildName*/ buildName,
                /*startDate*/ new Date(),
                /*endDate*/ null,
                /*status*/ null,
                /*description*/ description,
                /*criteriaPairList*/ criteriaPairs,
                /*infoPairList*/ infoPairs,
                /*reportsPairList*/ null,
                /*stepsPairList*/ null,
                /*commitsPairList*/ null);

            System.out.println("Created new build entry in Batam for buildName : " + buildName
                    + " and Batam Response is:" + msg);


        } catch (IOException ioe) {
            System.out.println("startBuild failed with IOException : " + ioe.getMessage());
        } catch (NullPointerException npe) {
            System.out.println("startBuild failed with NullPointerException : " + npe.getMessage());
        }
    }

    public static void endBuild(String buildName, String buildUniqueId) {

        String msg = "";
        try {
            msg = com.modeln.batam.connector.ConnectorHelper.updateBuildStatus(
                /*id*/ buildUniqueId,
                /*name*/ buildName,
                /*status*/ "completed");
            System.out.println("updated build status for Batam build :" + buildName + " and Batam Repsonse is : " + msg);

            msg = com.modeln.batam.connector.ConnectorHelper.updateBuildEndDate(
                /*id*/ buildUniqueId,
                /*name*/ buildName,
                /*endDate*/ new Date());

            System.out.println("updated build EndDate for Batam build :" + buildName + " and Batam Repsonse is : " + msg);

            msg = com.modeln.batam.connector.ConnectorHelper.runAnalysis(
                /*id*/ buildUniqueId,
                /*name*/ buildName,
                /*overrideFlag*/ false);
            System.out.println("Completed runAnalysis for Batam build :" + buildName + " and Batam Repsonse is : " + msg);

        } catch (IOException ioe) {
            System.out.println("endBuild failed with IOException : " + ioe.getMessage());
        } catch (NullPointerException npe) {
            System.out.println("endBuild failed with NullPointerException : " + npe.getMessage());
        }
    }
}
