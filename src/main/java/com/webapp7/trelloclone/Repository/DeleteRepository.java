package com.webapp7.trelloclone.Repository;

import com.webapp7.trelloclone.Model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface DeleteRepository extends Repository<Task,Long> {

    @Query(value = "SELECT u.taskid FROM task u WHERE u.taskid=:id",nativeQuery = true) public Long retrieveTaskByID(Long id);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM task_comments t WHERE t.task_taskid=:taskID",nativeQuery = true) public void deleteTaskComments(Long taskID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM task t WHERE t.taskid=:taskID",nativeQuery = true) public void deleteTaskById(Long taskID);
}
