package com.sample.dl.bdd.utils.common;

import org.springframework.stereotype.Component;

@Component
public class Parser {
//    public <T> List<T> parseDatatableToList(DataTable dataTable, Class clazz) {
//        return new ArrayList(dataTable.asList(clazz));
//    }

    public <T> T parseFromJson(String jsonString, Class clazz) {
        return (T) JsonObjectMapper.get().readValue(jsonString, clazz);
    }
}
