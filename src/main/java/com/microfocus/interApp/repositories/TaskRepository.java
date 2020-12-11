package com.microfocus.interApp.repositories;

import com.microfocus.interApp.domain.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface TaskRepository extends CrudRepository<Task,Long> {


    //@Query("SELECT task FROM Task task")// where task.status = task.Status.NEW")
    List<Task> findByStatus(Task.Status status);

}
