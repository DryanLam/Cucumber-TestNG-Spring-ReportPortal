package com.sample.dl.bdd.utils.common

import groovy.util.logging.Slf4j

@Slf4j
class IOUtils {
    public static final String ENCODER = "UTF-8"
    public static final Integer HEADER_ROW = 0
    public static final String SEPARATOR = ","
    public static final String CSV_REGEX = "${SEPARATOR}(?=([^\"]*\"[^\"]*\")*[^\"]*\$)"

    /**
     * Read all data of file and return unique respective rows
     * @param   file        File input stream
     * @param   rowNum      Number of unique rows to get from file.
     * @param   colIndex    Column Index of csv
     * @param   existHeader True is meaning file containing headers
     */
    def readFile(File file, def rowNum = "All", def colIndex = null, def existHeader = true) {
        def results
        if(rowNum.toString().equalsIgnoreCase("All")){
            results = getAllUniqueRows(file, colIndex)
        } else {
            rowNum = existHeader ? ++rowNum : rowNum
            results = getNumberUniqueRows(file, rowNum, colIndex)
        }
        results
    }

    /**
     * Get only unique data row of csv file
     * @param   file        File input stream
     * @param   rowNum      Number of unique rows to get from file.
     * @param   colIndex    Column Index of csv
     * @param   existHeader True is meaning file containing headers
     */
    def getCSVData(File file, def rowNum = "All", def colIndex = null, def existHeader = true) {
        if(existHeader){
            return readFile(file,rowNum,colIndex,existHeader).drop(1)
        }else{
            return readFile(file,rowNum,colIndex,existHeader)
        }
    }

    /**
     * This method supports read file to get all unique data rows
     * @param   file        File input
     * @param   colIndex    Column index of data
     */
    private def getAllUniqueRows(File file, def colIndex = null){
        try{
            def rows  = []
            def dataRow
            file.withReader(ENCODER) { reader ->
                while ((dataRow = reader.readLine()) != null) {
                    def row = colIndex == null ? dataRow
                            : getColumnDataByIndex(dataRow, colIndex as int)
                    if(!isSimilar(rows, row)){
                        rows += row
                    }
                }
            }
            rows
        }catch(e){
            log.info(e.message)
        }
    }

    /**
     * This method supports read file to get specific
     * number unique data rows
     * @param   file        File input
     * @param   numRow      Number of unique data able to get
     * @param   colIndex    Column index of data
     */
    private def getNumberUniqueRows(File file, def numRow, def colIndex = null){
        try{
            def dataRow
            def rows = []
            file.withReader(ENCODER) { reader ->
                while ((dataRow = reader.readLine()) != null) {
                    def row = colIndex == null ? dataRow
                            : getColumnDataByIndex(dataRow, colIndex as int)
                    if(!isSimilar(rows, row)){
                        rows += row
                    }

                    // Make sure to match required number
                    if(rows.size() == numRow.toInteger()){
                        return rows
                    }
                }
            }
            rows
        }catch(e){
            log.info(e.message)
        }
    }

    /**
     * This method is to return data of csv
     * according to column index
     * @param   dataRow    String row value
     * @param   index      Index of column
     */
    def getColumnDataByIndex(def dataRow, int index){
        readLineCSV(dataRow.toString())[index]
    }

    /**
     * Writer data to file accepting unicode
     * @param   dataRow    Data row value
     * @param   filePath   Path of file
     */
    def writeFile(String dataRow, String filePath){
        try{
            def fOut = new File(filePath)
            def pFile = fOut.getPath()
            log.info("Your output file is: ${pFile}")
            fOut.withWriter(ENCODER) { writer ->
                writer.writeLine(dataRow)
            }
        }catch(e){
            log.info(e.message)
        }
    }

    /**
     * Find index of header column
     * @param   file     File input
     * @param   header   Header name
     */
    def headerNameIndex(File file, String header){
        def headerRow = readFile(file, HEADER_ROW)?.first()
        def headers = readLineCSV(headerRow)
        headers.findIndexOf {it.toString().equalsIgnoreCase(header)}
    }

    /**
     * This method is to render csv data.
     * It is able to break complicated csv data
     * that include special character and quotes
     * @param   data    String csv data
     */
    def readLineCSV(String data){
        data.split(CSV_REGEX)
    }

    /**
     * Check the identical of string data in existing list
     * @param   lst     List of string data
     * @param   str     String data have to compare in list
     */
    def isSimilar(def lst, String str){
        def tmpStr = shortenString(str)
        return lst.any {shortenString(it).equalsIgnoreCase(tmpStr)}
    }

    /**
     * Remove any leading/trailing quote
     * @return       String without any leading
     *               or trailing quote
     * @param   str  String data containing
     *               any leading/trailing quote
     */
    def shortenString(String str){
        def sString = str
        if(str.endsWithAny("\"", "\'") || str.startsWithAny("\"", "\'")){
            sString = str.replaceAll("^[\"']+|[\"']+\$", "")
        }
        sString
    }
}
