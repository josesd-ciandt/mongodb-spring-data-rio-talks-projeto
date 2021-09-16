package br.com.riotalks.mogodb.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormatDateUtils {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static Date formatDate(String date) {
        SimpleDateFormat formato = new SimpleDateFormat(DATE_FORMAT);
        try {
            return Objects.nonNull(date) ? formato.parse(date) : null;
        } catch (ParseException parseException) {
            throw new DateTimeException(String.format("We can't parse date %s to %s", date, DATE_FORMAT));
        }
    }

    public static Date formatDate(Date date) {
        SimpleDateFormat formato = new SimpleDateFormat(DATE_FORMAT);
        return Objects.nonNull(date) ? formatDate(formato.format(date)) : null;
    }

    public static Date formatDate(Instant date) {
        SimpleDateFormat formato = new SimpleDateFormat(DATE_FORMAT);
        Date.from(date);
        return Objects.nonNull(date) ? formatDate(formato.format(date)) : null;
    }





}
