package com.biblioteca.saraiva.vendas.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DataUtils {

    public static final String  DATA_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATA_TIME_PATTERN);

    public static String format (LocalDateTime dateTime){
        return dateTime != null ? dateTime.format(DATE_TIME_FORMATTER) : null;
    }

}
