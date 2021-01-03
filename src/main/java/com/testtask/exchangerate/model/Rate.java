package com.testtask.exchangerate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {
    private Long timestamp;

    @JsonProperty(value = "base")
    private String baseCurrency;

    private HashMap<String, Double> rates;
}
