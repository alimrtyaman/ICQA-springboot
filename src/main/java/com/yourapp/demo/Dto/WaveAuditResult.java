package com.yourapp.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaveAuditResult {
    private List<String> headers;
    private int columnsCount;

    private long totalRowsMatched;                 // filtreye uyan toplam satır
    private List<Map<String, String>> previewRows; // ilk 200 satır

    private Map<String, Long> statusCounts;
    private Map<String, Long> cancelledByWave;
    private Map<String, Long> completedByWave;

    private Map<Integer, Long> countsByHour;
}
