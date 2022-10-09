package com.techgen.blog.model;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDTO {
    private Date timeStamp;
    private String message;
    private String details;
}
