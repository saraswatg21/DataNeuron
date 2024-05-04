package com.example.demo.controller;

import com.example.demo.dto.DataDTO;
import com.example.demo.entity.Data;
import com.example.demo.service.AddDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private AddDataService addDataService;

    @PostMapping("/add")
    public ResponseEntity<Data> addData(@RequestBody DataDTO requestData) {
        Data savedData = addDataService.addData(requestData);
        return ResponseEntity.ok(savedData);
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editData(@RequestBody Data requestData) throws IllegalAccessException {
        if (requestData.getId() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "ID cannot be null"));
        }
        try {
            Data savedData = addDataService.update_data(requestData);
            if (savedData.getId() != null) {
                return ResponseEntity.ok(savedData);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "ID not found in db"));
            }
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }

    }

    @GetMapping("/count")
    public Map<String, Integer> getCount() {
        int[] counts = addDataService.getCount();
        return Map.of("addCount", counts[0], "updateCount", counts[1]);
    }
}
