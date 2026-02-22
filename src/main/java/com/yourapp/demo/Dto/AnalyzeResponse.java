package com.yourapp.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyzeResponse {
    private SummaryDto summary;
    private List<WaveResultDto> waves;
}
