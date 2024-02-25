package com.webapp7.trelloclone.dto;

public class PostAPIResponse {

    private String status;
    private String message;
    private Long taskID;

    public PostAPIResponse() {
    }

    public PostAPIResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public PostAPIResponse(String status, String message, Long taskID) {
        this.status = status;
        this.message = message;
        this.taskID = taskID;
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

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }
}
