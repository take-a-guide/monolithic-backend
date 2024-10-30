package br.com.takeaguide.userservice.domain.repositories;

import javax.sql.DataSource;

import static br.com.takeaguide.userservice.utils.StatementFormatter.format;

import java.util.List;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import br.com.takeaguide.userservice.domain.entities.User;
import br.com.takeaguide.userservice.dtos.ChangeUserRequest;
import br.com.takeaguide.userservice.dtos.CreateUserRequest;
import br.com.takeaguide.userservice.infrastructure.rowmappers.UserRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Repository
public class UserRepository {

    @Autowired
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    public User login(String email, String password) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        
        String sql = String.format("""
            SELECT 
                cpf,
                name,
                email,
                password,
                user_type_id,
                phone,
                deleted_at
            FROM 
                account
            WHERE 
                email = '%s'
                AND password = '%s'
        """, email, password);

        MapSqlParameterSource map = new MapSqlParameterSource();

        try {
            return jdbcTemplate.queryForObject(sql, map, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean removeUser(String String) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = """
            UPDATE 
                account
            SET
                deleted_at = UTC_TIMESTAMP()
            WHERE 
                cpf = :cpf
        """;

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("cpf", String);

        jdbcTemplate.update(sql, map);
        return false;
    }

    public Integer checkIfUserIsAllowed(String email, String name) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = String.format("""
            SELECT
                COUNT(cpf)
            FROM 
                account
            WHERE
                (
                    email LIKE '%s'
                    OR 
                    name LIKE '%s'
                )
                AND deleted_at IS NULL
        """, email, name);

        MapSqlParameterSource map = new MapSqlParameterSource();

        try {
            return jdbcTemplate.queryForObject(sql, map, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> retrieveUserByName(String name) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = String.format("""
            SELECT 
                cpf,
                name,
                email,
                password,
                user_type_id,
                phone,
                deleted_at
            FROM
                account
            WHERE 
                name LIKE '%s'
                AND deleted_at IS NULL 
        """, ("%" + name + "%"));

        try {
            return jdbcTemplate.query(sql, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> retrieveUserByEmail(String email) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = String.format("""
            SELECT 
                cpf,
                name,
                email,
                password,
                user_type_id,
                phone,
                deleted_at
            FROM
                account
            WHERE 
                email LIKE '%s'
                AND deleted_at IS NULL
        """, ("%" + email + "%"));

        try {
            return jdbcTemplate.query(sql, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> retrieveUserByCpf(String cpf) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = """
            SELECT 
                cpf,
                name,
                email,
                password,
                user_type_id,
                phone,
                deleted_at
            FROM
                account
            WHERE 
                cpf = :cpf
                AND deleted_at IS NULL
        """;

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("cpf", cpf);

        try {
            return jdbcTemplate.query(sql, map, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean updateUser(ChangeUserRequest request) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = String.format("""
            UPDATE 
                account
            SET 
                %s
            WHERE 
                cpf = :cpf;
        """, format(request));

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("cpf", request.cpf());

        
        int rowsAffected = jdbcTemplate.update(sql, map);

        return rowsAffected > 0;
    }

    public String insertUser(CreateUserRequest request) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = String.format("""
            INSERT INTO account(cpf, name, email, password, user_type_id, phone)
            VALUES('%s', '%s', '%s', '%s', :type, '%s');
        """, request.cpf(), request.name(), request.email(), request.password(), request.phone());

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("type", request.type());

        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, map, keyholder);

        return request.cpf();
    }

    
  
}
