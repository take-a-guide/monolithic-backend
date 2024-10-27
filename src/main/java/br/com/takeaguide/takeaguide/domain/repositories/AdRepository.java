package br.com.takeaguide.takeaguide.domain.repositories;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.takeaguide.takeaguide.dtos.ad.AdDto;
import br.com.takeaguide.takeaguide.infrastructure.mysql.rowmappers.AdDtoRowmappers;

@Repository
public class AdRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AdRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public String createAd(String cpf, String image) {
        String sql = """
            INSERT INTO ads(cpf, ad)
            VALUES(:cpf, :image)
        """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("cpf", cpf);
        params.addValue("image", image);

        jdbcTemplate.update(sql, params);
        return cpf; 
    }

    public void changeAd(String cpf, String adContent) {
        String sql = """
            UPDATE ads 
            SET ad = :ad
            WHERE cpf = :cpf
        """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ad", adContent);
        params.addValue("cpf", cpf);

        jdbcTemplate.update(sql, params);
    }

    public void removeAd(String cpf) {
        String sql = """
            UPDATE ads 
            SET deleted = UTC_TIMESTAMP()
            WHERE cpf = :cpf
        """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("cpf", cpf);

        jdbcTemplate.update(sql, params);
    }

    public List<AdDto> retrieveAdsByCpf(String cpf) {
        String sql = """
            SELECT
                cpf,
                ad
            FROM
                ads
            WHERE
                cpf = :cpf AND deleted IS NULL
        """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("cpf", cpf);

        try {
            return jdbcTemplate.query(sql, params, new AdDtoRowmappers());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
