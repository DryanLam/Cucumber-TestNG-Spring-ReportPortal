package com.sample.dl.bdd.utils.common

import groovy.util.logging.Slf4j

@Slf4j
class ConfigHandler {
    private Properties prop

    ConfigHandler(){
        initConfig()
    }

    def initConfig(){
        try{
            prop = new Properties();
            def inputStream = ClassLoader.getSystemResourceAsStream("config.properties")
            prop.load(inputStream);
        }catch (Exception e){
            log.info(e.printStackTrace())
        }
    }

    def getLogLevel(){
        prop.getProperty("log.level")
    }

    def getBrowserType(){
        prop.getProperty("browser.test.type")
    }

    def getBrowserScriptTimeout(){
        prop.getProperty("browser.script.timeout").toInteger()
    }

    def getBrowserPageLoadTimeout(){
        prop.getProperty("browser.page.timeout").toInteger()
    }

    def getGridHubServer(){
        prop.getProperty("remote.server.hub")
    }
}
