package com.example.demo.repository;

import com.example.demo.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepo extends JpaRepository<Data, Integer> {
    @Query("SELECT COUNT(d) FROM Data d WHERE d.updated = true")
    int findUpdatedDataCount();
}
