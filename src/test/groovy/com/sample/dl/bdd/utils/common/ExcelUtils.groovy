package com.sample.dl.bdd.utils.common

import groovy.util.logging.Slf4j
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.stereotype.Service

@Service
@Slf4j
class ExcelUtils {

    // Support for multi-workbook types
    private Workbook wb
    private Sheet wbSheet
    private Row wbRow


    /**
     * This method helps to prepare Excel file in a dedicated folder
     * @param fileName Absolute excel file
     * @param sheetName Name of excel sheet or sheet index
     * @param rowNum Row number
     * @param colNum Column number
     * @return String        Value of cell
     */
    def readFile(String fileName, String sheetName, int rowNum, int colNum) {
        def srcDir = System.getProperty("user.dir") + "/src/test/groovy/com/sample/dl/bdd/data/"
        def srcFile = srcDir + fileName
        wb = openWorkbook(srcFile)
        wbSheet = getSheet(wb, sheetName)
        wbRow = getRow(wbSheet, rowNum)
        return getCellValue(wbRow, colNum)
    }


    /**
     * This method is used to set data to an existing excel file by row number & column index
     * @param fileName Name of excel file
     * @param sheetName Name of sheet
     * @param rowNum Row number calculated from header(Header row is 0, next data row is 1)
     * @param colNum Column number
     * @param value Data value to be set in cell
     */
    def updateFile(String fileName, String sheetName, int rowNum, int colNum, def value) {
        def srcDir = System.getProperty("user.dir") + "/src/test/groovy/com/sample/dl/bdd/data/"
        def srcFile = srcDir + fileName
        wb = openWorkbook(srcFile)
        wbSheet = getSheet(wb, sheetName)
        wbRow = getRow(wbSheet, rowNum)
        setCell(wbRow, colNum, value)
        saveWorkbook(wb, srcFile)
    }


    /**
     * This method is used to set a row data with multi-column name to an existing excel file by row number & column index
     * @param fileName Name of excel file
     * @param sheetName Name of sheet
     * @param rowData The data information will be set into cell
     * Data sample:
     *   inputData = [rowNum: 1, rowData: [[colName: "Transfer", colValue: "ABC"],[colName: "Date", colValue: "ABC"]]]
     *
     * */
    def updateDataRow(def fileName, String sheetName, def inputData) {
        def srcDir = System.getProperty("user.dir") + "/src/test/groovy/com/sample/dl/bdd/data/"
        def srcFile = srcDir + fileName
        wb = openWorkbook(srcFile)
        wbSheet = getSheet(wb, sheetName)
        wbRow = getRow(wbSheet, inputData.rowNum)
        def items = inputData.rowData
        items.each { item -> setCell(item.colName, item.colValue) }
        saveWorkbook(wb, srcFile)
    }


    /**
     * This method is used to set a row data with multi-column name to an existing excel file by row number & column index
     * @param fileName Name of Excel file
     * @param sheetName The name of sheet
     * @param rowData The data information will be set into cell
     * Data sample:
     *    lstData = [
     * 					 [rowNum: 1, rowData: [[colName: "Transfer", colValue: "ABC"],[colName: "Date", colValue: "ABC"]]],
     * 					 [rowNum: 2, rowData: [[colName: "Transfer", colValue: "ABC"],[colName: "Date", colValue: "ABC"]]]
     * 					]
     *
     */
    def updateMultiDataRows(def fileName, String sheetName, def lstData) {
        def srcDir = System.getProperty("user.dir") + "/src/test/groovy/com/sample/dl/bdd/data/"
        def srcFile = srcDir + fileName
        wb = openWorkbook(srcFile)
        wbSheet = getSheet(wb, sheetName)

        lstData.each { rowData ->
            wbRow = getRow(wbSheet, rowData.rowNum)
            def items = rowData.rowData
            items.each { item -> setCell(item.colName, item.colValue) }
        }
        saveWorkbook(wb, srcFile)
    }


