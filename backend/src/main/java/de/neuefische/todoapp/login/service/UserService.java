package de.neuefische.todoapp.login.service;


import de.neuefische.todoapp.login.model.UserDocument;
import de.neuefische.todoapp.login.repo.MongoUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MongoUserRepo mongoUserRepo;

    public Optional<UserDocument> findByEmail(String email) {
        return mongoUserRepo.findByEmail(email);
    }

    public Optional<UserDocument> saveUser(UserDocument user) {
        Optional<UserDocument> oldUser = mongoUserRepo.findByEmail(user.getEmail());
        if(oldUser.equals(Optional.empty())){
            return Optional.of(mongoUserRepo.save(user));
        }
        return Optional.empty();
    }
}
