package com.vayusense.apigetway.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    private String address;
    private String phone;
    private String country;
    private String city;

}
