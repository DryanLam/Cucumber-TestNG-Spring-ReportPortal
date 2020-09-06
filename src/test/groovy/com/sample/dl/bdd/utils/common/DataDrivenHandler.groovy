package com.sample.dl.bdd.utils.common

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.DocumentContext
import com.jayway.jsonpath.JsonPath
import com.jayway.jsonpath.TypeRef
import com.jayway.jsonpath.spi.json.JacksonJsonProvider
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider
import groovy.util.logging.Slf4j
import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.DirectoryFileFilter
import org.apache.commons.io.filefilter.RegexFileFilter

import java.nio.file.Path
import java.nio.file.Paths

@Slf4j
class DataDrivenHandler {

    private static DataDrivenHandler data
    private DocumentContext dataDrivenObject
    private static final String DEFAULT_DATA_PATH_DIR = "/src/test/groovy/com/sample/dl/bdd/data"

    private DataDrivenHandler() {
        def initFolder = ConfigHandler.init().getDataTestFolder()
        def testFolder = (initFolder != null && !initFolder.equals("")) ? initFolder : DEFAULT_DATA_PATH_DIR
        def srcFolder = System.getProperty("user.dir") + testFolder
        log.info("the data path directiory is : " + srcFolder)

        def files = getAllDataFilePath(srcFolder)
        List<String> jsonData = new ArrayList<>()
        for (Object file : files) {
            String json = readDataFromFile(((File) file).getAbsolutePath())
            jsonData.add(json)
        }

        final Configuration configuration = Configuration.builder()
                                                         .jsonProvider(new JacksonJsonProvider())
                                                         .mappingProvider(new JacksonMappingProvider())
                                                         .build()
        dataDrivenObject = JsonPath.using(configuration).parse(jsonData.toString())
    }


    public static DataDrivenHandler getInstance() {
        return data ?: new DataDrivenHandler()
    }


    /**
     * This method is used to call API via GET method
     * @param jsonString String json
     * @param jsonPath JSON path to query object
     * @return List        List of result objects
     * Ex: getJsonValue(jsonString, "\$..data")
     */

//    def queryPath(def jsonPath){
//        DocumentContext dataContext = JsonPath.parse(jsonString)
//        dataContext.read(jsonPath)
//    }

    /*-----------------       Support read data as DTO -------------------*/

    public String getValue(String jsonPath) {
        return dataDrivenObject.read(jsonPath)
    }


    public <T> T getValue(String jsonPath, Class<T> clazz) {
        T value = null
        try {
            List<T> values = dataDrivenObject.read(jsonPath);
            if (values != null && values.size() > 0) {
                value = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).convertValue(value, clazz)
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
        return value
    }


    public <T> T getValues(String jsonPath, TypeRef<T> typeRef) {
        return dataDrivenObject.read(jsonPath, typeRef)
    }

    /**
     * File reader json in test folder
     */
    private String readDataFromFile(String path) {
        String data = "", line
        Path filePath = Paths.get(path)

        try {
            FileInputStream fin = new FileInputStream(filePath.toFile())
            InputStreamReader inStream = new InputStreamReader(fin)
            BufferedReader bufferedReader = new BufferedReader(inStream)
            StringBuilder sb = new StringBuilder()
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line)
            }
            data = sb.toString()
        } catch (Exception e) {
            e.printStackTrace()
        }
        return data
    }

    /**
     * Read all json files only
     */
    def getAllDataFilePath(String path) {
        Collection files = FileUtils.listFiles(
                new File(path),
                new RegexFileFilter('^(.*?)(\\.json$)'),
                DirectoryFileFilter.DIRECTORY
        );
        return files
    }
}
