package com.webapp7.trelloclone.Model;

// This enum defines the three possible states in which a task can exist.
public enum State {
    TODO(0),
    DOING(1),
    DONE(2);

    private int value;

    State(int value) {
        this.value = value;
    }

    public int getNumVal() {
        return value;
    }
}
