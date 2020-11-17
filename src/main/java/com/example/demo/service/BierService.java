package com.example.demo.service;

import com.example.demo.entity.Bier;

public interface BierService {

    Bier getRandom();

    Bier[] findBier(String text);
}
