package com.sample.dl.bdd.utils.exceptions


class TestException extends RuntimeException {

    TestException(String message) {
        super(message)
    }

    TestException(String message, Throwable cause) {
        super(message, cause)
    }

    TestException(Throwable cause) {
        super(cause)
    }

    TestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
