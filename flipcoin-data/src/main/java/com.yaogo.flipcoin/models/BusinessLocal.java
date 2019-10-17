package com.yaogo.flipcoin.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "businesses")
public class BusinessLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer visitTimes;

    @OneToMany
    private List<ReviewLocal> reviews;
}
