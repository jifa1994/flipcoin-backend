package com.yaogo.flipcoin.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Business {

    private String id;
    private String name;
    private String url;
    private String imageUrl;
    private Integer reviewCount;
    private Double rating;
    private String price;

    private Location location;



}
