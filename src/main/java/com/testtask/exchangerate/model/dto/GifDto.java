package com.testtask.exchangerate.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testtask.exchangerate.model.Gif;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GifDto {

    private List<Gif> data;
}
