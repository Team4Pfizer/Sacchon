package gr.codehub.sacchon.validate;

import gr.codehub.sacchon.exception.BadRequestException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {
    public static LocalDate validateDate(String date)throws BadRequestException{
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new BadRequestException("You did not provide a date. Input : "+date);
        }



    }
}
