package br.com.takeaguide.takeaguide.domain.repositories;

import br.com.takeaguide.takeaguide.domain.entities.GuideListing;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.takeaguide.takeaguide.infrastructure.mysql.rowmappers.GuideListingRowMapper;

import java.util.List;

@Repository
public class GuideListingRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void save(GuideListing guideListing) {
        String sql = "INSERT INTO guide_listing (id, cpf, title, description, location, tours, categories, created_at, updated_at) " +
                     "VALUES (:id, :cpf, :title, :description, :location, :tours, :categories, :createdAt, :updatedAt)";
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("id", guideListing.getId())
            .addValue("cpf", guideListing.getCpf())
            .addValue("title", guideListing.getTitle())
            .addValue("description", guideListing.getDescription())
            .addValue("location", guideListing.getLocation())
            .addValue("tours", String.join(",", guideListing.getTours()))
            .addValue("categories", String.join(",", guideListing.getCategories()))
            .addValue("createdAt", guideListing.getCreatedAt())
            .addValue("updatedAt", guideListing.getUpdatedAt());
        jdbcTemplate.update(sql, params);
    }

    public GuideListing findById(String id) {
        String sql = "SELECT * FROM guide_listing WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.queryForObject(sql, params, new GuideListingRowMapper());
    }

    public void update(GuideListing guideListing) {
        String sql = "UPDATE guide_listing SET title = :title, description = :description, location = :location, " +
                     "tours = :tours, categories = :categories, updated_at = :updatedAt WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("id", guideListing.getId())
            .addValue("title", guideListing.getTitle())
            .addValue("description", guideListing.getDescription())
            .addValue("location", guideListing.getLocation())
            .addValue("tours", String.join(",", guideListing.getTours()))
            .addValue("categories", String.join(",", guideListing.getCategories()))
            .addValue("updatedAt", guideListing.getUpdatedAt());
        jdbcTemplate.update(sql, params);
    }

    public void delete(String id) {
        String sql = "DELETE FROM guide_listing WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    public List<GuideListing> findAll() {
        String sql = "SELECT * FROM guide_listing";
        return jdbcTemplate.query(sql, new GuideListingRowMapper());
    }

    public List<GuideListing> findFiltered(String searchTerm, String category, String location) {
        String sql = "SELECT * FROM guide_listing WHERE (:searchTerm IS NULL OR title LIKE :searchTerm) " +
                     "AND (:category IS NULL OR FIND_IN_SET(:category, categories)) " +
                     "AND (:location IS NULL OR location LIKE :location)";
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("searchTerm", searchTerm != null ? "%" + searchTerm + "%" : null)
            .addValue("category", category)
            .addValue("location", location != null ? "%" + location + "%" : null);
        return jdbcTemplate.query(sql, params, new GuideListingRowMapper());
    }
}
