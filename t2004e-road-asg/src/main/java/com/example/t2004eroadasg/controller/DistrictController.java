package com.example.t2004eroadasg.controller;

import com.example.t2004eroadasg.entity.District;
import com.example.t2004eroadasg.entity.Road;
import com.example.t2004eroadasg.repository.DistrictRepository;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "seed/generate/districts")
public class DistrictController {
    @Autowired //tiêm bean và những thằng liên quan bean vào
    DistrictRepository districtRepository;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody District district){
            districtRepository.save(district);
            return new ResponseEntity<>(district, HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList(@RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "10") int limit) {

        HashMap<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("limit", limit);
        response.put("data", districtRepository.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
