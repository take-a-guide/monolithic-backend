package br.com.takeaguide.takeaguide.infrastructure.mysql.rowmappers;

import br.com.takeaguide.takeaguide.domain.entities.Guide;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class GuideRowMapper implements RowMapper<Guide> {

    @Override
    public Guide mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Guide(
            rs.getString("cpf"),
            rs.getString("education"),
            rs.getString("description"),
            rs.getString("location"),
            rs.getBoolean("is_verified"),
            Arrays.asList((String[]) rs.getArray("available_dates").getArray()),
            Arrays.asList((String[]) rs.getArray("tours").getArray()),
            Arrays.asList((String[]) rs.getArray("categories").getArray()),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
