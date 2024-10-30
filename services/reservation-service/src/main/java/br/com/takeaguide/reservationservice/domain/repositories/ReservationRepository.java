package br.com.takeaguide.reservationservice.domain.repositories;

import br.com.takeaguide.reservationservice.domain.entities.Reservation;
import br.com.takeaguide.reservationservice.infrastructure.rowmappers.ReservationRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class ReservationRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void save(Reservation reservation) {
        String sql = "INSERT INTO reservation (id, guide_cpf, user_cpf, reservation_date, tour_name, status, created_at, updated_at) " +
                     "VALUES (:id, :guideCpf, :userCpf, :reservationDate, :tourName, :status, :createdAt, :updatedAt)";
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("id", reservation.getId())
            .addValue("guideCpf", reservation.getGuideCpf())
            .addValue("userCpf", reservation.getUserCpf())
            .addValue("reservationDate", reservation.getReservationDate())
            .addValue("tourName", reservation.getTourName())
            .addValue("status", reservation.getStatus())
            .addValue("createdAt", reservation.getCreatedAt())
            .addValue("updatedAt", reservation.getUpdatedAt());
        jdbcTemplate.update(sql, params);
    }

    public Reservation findById(String id) {
        String sql = "SELECT * FROM reservation WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.queryForObject(sql, params, new ReservationRowMapper());
    }

    public List<Reservation> findByUserCpf(String userCpf) {
        String sql = "SELECT * FROM reservation WHERE user_cpf = :userCpf";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("userCpf", userCpf);
        return jdbcTemplate.query(sql, params, new ReservationRowMapper());
    }

    public List<Reservation> findByGuideCpf(String guideCpf) {
        String sql = "SELECT * FROM reservation WHERE guide_cpf = :guideCpf";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("guideCpf", guideCpf);
        return jdbcTemplate.query(sql, params, new ReservationRowMapper());
    }

    public void update(Reservation reservation) {
        String sql = "UPDATE reservation SET guide_cpf = :guideCpf, user_cpf = :userCpf, reservation_date = :reservationDate, " +
                     "tour_name = :tourName, status = :status, updated_at = :updatedAt WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("id", reservation.getId())
            .addValue("guideCpf", reservation.getGuideCpf())
            .addValue("userCpf", reservation.getUserCpf())
            .addValue("reservationDate", reservation.getReservationDate())
            .addValue("tourName", reservation.getTourName())
            .addValue("status", reservation.getStatus())
            .addValue("updatedAt", reservation.getUpdatedAt());
        jdbcTemplate.update(sql, params);
    }

    public void updateStatus(String id, String status) {
        String sql = "UPDATE reservation SET status = :status, updated_at = NOW() WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("id", id)
            .addValue("status", status);
        jdbcTemplate.update(sql, params);
    }

    public List<Reservation> findAll() {
        String sql = "SELECT * FROM reservation";
        return jdbcTemplate.query(sql, new ReservationRowMapper());
    }

    public void delete(String id) {
        String sql = "DELETE FROM reservation WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        jdbcTemplate.update(sql, params);
    }
}
