package br.com.takeaguide.takeaguide.repositories.mysql;

import java.math.BigInteger;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.takeaguide.takeaguide.dtos.usertype.UserTypeDto;
import br.com.takeaguide.takeaguide.repositories.mysql.rowmappers.UserTypeRowMapper;

@Repository
public class UserTypeRepository {

    @Autowired
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BigInteger ChangeUserType(long id, String name){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = String.format("""
            UPDATE 
                user_type
            SET 
                name = '%s'
            WHERE 
                id = :id
        """, name);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("id", id);

        jdbcTemplate.update(sql, map, keyHolder);

        return keyHolder.getKeyAs(BigInteger.class);

    }

    public List<UserTypeDto> retrieveUserTypes(){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = """
            SELECT 
                id,
                name,
            FROM 
                user_type
        """;

        try {

            return jdbcTemplate.query(sql, new UserTypeRowMapper());
            
        } catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public List<UserTypeDto> retrieveUserType(long id){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = """
            SELECT
                id,
                name
            FROM 
                user_type
            WHERE 
                id = :id
        """;

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("id", id);

        try {

            return jdbcTemplate.query(sql, map, new UserTypeRowMapper());
            
        } catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public BigInteger CreateNewUserType(String name){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = String.format("""
            INSERT INTO user_type 
            VALUES ('%s')
        """, name);

        MapSqlParameterSource map = new MapSqlParameterSource();

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, map, keyHolder);

        return keyHolder.getKeyAs(BigInteger.class);

    }
    
}
