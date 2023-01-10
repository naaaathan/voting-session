package com.voting.session.utils;

import com.voting.session.exception.DateFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date formatDate(String stringDate){

        Date dateFormatted;

        try {
            dateFormatted = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
        } catch (ParseException e) {
            throw new DateFormatException("Data bad formatted please check if the pattern being sent is dd/MM/yyyy");
        }

        return dateFormatted;

    }
}
