package com.example.t2004eroadasg.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Roads")
public class Road {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_duong")
    private String name;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private District district;
    @Column(name="ngay_thanh_lap")
    private Date sdate;
    @Column(name="mo_ta")
    private String description;
    @Column(name="trang_thai")
    private String status;

}
