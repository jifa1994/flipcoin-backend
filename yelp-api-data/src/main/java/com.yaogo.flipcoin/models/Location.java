package com.yaogo.flipcoin.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {

    private String id;

    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String zipCode;
    private String country;
    private String state;
    private String[] displayAddress;
}
