package com.example.springboot_web_req_resp.dao.impl;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hall")
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