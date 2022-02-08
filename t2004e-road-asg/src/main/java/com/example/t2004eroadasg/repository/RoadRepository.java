package com.example.t2004eroadasg.repository;

import com.example.t2004eroadasg.entity.Road;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadRepository extends JpaRepository<Road,Integer> {
    List<Road> findAllById(int id);
    List<Road> findAllByDistrict_Name(String name);
    List<Road> findAllByName(String name);
    List<Road> findAllByDistrict_Id(int id);
}