    /**
     * Open workbook adopting multiple Excel versions
     */
    private def openWorkbook(String filePath) {
        FileInputStream wbFIS
        Workbook wb
        File wbFile = new File(filePath)
        if (wbFile.exists() && wbFile.canRead()) {
            try {
                wbFIS = new FileInputStream(wbFile)
                wb = WorkbookFactory.create(wbFIS)
                return wb
            } catch (e) {
                log.info("Can not open file: " + filePath)
            } finally {
                if (wbFIS != null) {
                    try {
                        wbFIS.close()
                    } catch (ef) {
                        log.info("Can not close file: " + filePath)
                    }
                }
            }
        } else {
            log.info("File does not exist: " + filePath)
        }
    }


    /**
     * Check Sheet-Name is existed
     */
    private def isSheet(Workbook wb, String sheetName) {
        return (wb.getSheetIndex(sheetName) >= 0);
    }


    /**
     * Get target sheet by name
     */
    private Sheet getSheet(Workbook wb, String sheetName) {
        if (!isSheet(wb, sheetName)) {
            throw new Exception("Sheet ${sheetName} does not exist!")
        }
        return wb.getSheet(sheetName)
    }


    /**
     * Get row data of target sheet
     */
    private Row getRow(Sheet sheet, def rowNum) {
        def row = sheet.getRow(rowNum)
        if (null == row) {
            row = sheet.createRow(rowNum)
        }
        return row
    }


    /**
     * Get cell value by column index
     */
    private def getCellValue(Row row, int idxCol) {
        try {
            def cell = row.getCell(idxCol)
            def celType = cell.getCellType()
            def celValue
            switch (celType) {
                case CellType.FORMULA: celValue = cell.getCellFormula(); break
                case CellType.BLANK: celValue = ""; break
                case CellType.NUMERIC: celValue = cell.getNumericCellValue(); break
                default: celValue = cell.getStringCellValue(); break
            }
            return celValue
        } catch (NullPointerException eNul) {
            return ""
        } catch (e) {
            log.info("- Error --- Have problem on getting cell value. Message: ${e.getMessage()}")
        }
    }


    /**
     * Get column index by column name
     */
    private def getColIndexByName(Sheet sheet, def colName, int rowNum = 1) {
        try {
            def headerRow = sheet.getRow(rowNum - 1)
            def lastColIndex = headerRow.getLastCellNum()
            for (int i = 0; i < lastColIndex; i++) {
                if (headerRow.getCell(i).getStringCellValue().trim().equals(colName)) {
                    return i
                }
            }
            throw new Exception("Column ${colName} does not exist!")
        } catch (e) {
            log.info("- Error --- Have problem on finding col-name. Message: ${e.getMessage()}")
        }
    }


    /**
     * Save workbook to excel file
     */
    private def saveWorkbook(Workbook workbook, String filePath) {
        def wbFOS = new FileOutputStream(wbFile)
        try {
            File wbFile = new File(filePath)
            workbook.write(wbFOS)
            wbFOS.flush()
            wbFOS.close()
            log.info("File saved successfully")
        } catch (e) {
            log.info("- Error --- Unable to save file. Please check again.. Message: ${e.getMessage()}")
        } finally {
            if (wbFOS != null) {
                try {
                    wbFOS.close()
                } catch (ef) {
                    log.info("- Error --- File stream can not close. Message: ${ef.getMessage()}")
                }
            }
        }
    }


    /**
     * Set value to cell by column number
     */
    private def setCell(Row row, int colNum, def value) {
        try {
            def cell = row.createCell(colNum)
            cell.setCellValue(value)
        } catch (e) {
            log.info("- Error --- Cell is invalid. Message: ${e.getMessage()}")
        }
    }


    /**
     * Set value to cell by column name
     * after initializing wbSheet & wbRow
     */
    private def setCell(String colName, def value) {
        def colIndex = getColIndexByName(wbSheet, colName)
        setCell(wbRow, colIndex, value)
    }
}
