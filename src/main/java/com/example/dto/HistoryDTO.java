package com.example.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class HistoryDTO {
    private int ID;
    private Double LAT;
    private Double LNT;
    private String HISTORY_DATE;
}
