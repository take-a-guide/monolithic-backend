package br.com.takeaguide.reservationservice.infrastructure.rowmappers;

import br.com.takeaguide.reservationservice.domain.entities.Reservation;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRowMapper implements RowMapper<Reservation> {

    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Reservation(
            rs.getString("id"),
            rs.getString("guide_cpf"),
            rs.getString("user_cpf"),
            rs.getTimestamp("reservation_date").toLocalDateTime(),
            rs.getString("tour_name"),
            rs.getString("status"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
