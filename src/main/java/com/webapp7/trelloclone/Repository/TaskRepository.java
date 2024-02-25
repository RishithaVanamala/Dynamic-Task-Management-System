package com.webapp7.trelloclone.Repository;

import com.webapp7.trelloclone.Model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Iterable<Task> findBySuid(Long suid);

}
