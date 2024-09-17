package br.com.takeaguide.takeaguide.repositories.mysql.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.takeaguide.takeaguide.dtos.account.UserDto;

public class userRowmappers implements RowMapper<UserDto>{

	@Override
	public UserDto mapRow(ResultSet rs, int arg1) throws SQLException {

        return new UserDto(
            rs.getLong("id"), 
            rs.getString("username"), 
            rs.getString("email"), 
            rs.getString("salary"),
            rs.getLong("points"), 
            rs.getInt("user_type_id"), 
            rs.getLong("items")
        );

	}
    
}
