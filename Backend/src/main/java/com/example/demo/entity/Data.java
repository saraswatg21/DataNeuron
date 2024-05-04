package com.example.demo.entity;

import jakarta.persistence.*;


@Entity
@lombok.Data
@Table(name = "data")
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data")
    private String  data;

    @Column(name = "updated")
    private Boolean updated;

}
