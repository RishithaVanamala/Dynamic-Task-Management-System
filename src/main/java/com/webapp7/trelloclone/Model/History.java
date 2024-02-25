package com.webapp7.trelloclone.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GenerationType;


@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "num", updatable = false, nullable = false)
    private int num;

    private long taskID;

    private Long suid;

    private State state;

    private String name;

    private String description;

    private Long timeInTodo;

    private Long timeInDoing;

    private Long timeInDone;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
        this.state = state;
    }
}
