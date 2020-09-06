package com.sample.dl.bdd.utils.common

import groovy.util.logging.Slf4j

@Slf4j
class ConfigHandler {

    private static Properties prop

    /**
     * Initial config.properties file
     */
    static ConfigHandler init(){
        try{
            prop = new Properties();
            def inputStream = ClassLoader.getSystemResourceAsStream("config.properties")
            prop.load(inputStream);
        }catch (Exception e){
            log.info(e.printStackTrace())
        }
        return new ConfigHandler()
    }

    static String getLogLevel(){
        prop.getProperty("log.level")
    }


    static String getDataTestFolder(){
        prop.getProperty("data.test.folder")
    }
}
