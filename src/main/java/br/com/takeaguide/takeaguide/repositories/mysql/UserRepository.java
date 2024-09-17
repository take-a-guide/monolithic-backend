package br.com.takeaguide.takeaguide.repositories.mysql;

import javax.sql.DataSource;

import static br.com.takeaguide.takeaguide.utils.StatementFormatter.format;

import java.math.BigInteger;
import java.util.List;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import br.com.takeaguide.takeaguide.dtos.account.ChangeUserRequest;
import br.com.takeaguide.takeaguide.dtos.account.CreateUserRequest;
import br.com.takeaguide.takeaguide.dtos.account.UserDto;
import br.com.takeaguide.takeaguide.repositories.mysql.rowmappers.userRowmappers;

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

    public UserDto login(String email, String password){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        
        String sql = String.format("""
            SELECT 
                id,
                username,
                email,
                salary,
                points,
                user_type_id,
                items
            FROM 
                account
            WHERE 
                email = '%s'
                AND password = '%s'
        """, email, password);

        MapSqlParameterSource map = new MapSqlParameterSource();

        try {

            return jdbcTemplate.queryForObject(sql, map, new userRowmappers());
            
        } catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public BigInteger updateUserPoints(long pontuation, long id){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = """
            UPDATE
                account
            SET
                points = :pontuation
            WHERE 
                id = :id;
        """;

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("pontuation", pontuation);
        map.addValue("id", id);

        KeyHolder keyholder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, map, keyholder);
    
        return keyholder.getKeyAs(BigInteger.class);

    }

    public void removeUser(long userId){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = """
            UPDATE 
                account
            SET
                deleted = UTC_TIMESTAMP()
            WHERE 
                id = :userId
        """;

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("userId", userId);

        jdbcTemplate.update(sql, map);

    }

    public Integer checkIfUserIsAllowed(String email, String username){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = String.format("""
            SELECT
                COUNT(id)
            FROM 
                account
            WHERE
                (
                    email LIKE '%s'
                    OR 
                    username LIKE '%s'
                )
                AND deleted IS NULL
        """, email, username);

        MapSqlParameterSource map = new MapSqlParameterSource();

        try {
            
            return jdbcTemplate.queryForObject(sql, map, Integer.class);

        } catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public List<UserDto> retrieveUserByUsername(String username){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = String.format("""
            SELECT 
                id,
                username,
                email,
                points,
                salary,
                user_type_id,
                items
            FROM
                account
            WHERE 
                username LIKE '%s'
                AND deleted IS NULL 
        """, ("%" + username + "%"));

        try {

            return jdbcTemplate.query(sql, new userRowmappers());
            
        } catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public List<UserDto> retrieveUserByEmail(String email){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = String.format("""
            SELECT 
                id,
                username,
                email,
                points,
                salary,
                user_type_id,
                items
            FROM
                account
            WHERE 
                email LIKE '%s'
                AND deleted IS NULL
        """, ("%" + email + "%"));

        try {

            return jdbcTemplate.query(sql, new userRowmappers());
            
        } catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public List<UserDto> retrieveUserById(long id){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = """
            SELECT 
                id,
                username,
                email,
                salary,
                points,
                user_type_id,
                items
            FROM
                account
            WHERE 
                id = :id
                AND deleted IS NULL
        """;

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("id", id);

        try {
        
            return jdbcTemplate.query(sql, map, new userRowmappers());
            
        } catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public BigInteger updateUser(ChangeUserRequest request){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = String.format("""
            UPDATE 
                account
            SET 
                %s
            WHERE 
                id = %d;
        """, format(request), request.id());

        MapSqlParameterSource map = new MapSqlParameterSource();

        KeyHolder keyholder = new GeneratedKeyHolder();

        map.addValue("id", request.id());

        jdbcTemplate.update(sql, map, keyholder);

        return keyholder.getKeyAs(BigInteger.class);

    } 

	public BigInteger insertUser(CreateUserRequest request){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = String.format("""
            INSERT INTO account(username, email, password, salary, points, items, user_type_id)
            VALUES('%s', '%s', '%s','%s' ,0, 0, :type);
        """, request.username(), request.email(), request.password() + request.salary());

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("type", request.type());

        KeyHolder keyholder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, map, keyholder);

        return keyholder.getKeyAs(BigInteger.class);

    }

}
