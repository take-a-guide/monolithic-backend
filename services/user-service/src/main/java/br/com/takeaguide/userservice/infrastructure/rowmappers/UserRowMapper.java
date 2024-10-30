package br.com.takeaguide.userservice.infrastructure.rowmappers;

import br.com.takeaguide.userservice.domain.entities.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        String cpf = rs.getString("cpf");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        int userTypeId = rs.getInt("user_type_id");
        String phone = rs.getString("phone");
        Timestamp deletedAtTimestamp = rs.getTimestamp("deleted_at");
        LocalDateTime deletedAt = (deletedAtTimestamp != null) ? deletedAtTimestamp.toLocalDateTime() : null;

        return new User(cpf, name, email, password, phone, userTypeId, deletedAt);
    }
}
