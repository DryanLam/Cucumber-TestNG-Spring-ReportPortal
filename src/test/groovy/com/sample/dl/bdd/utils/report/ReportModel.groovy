package com.sample.dl.bdd.utils.report

import groovy.transform.ToString

@ToString
class ReportModel {
    List<String> tags
    List<String> steps
    String status
    String message
    int total
}
