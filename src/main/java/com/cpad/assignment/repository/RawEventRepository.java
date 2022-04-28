package com.cpad.assignment.repository;

import com.cpad.assignment.entity.RawEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface RawEventRepository extends JpaRepository<RawEvent, Integer> {
    RawEvent findTopByOrderByIdDesc();
}
