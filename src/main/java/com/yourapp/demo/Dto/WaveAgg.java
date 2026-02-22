package com.yourapp.demo.Dto;

import java.time.LocalDateTime;

public record WaveAgg(long count, LocalDateTime maxShelvingTime) {}