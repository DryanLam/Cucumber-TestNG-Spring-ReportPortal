package com.sample.dl.bdd.utils.common;

import io.cucumber.datatable.DataTable;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Parser {
    public <T> List<T> parseDataTableToList(DataTable dataTable, Class clazz) {
        return new ArrayList(dataTable.asList(clazz));
    }

    public <T> T parseFromJson(String jsonString, Class clazz) {
        return (T) JsonObjectMapper.get().readValue(jsonString, clazz);
    }
}
