package com.demo.dto;

import com.demo.entities.Job;
import com.demo.entities.Seeker;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return "ApplicationDTO{" +
                "id=" + id +
                ", appliedAt=" + appliedAt +
                ", status=" + status +
                ", jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                ", seekerId=" + seekerId +
                ", seekerName='" + seekerName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
