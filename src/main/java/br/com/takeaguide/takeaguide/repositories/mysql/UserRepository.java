package br.com.takeaguide.takeaguide.repositories.mysql;

import br.com.takeaguide.takeaguide.dtos.account.ChangeUserRequest;
import br.com.takeaguide.takeaguide.entities.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.takeaguide.takeaguide.dtos.account.CreateUserRequest;
import br.com.takeaguide.takeaguide.dtos.account.UserDto;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {

    @Query("SELECT l from Account l where l.email = :email and l.password = :password")
    Account login(@Param("email") String email, @Param("password") String password);
    default BigInteger insertUser(CreateUserRequest request) {
        Account account = new Account();
        account.setCpf(request.cpf());
        account.setName(request.name());
        account.setEmail(request.email());
        account.setPassword(request.password());
        account.setUserTypeId(request.type());
        account.setPhone(request.phone());

        account = save(account);

        return new BigInteger(account.getCpf());
    }
    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.name = :name, a.email = :email, a.password = :password, a.phone = :phone WHERE a.cpf = :cpf")
    void updateUser(@Param("cpf") String cpf,
                    @Param("name") String name,
                    @Param("email") String email,
                    @Param("password") String password,
                    @Param("phone") String phone);
    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.deletedAt = UTC_TIMESTAMP() WHERE a.cpf = :cpf")
    void deleteByCpf(@Param("cpf") String cpf);
    List<Account> findByCpf(String cpf);

    @Query("SELECT a FROM Account a WHERE a.email = :email")
    List<Account> retrieveUserByEmail(@Param("email") String email);
    List<Account> findByName(String name);
    @Query("SELECT COUNT(a.cpf) FROM Account a WHERE (a.email LIKE %:email% OR a.name LIKE %:name%) AND a.deletedAt IS NULL")
    Integer checkIfUserIsAllowed(@Param("email") String email, @Param("name") String name);
    @Query("SELECT COUNT(a) FROM Account a WHERE (a.email = :email OR a.name = :name) AND a.cpf <> :cpf AND a.deletedAt IS NULL")
    Integer checkIfUserIsAllowedForUpdate(@Param("email") String email, @Param("name") String name, @Param("cpf") String cpf);
}