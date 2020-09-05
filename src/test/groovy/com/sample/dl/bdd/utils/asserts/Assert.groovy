package com.sample.dl.bdd.utils.asserts

import com.sample.dl.bdd.utils.exceptions.TestException
import org.springframework.stereotype.Service

import java.text.MessageFormat

@Service
class Assert {
    /**
     * This enum is to generate exception message
     *
     **/
    enum AssertionMessage {
        EQUAL_ERROR_MESSAGE("\n*** ERROR: Equal Assertion. \nExpected: ''{0}''. \nActual: " + "''{1}''."),
        NOT_EQUAL_ERROR_MESSAGE("\n*** ERROR: Not Equal Assertion. \nExpected: ''{0}''. \nActual: " + "''{1}''."),
        LIST_EQUAL_ERROR_MESSAGE("\n*** ERROR: List Equal Assertion.\nExpected: ''{0}''.\nGot     : ''{1}''.\nMissing elements: ''{2}''.\nExtra elements  : ''{4}''"),
        LIST_CONTAIN_ERROR_MESSAGE("\n*** ERROR: List Contain Assertion.\nExpected:\n {0}.\nGot:\n {1}.\nMissing elements: {2}"),
        LIST_NOT_CONTAIN_ERROR_MESSAGE("*** ERROR:  List Not Contain Assertion.\nExpected:\n {0}.\nGot:\n {1}.\nCommon elements: {2}"),
        LIST_NOT_SORTED("*** ERROR: List Not Sorted Assertion.\nExpected order: {0}.\nGot:\n {1}.");

        private String value;
        AssertionMessage(String value) {
            this.value = value;
        }
        String getValue() {value}
    }

    /**
     * Assert expected result with actual result
     * @param expectedResult
     * @param actualResult
     **/
    static def assertEquals(Object expectedResult, Object actualResult) {
        if (!Objects.equals(expectedResult, actualResult)) {
            throw new TestException(MessageFormat.format(
                    AssertionMessage.EQUAL_ERROR_MESSAGE.getValue(), expectedResult, actualResult))
        }
    }

    /**
     * Assert the result is true boolean
     * @param value boolean
     **/
    static def assertTrue(def value){
        assertEquals(true,value)
    }

    /**
     * Assert two list and check isSort if needed
     * @param stList    List objects
     * @param ndList    List objects
     **/
    static def assertListEquals(List<Object> stList, List<Object> ndList, def isSort = false){
        if(isSort && stList != ndList ||
           !isSort && stList.sort() != ndList.sort()){
            def missing =  new ArrayList<>(stList)
            missing.removeAll(stList);
            def extra =  new ArrayList<>(ndList)
            extra.removeAll(ndList);
            throw new TestException(MessageFormat.format(
                    AssertionMessage.LIST_EQUAL_ERROR_MESSAGE.getValue(), stList, ndList, missing, extra))
        }
    }
}
