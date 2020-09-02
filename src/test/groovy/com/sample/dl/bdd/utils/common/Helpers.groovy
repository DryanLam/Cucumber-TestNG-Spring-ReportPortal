package com.sample.dl.bdd.utils.common

class Helpers {
    static def log(String msg){
        println(">> " + msg)
    }

    /**
     * Print message in command window and exit program
     * with exit code accordingly
     * Able to assume this method is a simple kind of log
     * @param   msg    String message to display status
     * @param   code   Exit code
     */
    static def displayMsg(String msg, def code){
        log(msg)
        switch (code){
            case 1:
                System.exit(1); break
            default:
                System.exit(0); break
        }
    }

    boolean isListEqual(def fList, def sList) {
        return fList.containsAll(sList) && fList.size() == sList.size()
    }
}
