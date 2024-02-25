package com.webapp7.trelloclone.Repository;

import com.webapp7.trelloclone.Model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface ModifyRepository extends Repository<Task, Long> {

    // Query for fetching time
    @Query(value = "SELECT timestamp FROM task WHERE taskid=:id", nativeQuery = true)
    LocalTime getTime(Long id);

    // Query for fetching the duration the task was in Todo
    @Query(value = "SELECT time_in_todo FROM task WHERE taskid=:id", nativeQuery = true)
    Long getTimeInTodo(Long id);

    // Query for fetching the duration the task was in Doing
    @Query(value = "SELECT time_in_doing FROM task WHERE taskid=:id", nativeQuery = true)
    Long getTimeInDoing(Long id);

    // Query for fetching the duration the task was in Done
    @Query(value = "SELECT time_in_done FROM task WHERE taskid=:id", nativeQuery = true)
    Long getTimeInDone(Long id);


    // Query for updating the timestamp
    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET timestamp=:time WHERE taskid=:id", nativeQuery = true)
    void modifyTimestamp(Long id, Time time);

    // Query for updating the completion time
    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET completion_time=:time WHERE taskid=:id", nativeQuery = true)
    void setCompletionTime(Long id, Long time);

    // Query for updating the duration the task was in Todo
    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET time_in_todo=:time WHERE taskid=:id", nativeQuery = true)
    void timeInTodo(Long id, Long time);

    // Query for updating the duration of the task in Doing
    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET time_in_doing=:time WHERE taskid=:id", nativeQuery = true)
    void timeInDoing(Long id, Long time);

    // Query for updating the duration of the task in Done
    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET time_in_done=:time WHERE taskid=:id", nativeQuery = true)
    void timeInDone(Long id, Long time);

    // Query for fetching the current state of the task
    @Query(value = "SELECT state FROM task WHERE taskid=:id", nativeQuery = true)
    int retrieveCurrentState(Long id);

    // Query for changing state
    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET state=:state WHERE taskid=:id", nativeQuery = true)
    void modifystate(Long id, int state);

    // Query for changing username
    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET suid=:new_suid WHERE taskid=:id and exists (select suid from user where suid=:new_suid)", nativeQuery = true)
    void modifyusername(Long id, Long new_suid);

    // Query for changing name
    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET name=:name WHERE taskid=:id", nativeQuery = true)
    void modifyname(Long id, String name);

    // Query for changing description
    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET description=:description WHERE taskid=:id", nativeQuery = true)
    void modifydesc(Long id, String description);

    // Query for adding comments
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO task_comments (task_taskid, comments) VALUES (:id, :comments)", nativeQuery = true)
    void attachComment(Long id, String comments);

    //  Query for updating lastStateChangeTimestamp
    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET last_state_change_timestamp=:timestamp WHERE taskid=:id", nativeQuery = true)
    void updateStateChangeTime(Long id, LocalDateTime timestamp);
}

