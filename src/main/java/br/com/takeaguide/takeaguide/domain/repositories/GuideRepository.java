package br.com.takeaguide.takeaguide.domain.repositories;

import br.com.takeaguide.takeaguide.domain.entities.Guide;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import br.com.takeaguide.takeaguide.infrastructure.mysql.rowmappers.GuideRowMapper;

import java.util.List;

@Repository
public class GuideRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void saveGuide(Guide guide) {
        String sql = "INSERT INTO guide (cpf, education, description, location, is_verified, available_dates, tours, categories, created_at, updated_at) " +
                     "VALUES (:cpf, :education, :description, :location, :isVerified, :availableDates, :tours, :categories, :createdAt, :updatedAt)";
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("cpf", guide.getCpf())
            .addValue("education", guide.getEducation())
            .addValue("description", guide.getDescription())
            .addValue("location", guide.getLocation())
            .addValue("isVerified", guide.isVerified())
            .addValue("availableDates", String.join(",", guide.getAvailableDates()))
            .addValue("tours", String.join(",", guide.getTours()))
            .addValue("categories", String.join(",", guide.getCategories()))
            .addValue("createdAt", guide.getCreatedAt())
            .addValue("updatedAt", guide.getUpdatedAt());
        jdbcTemplate.update(sql, params);
    }

    public Guide findGuideByCpf(String cpf) {
        String sql = "SELECT * FROM guide WHERE cpf = :cpf";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("cpf", cpf);
        try {
            return jdbcTemplate.queryForObject(sql, params, new GuideRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Guide> findAllGuides() {
        String sql = "SELECT * FROM guide";
        return jdbcTemplate.query(sql, new GuideRowMapper());
    }

    public void updateGuide(Guide guide) {
        String sql = "UPDATE guide SET education = :education, description = :description, location = :location, is_verified = :isVerified, " +
                     "available_dates = :availableDates, tours = :tours, categories = :categories, updated_at = :updatedAt WHERE cpf = :cpf";
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("cpf", guide.getCpf())
            .addValue("education", guide.getEducation())
            .addValue("description", guide.getDescription())
            .addValue("location", guide.getLocation())
            .addValue("isVerified", guide.isVerified())
            .addValue("availableDates", String.join(",", guide.getAvailableDates()))
            .addValue("tours", String.join(",", guide.getTours()))
            .addValue("categories", String.join(",", guide.getCategories()))
            .addValue("updatedAt", guide.getUpdatedAt());
        jdbcTemplate.update(sql, params);
    }

    public void deleteGuide(String cpf) {
        String sql = "DELETE FROM guide WHERE cpf = :cpf";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("cpf", cpf);
        jdbcTemplate.update(sql, params);
    }
}
