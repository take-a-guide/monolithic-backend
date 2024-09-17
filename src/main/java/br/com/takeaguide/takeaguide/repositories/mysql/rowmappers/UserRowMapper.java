package br.com.takeaguide.takeaguide.repositories.mysql.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.takeaguide.takeaguide.dtos.account.UserDto;

public class UserRowMapper implements RowMapper<UserDto> {

    @Override
    public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new UserDto(
            rs.getString("cpf"), 
            rs.getString("name"), 
            rs.getString("email"), 
            rs.getString("password"),
            rs.getInt("user_type_id"),
            rs.getString("phone"), 
            rs.getString("deleted_at")
        );

    }

}