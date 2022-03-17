package de.neuefische.todoapp.login.repo;

import de.neuefische.todoapp.login.model.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoUserRepo extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByEmail (String email);
}
