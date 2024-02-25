package com.webapp7.trelloclone.Model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskID;

    private Long suid;

    @Enumerated(EnumType.ORDINAL)
    private State state;

    private String name;

    private String description;

    private LocalTime timestamp;

    private Long timeInTodo;

    private Long timeInDoing;

    private Long timeInDone;

    private Long CompletionTime;

    @ElementCollection(targetClass = String.class)
    private List<String> comments = new ArrayList<String>();

    @Column(name = "last_state_change_timestamp")
    private LocalDateTime lastStateChangeTimestamp;

    // Getters and Setters

    public LocalDateTime getLastStateChangeTimestamp() {
        return lastStateChangeTimestamp;
    }

    public void setLastStateChangeTimestamp(LocalDateTime lastStateChangeTimestamp) {
        this.lastStateChangeTimestamp = lastStateChangeTimestamp;
    }

    public Long getTimeInTodo() {
        return timeInTodo;
    }

    public void setTimeInTodo(Long timeInTodo) {
        this.timeInTodo = timeInTodo;
    }

    public Long getTimeInDoing() {
        return timeInDoing;
    }

    public void setTimeInDoing(Long timeInDoing) {
        this.timeInDoing = timeInDoing;
    }

    public Long getTimeInDone() {
        return timeInDone;
    }

    public void setTimeInDone(Long timeInDone) {
        this.timeInDone = timeInDone;
    }

    public Long getCompletionTime() {
        return CompletionTime;
    }

    public void setCompletionTime(Long completionTime) {
        CompletionTime = completionTime;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        this.timestamp = LocalTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSuid() {
        return suid;
    }

    public void setSuid(Long suid) {
        this.suid = suid;
    }

    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        if (this.state != state) { // Verify that the state has indeed been altered.
            this.setLastStateChangeTimestamp(LocalDateTime.now()); // Set the timestamp of the state change
        }
        this.state = state;
    }
}
