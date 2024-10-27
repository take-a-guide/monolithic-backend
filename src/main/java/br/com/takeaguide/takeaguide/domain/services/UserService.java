package br.com.takeaguide.takeaguide.domain.services;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import br.com.takeaguide.takeaguide.dtos.user.*;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<ResponseObject> login(LoginRequest request);

    ResponseEntity<ResponseObject> createUser(CreateUserRequest request);

    ResponseEntity<ResponseObject> changeUser(ChangeUserRequest request);

    ResponseEntity<ResponseObject> removeUser(DeleteUserRequest request);

    ResponseEntity<ResponseObject> retrieveUser(RetrieveUserRequest request);
}
