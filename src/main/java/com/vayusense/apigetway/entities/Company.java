package com.vayusense.apigetway.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Company {
    private String name;
    private Address address;
    private List<String> product;
}
