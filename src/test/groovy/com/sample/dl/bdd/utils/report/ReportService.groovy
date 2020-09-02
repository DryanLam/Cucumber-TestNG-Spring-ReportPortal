package com.sample.dl.bdd.utils.report

import cucumber.runner.Scenario


//import com.sample.dl.bdd.utils.common.IOUtils
//import io.cucumber.java.Scenario

class ReportService {

    static def runTimeStatus

    static def logStepResult(List stepResult) {
        runTimeStatus += [stepResult]
        // index,status, step, message
    }

    def customReport(String pathFile, String results) {
//        def io = new IOUtils()
//        io.writeFile(results, pathFile)
    }

//    def scenarioResult(){
//
//    }

    public static void resetService() {
        currentStepDefIndex = 0
        runTimeStatus = []
    }

    private static int currentStepDefIndex;

    public static int getStepIndex() {
        return currentStepDefIndex;
    }

    public static void setStepIndex(int indexStep) {
        currentStepDefIndex = indexStep;
    }

    public static String getErrorMessage(Scenario scenario) {
        def lstSteps = scenario.delegate?.stepResults
        def errorMsg = lstSteps?.last().error?.stackTrace.toString()
        return errorMsg
    }

    static String currentStepDescription(Scenario scenario) throws Exception {
        String curStepText = null
        int curIndex = getStepIndex()
        def stepDefs = scenario?.delegate?.testCase?.pickle?.steps
        def currentStepDef = stepDefs.get(curIndex)
        curStepText = currentStepDef?.keyWord + currentStepDef?.step?.text
        curIndex += 1
        setStepIndex(curIndex)
        return curStepText
    }

}
