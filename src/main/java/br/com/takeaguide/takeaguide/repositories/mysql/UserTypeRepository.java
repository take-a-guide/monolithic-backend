package br.com.takeaguide.takeaguide.repositories.mysql;

import java.math.BigInteger;
import java.util.List;

import javax.sql.DataSource;

import br.com.takeaguide.takeaguide.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.takeaguide.takeaguide.dtos.usertype.UserTypeDto;
import br.com.takeaguide.takeaguide.repositories.mysql.rowmappers.UserTypeRowMapper;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

}
