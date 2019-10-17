package com.yaogo.flipcoin.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class ReviewLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private Double rating;

    @OneToMany
    private Set<TagLocal> tags;


}
