package com.testtask.exchangerate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.testtask.exchangerate.client.GetOriginalFromImagesList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gif {

    private String id;

    @JsonProperty(value = "embed_url")
    private String embedUrl;

    private String title;

    @JsonProperty(value = "images")
    @JsonDeserialize(using = GetOriginalFromImagesList.class)
    private Original original;

}
