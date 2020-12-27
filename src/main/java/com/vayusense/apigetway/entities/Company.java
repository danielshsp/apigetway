package com.vayusense.apigetway.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Company {
    private String companyName;
    private Address address;
    private List<String> products;
}
