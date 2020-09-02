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
        EQUAL_ERROR_MESSAGE("\n*** ERROR: ''{0}'' Equal Assertion. \nExpected: ''{1}''. \nActual: " + "''{2}''."),
        NOT_EQUAL_ERROR_MESSAGE("\n*** ERROR: ''{0}'' Not Equal Assertion. \nExpected: ''{1}''. \nActual: " + "''{2}''."),
        LIST_EQUAL_ERROR_MESSAGE("\n*** ERROR: ''{0}'' List Equal Assertion.\nExpected: ''{1}''.\nGot     : ''{2}''.\nMissing elements: ''{3}''.\nExtra elements  : ''{4}''"),
        LIST_CONTAIN_ERROR_MESSAGE("\n*** ERROR: ''{0}'' List Contain Assertion.\nExpected:\n {1}.\nGot:\n {2}.\nMissing elements: {3}"),
        LIST_NOT_CONTAIN_ERROR_MESSAGE("*** ERROR: ''{0}'' List Not Contain Assertion.\nExpected:\n {1}.\nGot:\n {2}.\nCommon elements: {3}"),
        LIST_NOT_SORTED("*** ERROR: ''{0}'' List Not Sorted Assertion.\nExpected order: {1}.\nGot:\n {2}.");

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
