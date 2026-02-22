package com.yourapp.demo.service;

import com.yourapp.demo.Dto.AnalyzeResponse;
import com.yourapp.demo.Dto.SummaryDto;
import com.yourapp.demo.Dto.WaveAgg;
import com.yourapp.demo.Dto.WaveResultDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SecondSortingService {

    private final WaveListReader waveReader = new WaveListReader();
    private final ExceptionListReader exceptionReader = new ExceptionListReader();

    public AnalyzeResponse analyze(MultipartFile waveFile, MultipartFile exceptionFile, String shiftType, String shiftDate) {

        return analyze(waveFile, exceptionFile);
    }

    public AnalyzeResponse analyze(MultipartFile waveFile, MultipartFile exceptionFile) {

        Map<String, LocalDateTime> waveEndTimeMap = waveReader.readWaveEndTimes(waveFile);
        Map<String, WaveAgg> agg = exceptionReader.readSecondSortingAgg(exceptionFile);

        List<WaveResultDto> results = new ArrayList<>();
        long missingEndTime = 0;

        for (var entry : agg.entrySet()) {
            String waveNo = entry.getKey();
            WaveAgg a = entry.getValue();

            LocalDateTime endTime = waveEndTimeMap.get(waveNo);
            LocalDateTime shelvingTime = a.maxShelvingTime();

            Double diffHours = null;
            if (endTime != null && shelvingTime != null) {
                long minutes = Duration.between(endTime, shelvingTime).toMinutes();
                diffHours = minutes / 60.0;
            } else if (endTime == null) {
                missingEndTime++;
            }

            results.add(new WaveResultDto(
                    waveNo,
                    a.count(),
                    endTime,
                    shelvingTime,
                    diffHours
            ));
        }

        results.sort(
                Comparator
                        .comparingLong(WaveResultDto::getExceptions)
                        .reversed()
                        .thenComparing(WaveResultDto::getTimeDiffHours,
                                Comparator.nullsLast(Comparator.reverseOrder()))
        );

        long totalExceptions = agg.values().stream().mapToLong(WaveAgg::count).sum();

        SummaryDto summary = new SummaryDto(
                totalExceptions,
                results.size(),
                missingEndTime
        );

        return new AnalyzeResponse(summary, results);
    }
}