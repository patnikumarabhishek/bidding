package com.example.bid.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceData {

    private long uniqueId;
    private String instrumentName;
    private double bid;
    private double ask;
    private LocalDateTime timestamp;
}
