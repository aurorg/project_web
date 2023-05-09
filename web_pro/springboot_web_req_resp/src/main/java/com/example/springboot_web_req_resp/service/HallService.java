package com.example.springboot_web_req_resp.service;

import com.example.springboot_web_req_resp.dao.impl.Hall;

import java.util.List;

public interface HallService {
    List<Hall> getAllHalls();
    Hall createHall(Hall hall);
    Hall getHallById(Long id);
    Hall updateHall(Long id, Hall hallDetails);
    void deleteHall(Long id);
}