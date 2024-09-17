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

import br.com.takeaguide.takeaguide.dtos.ad.AdDto;
import br.com.takeaguide.takeaguide.repositories.mysql.rowmappers.AdDtoRowmappers;

@Repository
public class AdRepository {

    @Autowired
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    public BigInteger CreateAd(long userId, String image){

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = """
            INSERT INTO ads(user_id, ad)
            VALUES(:userId, :image)
        """;

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("userId", userId);
        map.addValue("image", image);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, map, keyHolder);

        return keyHolder.getKeyAs(BigInteger.class);

    }

	public BigInteger ChangeAd(Long id, String Ad) {

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = String.format("""
            UPDATE ads 
            SET ad = '%s'
        """, Ad);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource map = new MapSqlParameterSource();

        jdbcTemplate.update(sql, map, keyHolder);

        return keyHolder.getKeyAs(BigInteger.class);

	}

	public void removeAd(Long id) {

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = """
            UPDATE ads 
            SET deleted = UTC_TIMESTAMP()
            WHERE id = :id
        """;

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("id", id);

        jdbcTemplate.update(sql, map);

	}

	public List<AdDto> retrieveAdsByUserId(Long userId) {

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        String sql = """
            SELECT
                id,
                user_id,
                ad
            FROM
                ads
            WHERE
                user_id = :userId AND deleted IS NULL
        """;

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("userId", userId);

        try {

            return jdbcTemplate.query(sql, map, new AdDtoRowmappers());
            
        } catch (EmptyResultDataAccessException e) {

            return null;

        }

	}

	public List<AdDto> retrieveAdsById(Long idAd) {

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = """
            SELECT
                id,
                user_id,
                ad
            FROM 
                ads
            WHERE 
                id = :id
                AND deleted IS NULL
        """;

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("id", idAd);

        try {

            return jdbcTemplate.query(sql, map, new AdDtoRowmappers());
            
        } catch (EmptyResultDataAccessException e) {

            return null;

        }

	}
    
}
