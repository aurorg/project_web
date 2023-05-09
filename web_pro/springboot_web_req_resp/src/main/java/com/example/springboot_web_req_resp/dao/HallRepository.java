package com.example.springboot_web_req_resp.dao;

import com.example.springboot_web_req_resp.dao.impl.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
}