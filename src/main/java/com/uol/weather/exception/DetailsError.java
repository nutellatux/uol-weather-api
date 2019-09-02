package com.uol.weather.exception;

import lombok.Data;

public @Data
class DetailsError {

    private String title;

    private Long status;

    private Long timestamp;

    private String messageDev;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessageDev() {
		return messageDev;
	}

	public void setMessageDev(String messageDev) {
		this.messageDev = messageDev;
	}
    
    
}