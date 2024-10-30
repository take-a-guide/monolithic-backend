package br.com.takeaguide.guidelistingservice.infrastructure.rowmappers;

import br.com.takeaguide.guidelistingservice.domain.entities.GuideListing;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class GuideListingRowMapper implements RowMapper<GuideListing> {

    @Override
    public GuideListing mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GuideListing(
            rs.getString("id"),
            rs.getString("cpf"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getString("location"),
            Arrays.asList(rs.getString("tours").split(",")),
            Arrays.asList(rs.getString("categories").split(",")),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
