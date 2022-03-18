package de.neuefische.todoapp.repo;

import de.neuefische.todoapp.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDosRepo extends MongoRepository<ToDo, String> {


    List<ToDo> findAllByCreatedBy(String email);

    List<ToDo> findAllByCreatedByOrderByStatus(String email);

    Optional<ToDo> findByIdAndCreatedBy(String id, String email);
}
