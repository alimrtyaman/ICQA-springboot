package com.yourapp.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryDto {
    private long totalExceptions;
    private long matchedWaves;
    private long missingEndTimeWaves;
}
