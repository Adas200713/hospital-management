package com.hospital.hospital_management.exception;

public class TimeSlotConflictException extends RuntimeException{
    public TimeSlotConflictException(String message){
        super(message);
    }
}
