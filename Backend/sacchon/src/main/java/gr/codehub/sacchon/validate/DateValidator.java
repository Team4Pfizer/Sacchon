package gr.codehub.sacchon.validate;

import gr.codehub.sacchon.exception.BadRequestException;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {



    public static LocalDate validateDate(String date,Clock clock)throws BadRequestException{
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate localDate=LocalDate.parse(date, dateFormatter);
            if (localDate.isAfter(LocalDate.now(clock))){
                throw new BadRequestException("You can't search for future dates, Input date: "+date);
            }else{
                return localDate;
            }
        } catch (DateTimeParseException e) {
            throw new BadRequestException("You did not provide a date. Input : "+date);
        }
    }

}
