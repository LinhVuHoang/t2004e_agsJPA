package com.example.t2004eroadasg.controller;

import com.example.t2004eroadasg.entity.Road;
import com.example.t2004eroadasg.repository.RoadRepository;
import javafx.beans.binding.ObjectBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping(path = "seed/generate/roads")
public class RoadController {
    @Autowired
    RoadRepository roadRepository;
//create
@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Road road){
    Road road1 = road;
    System.out.println(road1.getStatus());
        roadRepository.save(road);
//    if(road1.getStatus() == status){
        return new ResponseEntity<>(road, HttpStatus.CREATED);
//    }else {
//        return new ResponseEntity<>(road, HttpStatus.valueOf("Trạng thái phải là đang tu sửa,đang thi công hoặc đang sử dụng"));
//    }
}
//find all
@RequestMapping(method = RequestMethod.GET)
    public  ResponseEntity<Object> getlist(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "1") int limit)
{
    HashMap<String,Object> response = new HashMap<>();
    response.put("page",page);
    response.put("limit",limit);
    response.put("data",roadRepository.findAll());
    return new ResponseEntity<>(response,HttpStatus.OK);
}
//find by districtname
    @RequestMapping(method = RequestMethod.GET, path = "listroad/{name}")
    public ResponseEntity<Object> findbynamedistrict(@RequestParam(defaultValue = "1") int page
            ,@RequestParam(defaultValue = "10")int limit,@PathVariable String name) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("limit", limit);
        response.put("data", roadRepository.findAllByDistrict_Name(name));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //find by road name
    @RequestMapping(method = RequestMethod.GET, path = "listroadname/{name}")
    public ResponseEntity<Object> findbyname(@RequestParam(defaultValue = "1") int page
            ,@RequestParam(defaultValue = "10")int limit,@PathVariable String name) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("limit", limit);
        response.put("data", roadRepository.findAllByName(name));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //find by id
    @RequestMapping(method = RequestMethod.GET,path = "list/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id){
        Optional<Road> road = roadRepository.findById(id);//kiểm tra xem null ko nếu null trả về obj rỗng
        return  new ResponseEntity<>(road,HttpStatus.OK);
    }
    //update
    @RequestMapping(method= RequestMethod.PUT,path = "{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Road updateroad){
        Optional<Road> road = roadRepository.findById(id);
        if(road.isPresent()){
            Road road1 = road.get();
            road1.setName(updateroad.getName());
            road1.setDistrict(updateroad.getDistrict());
            road1.setDescription(updateroad.getDescription());
            road1.setSdate(updateroad.getSdate());
            road1.setStatus(updateroad.getStatus());
//            if(road1.getStatus() =="Đang sử dụng"||road1.getStatus() =="Đang thi công" ||road1.getStatus()=="Đang tu sửa"){
                roadRepository.save(road1);
                return new ResponseEntity<>(road1,HttpStatus.OK);
//            }else {
//                return new ResponseEntity<>(road, HttpStatus.valueOf("Trạng thái phải là đang tu sửa,đang thi công hoặc đang sử dụng"));
//            }
        }else {
            return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    //delete
    @RequestMapping(method = RequestMethod.DELETE  , path = "{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
            Optional<Road> road = roadRepository.findById(id);
            if(road.isPresent()){
                roadRepository.delete(road.get());
                return new ResponseEntity<>(road,HttpStatus.OK);
            }else {
                return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
    }
}
