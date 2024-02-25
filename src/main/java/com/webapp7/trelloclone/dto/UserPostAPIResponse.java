package com.webapp7.trelloclone.dto;

public class UserPostAPIResponse {
    private String status;
    private String message;
    private Long suid;

    public UserPostAPIResponse(String status, String message, Long suid) {
        this.status = status;
        this.message = message;
        this.suid = suid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSuid() {
        return suid;
    }

    public void setSuid(Long suid) {
        this.suid = suid;
    }
}
