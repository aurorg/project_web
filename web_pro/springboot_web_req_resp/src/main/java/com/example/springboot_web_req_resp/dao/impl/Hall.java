package com.example.springboot_web_req_resp.dao.impl;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
//@Table(name = "hall")
@TableName(value="hall")
@Getter
@Setter
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotNull
    private Integer seatCount;
}