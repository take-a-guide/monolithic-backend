package br.com.takeaguide.takeaguide.infrastructure.mysql.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.takeaguide.takeaguide.dtos.ad.AdDto;

public class AdRowMapper implements RowMapper<AdDto> {

    @Override
    public AdDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AdDto(
            rs.getString("cpf"),
            rs.getString("ad")
        );
    }
}
