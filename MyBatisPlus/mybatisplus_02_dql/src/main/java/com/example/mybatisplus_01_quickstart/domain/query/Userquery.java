package com.example.mybatisplus_01_quickstart.domain.query;

import com.example.mybatisplus_01_quickstart.domain.User;
import lombok.Data;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Data
public class Userquery extends User {
    private Integer age2;



}
