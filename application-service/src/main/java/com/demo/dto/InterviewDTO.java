package com.demo.dto;

import com.demo.entities.Application;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDTO {
    private Integer id;
    private Integer applicationId;
    private Date scheduledAt;
    private String interviewLink;
    private int status;
    private String jobTitle;
    private String seekerName;
    private String employeeName;
}
