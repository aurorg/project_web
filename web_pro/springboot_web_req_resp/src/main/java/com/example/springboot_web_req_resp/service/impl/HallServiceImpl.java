package com.example.springboot_web_req_resp.service.impl;

import com.example.springboot_web_req_resp.dao.HallRepository;
import com.example.springboot_web_req_resp.dao.impl.Hall;
import com.example.springboot_web_req_resp.pojo.ResourceNotFoundException;
import com.example.springboot_web_req_resp.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {
    @Autowired
    private HallRepository hallRepository;

    @Override
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    @Override
    public Hall createHall(Hall hall) {
        return hallRepository.save(hall);
    }

    @Override
    public Hall getHallById(Long id) {
        return hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall", "id", id));
    }

    @Override
    public Hall updateHall(Long id, Hall hallDetails) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall", "id", id));

        hall.setName(hallDetails.getName());
        hall.setSeatCount(hallDetails.getSeatCount());

        return hallRepository.save(hall);
    }

    @Override
    public void deleteHall(Long id) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall", "id", id));

        hallRepository.delete(hall);
    }
}