package com.yourapp.demo.Dto;

import lombok.AllArgsConstructor;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaveResultDto {
        private String waveNo;
        private long exceptions;
        private LocalDateTime endTime;
        private LocalDateTime shelvingTime;
        private Double timeDiffHours;
}

