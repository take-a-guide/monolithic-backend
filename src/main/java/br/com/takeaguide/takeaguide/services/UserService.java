package br.com.takeaguide.takeaguide.services;

import br.com.takeaguide.takeaguide.dtos.account.ChangeUserRequest;
import br.com.takeaguide.takeaguide.dtos.account.CreateUserRequest;
import br.com.takeaguide.takeaguide.dtos.account.UserDto;
import br.com.takeaguide.takeaguide.entities.Account;
import br.com.takeaguide.takeaguide.repositories.mysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto login(String email, String password) {
        Account account = userRepository.login(email, password);
        if (account == null) {
            return null;
        }
        String deletedAtStr = (account.getDeletedAt() != null) ? account.getDeletedAt().toString() : null;
        return new UserDto(account.getCpf(), account.getName(), account.getEmail(), account.getPassword(), account.getUserTypeId(), account.getPhone(), deletedAtStr);
    }

    public BigInteger createUser(CreateUserRequest request) {
        return userRepository.insertUser(request);
    }

    public void updateUser(ChangeUserRequest request) {
        userRepository.updateUser(request.cpf(), request.name(), request.email(), request.password(), request.phone());
    }

    public void removeUser(String cpf) {
        userRepository.deleteByCpf(cpf);
    }

    public List<UserDto> retrieveUserByCpf(String cpf) {
        List<Account> accounts = userRepository.findByCpf(cpf);
        return convertToDto(accounts);
    }

    public List<UserDto> retrieveUserByEmail(String email) {
        List<Account> accounts = userRepository.retrieveUserByEmail(email);
        return convertToDto(accounts);
    }

    public List<UserDto> retrieveUserByName(String name) {
        List<Account> accounts = userRepository.findByName(name);
        return convertToDto(accounts);
    }

    public Integer checkIfUserIsAllowed(String email, String name) {
        return userRepository.checkIfUserIsAllowed(email, name);
    }

    public Integer checkIfUserIsAllowedForUpdate(String email, String name, String cpf) {
        return userRepository.checkIfUserIsAllowedForUpdate(email, name, cpf);
    }

    private List<UserDto> convertToDto(List<Account> accounts) {
        return accounts.stream()
                .filter(account -> account.getDeletedAt() == null)
                .map(account -> new UserDto(
                        account.getCpf(),
                        account.getName(),
                        account.getEmail(),
                        account.getPassword(),
                        account.getUserTypeId(),
                        account.getPhone(),
                        null
                ))
                .collect(Collectors.toList());
    }
}
