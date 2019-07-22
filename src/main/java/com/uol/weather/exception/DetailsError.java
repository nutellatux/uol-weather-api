package com.uol.weather.exception;

import lombok.Data;

public @Data
class DetailsError {

    private String title;

    private Long status;

    private Long timestamp;

    private String messageDev;
}