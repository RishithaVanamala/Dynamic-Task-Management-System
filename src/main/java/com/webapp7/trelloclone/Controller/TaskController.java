package com.webapp7.trelloclone.Controller;

import com.webapp7.trelloclone.Model.History;
import com.webapp7.trelloclone.Model.Task;
import com.webapp7.trelloclone.Repository.*;
import com.webapp7.trelloclone.dto.PostAPIResponse;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import com.webapp7.trelloclone.Model.State;
import com.webapp7.trelloclone.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;



import static java.time.temporal.ChronoUnit.*;

@Controller
//The path for all our APIs begins with "/trello." (after Application path)
@RequestMapping(path="/trello")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    HistoryRepository historyRepository;

    // This API is used to create a task and assign it to a specific user.
    // You can include an optional description and comments for the task.
    // The task's initial state is set to "to-do," and the system records the creation time.
    // If the task is created successfully, it returns the task ID along with a status and message
    // using a `PostAPIResponse`.
    @PostMapping(path = "/addTask")
    public @ResponseBody PostAPIResponse createTask(@RequestParam(required = false) Long suid, String description, @RequestParam(required = false) List<String> comments) {

        String name;
        Task task = new Task();
        History history = new History();
        String message;
        String status;

        if (suid != null) {
            task.setSuid(suid);
            history.setSuid(suid);
            name = taskService.searchByName(suid);
            if (name != null) {
                task.setName(name);
                history.setName(name);
                message = "Task created successfully";
                status = "success";
            } else {
                message = "Failed to create task. Invalid suid.";
                status = "failure";
                return new PostAPIResponse(status, message, -1L); // Return a failure response
            }
        } else {
            // Handled the case where suid is null
            message = "Task created successfully, but suid is null";
            status = "success";
        }

        if (comments != null && !comments.isEmpty()) {
            task.setComments(comments);
        }

        task.setTimestamp();
        task.setDescription(description);
        task.setState(State.TODO);

        history.setDescription(description);
        history.setState(State.TODO);

        taskService.save(task);
        history.setTaskID(task.getTaskID());
        historyRepository.save(history);

        return new PostAPIResponse(status, message, task.getTaskID());
    }



    // This API displays all the details of a task, including all of its fields.
    @GetMapping(path = "/allTasks")
    public @ResponseBody Iterable<Task> fetchAllTasks() {
        // This returns a JSON data
        return taskService.findAll();
    }


    // This API displays all the details of a task assigned to particular user, including all of its fields.
    @GetMapping(path = "/userTasks/{suid}")
    public @ResponseBody Iterable<Task> fetchTasksAssignedToUser(@PathVariable Long suid) {
        return taskService.findAllTasksByUser(suid);
    }


    // This API is responsible for deleting a specific task based on its taskID,
    // which was assigned to the user when the task was created.
    // It first checks if a task with the given taskID exists and, upon successful deletion, returns a "success" status.
    @PostMapping(path = "/delTask")
    public @ResponseBody PostAPIResponse deleteTask(@RequestParam Long taskID) {
        PostAPIResponse response = new PostAPIResponse();

        if (taskID.equals(taskService.getSpecificTask(taskID))) {
            taskService.removeCommentById(taskID);
            taskService.removeTaskById(taskID);
            response.setStatus("success");
            response.setMessage("Task deleted successfully.");
        } else {
            response.setStatus("failure");
            response.setMessage("Task not found or deletion failed.");
        }

        return response;
    }


    // This API is designed for modifying an existing task.
    // It allows you to make several changes to the task.
    @PostMapping(path = "/modifyTask")
    public @ResponseBody PostAPIResponse modifyTask(@RequestParam Long taskID, @RequestParam(required = false) String state, @RequestParam Long suid, @RequestParam(required = false, defaultValue = "") String description, @RequestParam(required = false, defaultValue = "") String comments) {

        History history = new History();
        String message = "Task modified successfully.";

        if (state != null) {

            int state_num = taskService.getCurrentState(taskID);
            if (State.valueOf(state) != State.TODO && state_num == State.TODO.getNumVal()) {

                LocalTime currentTimestamp = taskService.retrieveTime(taskID);
                Long timeToComplete = currentTimestamp.until(LocalTime.now(), MINUTES);
                Long previousTimeInTodo = taskService.fetchTimeInTodo(taskID);
                Long finalTimeInTodo;

                if (previousTimeInTodo != null) {
                    finalTimeInTodo = previousTimeInTodo + timeToComplete;
                } else {
                    finalTimeInTodo = timeToComplete;
                }

                taskService.timeInTodo(taskID, finalTimeInTodo);
                taskService.changeTimestamp(taskID, Time.valueOf(LocalTime.now()));


            }

            if (State.valueOf(state) != State.DOING && state_num == State.DOING.getNumVal()) {

                LocalTime currentTimestamp = taskService.retrieveTime(taskID);
                Long timeToComplete = currentTimestamp.until(LocalTime.now(), MINUTES);
                Long previousTimeInDoing = taskService.fetchTimeInDoing(taskID);
                Long finalTimeInDoing;

                if (previousTimeInDoing != null) {
                    finalTimeInDoing = previousTimeInDoing + timeToComplete;
                } else {
                    finalTimeInDoing = timeToComplete;
                }

                taskService.timeInDoing(taskID, finalTimeInDoing);
                taskService.changeTimestamp(taskID, Time.valueOf(LocalTime.now()));

            }

            if (State.valueOf(state) != State.DONE && state_num == State.DONE.getNumVal()) {
                LocalTime currentTimestamp = taskService.retrieveTime(taskID);
                Long timeToComplete = currentTimestamp.until(LocalTime.now(), MINUTES);
                Long previousTimeInDone = taskService.fetchTimeInDone(taskID);
                Long finalTimeInDone;

                if (previousTimeInDone != null) {
                    finalTimeInDone = previousTimeInDone + timeToComplete;
                } else {
                    finalTimeInDone = timeToComplete;
                }

                taskService.timeInDone(taskID, finalTimeInDone);
                taskService.changeTimestamp(taskID, Time.valueOf(LocalTime.now()));
            }

            taskService.updateState(taskID, State.valueOf(state).getNumVal());
            message = "Task modified successfully.";
        }
        if (suid != null) {
            taskService.updateUsername(taskID, suid);

            String name = taskService.searchByName(suid);
            if (name != null) {

                taskService.updateTaskName(taskID, name);
            }
            else {
                return new PostAPIResponse("failure", "Invalid suid. " + message, taskID);
            }
        }
        if (description != null) {
            taskService.updateDescription(taskID, description);

        }

        if (comments != null && !comments.equals("")) {
            taskService.addComment(taskID, comments);

        }

        taskService.recordHistory(taskID);


        return new PostAPIResponse("success", message, taskID);
    }

    // This API is used to retrieve and display the complete history of a task.
    @GetMapping(path = "/task/history")
    public @ResponseBody Iterable<History> fetchHistory() {
        // This returns a JSON data
        return historyRepository.findAll();
    }
}
