package com.uol.weather.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public @Data
class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String latitude;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String longitude;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ip;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String distance;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String woeid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String min_temp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String max_temp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer user;

}