package com.example.t2004eroadasg.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Districts")
public class District {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
    private String name;

}
