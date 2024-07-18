package com.project.dto.request.stock.data_table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.models_rework.enums.SKUChangeType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataTableFilterDTO implements Serializable {
    Integer draw;
    List<Column> columns;
    List<Order> order;
    Integer start;
    Integer length;
    Search search;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PUBLIC)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Column implements Serializable {
        String data;
        String name;
        Boolean searchable;
        Boolean orderable;
        Search search;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PUBLIC)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Order implements Serializable {
        Integer column;
        String dir;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PUBLIC)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Search implements Serializable {
        String value;
        Boolean regex;
    }
}
