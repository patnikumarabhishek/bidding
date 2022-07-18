package com.example.bid.model;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

// This is used to make Mapping from database via hibernate/ibatis/jpa

@Data
public class PriceModel {
    @CsvBindByPosition(position = 0)
    private long uniqueId;
    @CsvBindByPosition(position = 1)
    private String instrumentName;
    @CsvBindByPosition(position = 2)
    private double bid;
    @CsvBindByPosition(position = 3)
    private double ask;
    @CsvDate(value="dd-MM-yyyy HH:mm:ss:SSS")
    @CsvBindByPosition(position = 4)
    private LocalDateTime timestamp;
}
