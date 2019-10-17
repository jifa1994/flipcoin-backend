package com.yaogo.flipcoin.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "tags")
public class TagLocal {

    @Id
    @GeneratedValue
    private Long id;

    private String description;
}
