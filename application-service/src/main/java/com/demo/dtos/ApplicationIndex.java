package com.demo.dtos;

import com.demo.entities.Application;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "application") // ✅ Đúng chuẩn để Elasticsearch nhận diện
public class ApplicationIndex {
    @Id
    private Integer id;

    @Field(type = FieldType.Date)
    private Date appliedAt;

    @Field(type = FieldType.Integer)
    private int status;

    @Field(type = FieldType.Integer)
    private Integer jobId;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String jobTitle;

    @Field(type = FieldType.Integer)
    private Integer seekerId;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String seekerName;

    @Field(type = FieldType.Text)
    private String avatar;

    @Field(type = FieldType.Text)
    private String address;

    @Field(type = FieldType.Text)
    private String phone;

    @Override
    public String toString() {
        return "ApplicationIndex{" +
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

    public ApplicationIndex(Application application) {
        this.id = application.getId();
        this.appliedAt = application.getAppliedAt();
        this.status = application.getStatus();
        this.jobId = application.getJob().getId();
        this.jobTitle = application.getJob().getTitle().toLowerCase();
        this.seekerId = application.getSeeker().getId();
        this.seekerName = application.getSeeker().getFullName().toLowerCase();
        this.avatar = application.getSeeker().getAvatar();
        this.address = application.getSeeker().getAddress();
        this.phone = application.getSeeker().getPhone();
    }
}
