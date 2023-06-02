package com.example.microserviceuab.utils;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;

public class TimeUtils {
    public static Instant convertDateToUTCSameInstant(Date date) {
        Instant instant = date.toInstant();
        return instant.atOffset(ZoneOffset.systemDefault().getRules().getOffset(instant)).toInstant();
    }
}
