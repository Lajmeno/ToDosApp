package de.neuefische.todoapp.repo;

import de.neuefische.todoapp.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDosRepo extends MongoRepository<ToDo, String> {

    List<ToDo> findAllByOrderByStatus();
}
