package com.demo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplicationDTO {

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Timestamp appliedAt;
    private int status;
    private Integer jobId;
    private String jobTitle;
    private Integer seekerId;
    private String seekerName;
    private String avatar;
    private String address;
    private String phone;
    private String workType;
    private String experience;
    private String salary;
    private String companyName;
}
