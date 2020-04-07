package com.spring.boot.building.blocks.SpringBoot01.Exception;

import java.util.Date;

public class CustomeErrorDetails {
    private Date date;
    private String message;
    private String errorDetails;

    public CustomeErrorDetails(Date date, String message, String errorDetails) {
        this.date = date;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
