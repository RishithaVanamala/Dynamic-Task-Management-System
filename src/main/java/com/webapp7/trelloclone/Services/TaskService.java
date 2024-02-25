package com.webapp7.trelloclone.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.webapp7.trelloclone.Model.History;
import com.webapp7.trelloclone.Model.Task;
import com.webapp7.trelloclone.Repository.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeleteRepository del;

    @Autowired
    private ModifyRepository modifyRepository;

    @Autowired
    private HistoryRepository historyRepository;

    public String searchByName(Long suid) {
        return userRepository.fetchUsernameByUserId(suid);
    }

    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    public Iterable<Task> findAllTasksByUser(Long suid) {
        return taskRepository.findBySuid(suid);
    }

    public void save(Task s) {
        taskRepository.save(s);
    }

    public Long getSpecificTask(Long id) {
        return del.retrieveTaskByID(id);
    }

    public void removeCommentById(Long taskID) {
        del.deleteTaskComments(taskID);
    }

    public void removeTaskById(Long taskID) {
        del.deleteTaskById(taskID);
    }

    public LocalTime retrieveTime(Long id) {
        return modifyRepository.getTime(id);
    }

    public Long fetchTimeInTodo(Long id) {
        return modifyRepository.getTimeInTodo(id);
    }

    public Long fetchTimeInDoing(Long id) {
        return modifyRepository.getTimeInDoing(id);
    }

    public Long fetchTimeInDone(Long id) {
        return modifyRepository.getTimeInDone(id);
    }

    public void changeTimestamp(Long id, Time time) {
        modifyRepository.modifyTimestamp(id, time);
    }

    public void addCompletionTime(Long id, Long time) {
        modifyRepository.setCompletionTime(id, time);
    }

    public void timeInTodo(Long id, Long time) {
        modifyRepository.timeInTodo(id, time);
    }

    public void timeInDoing(Long id, Long time) {
        modifyRepository.timeInDoing(id, time);
    }

    public void timeInDone(Long id, Long time) {
        modifyRepository.timeInDone(id, time);
    }

    public void updateState(Long id, int state) {
        // Update the timestamp for the last state change before modifying the state.
        this.updateLastStateChangeTimestamp(id, LocalDateTime.now());
        modifyRepository.modifystate(id, state);

    }

    public void updateUsername(Long id, Long newsuid) {
        modifyRepository.modifyusername(id, newsuid);
    }

    public void updateTaskName(Long id, String name) {
        modifyRepository.modifyname(id, name);
    }

    public int getCurrentState(Long taskid) {
        return modifyRepository.retrieveCurrentState(taskid);
    }

    public void updateDescription(Long id, String desc) {
        modifyRepository.modifydesc(id, desc);
    }

    public void addComment(Long id, String comment) {
        modifyRepository.attachComment(id, comment);
    }



    // Service function to update the timestamp for the most recent state change.
    public void updateLastStateChangeTimestamp(Long id, LocalDateTime timestamp) {
        modifyRepository.updateStateChangeTime(id, timestamp);
    }

    // Auxiliary method to create a history record
    public void recordHistory(Long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            History history = new History();
            history.setTaskID(task.getTaskID());
            history.setSuid(task.getSuid());
            history.setState(task.getState());
            history.setName(task.getName());
            history.setDescription(task.getDescription());
            history.setTimeInTodo(task.getTimeInTodo());
            history.setTimeInDoing(task.getTimeInDoing());
            history.setTimeInDone(task.getTimeInDone());
            historyRepository.save(history);
        }
    }
}
