package com.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class StudentDto {
    @Id
    private int sid;
    private String sname;
    private Date birthday;
    private String phone;
    private int aid;
    private String province;

}
