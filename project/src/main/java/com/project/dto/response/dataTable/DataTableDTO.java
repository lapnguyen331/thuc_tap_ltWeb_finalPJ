package com.project.dto.response.dataTable;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class DataTableDTO implements Serializable {
    List<?> data;
    Integer draw;
    Integer recordsTotal;
    Integer recordsFiltered;
}
