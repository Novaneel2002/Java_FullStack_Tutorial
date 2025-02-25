package com.onlinebookshop.shop.repository;
import java.util.*;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.onlinebookshop.shop.model.Author;

@Repository
public class AuthorRepository {
	private final JdbcTemplate jdbcTemplate;
	
	public AuthorRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	private RowMapper<Author> authorRowMapper = (rs, rowNum) -> 
				new Author(rs.getInt("id"), rs.getString("name"), rs.getString("country"));
	
	
				
	//CRUD Operation
				
	//Create -> insert query
				
	public int save(Author author) {
		String sql = "INSERT into authors (name,country) VALUES (?,?)";
		return jdbcTemplate.update(sql, author.getName(), author.getCountry());
	}
	
	//Read (Get All Authors)
	public List<Author> findAll() {
		String sql = "Select * from authors";
		return jdbcTemplate.query(sql, authorRowMapper);
	}
	
	
	//Read (Get Author by ID)
	public Author findById(int id) {
		String sql = "SELECT * FROM AUTHORS WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, authorRowMapper, id);
	}
	
	//Update Author
	public int update(Author author) {
		String sql = "Update authors set name = ?, country = ? where id = ?";
		return jdbcTemplate.update(sql, author.getName(), author.getCountry(),author.getId());
	}
	
	
	//Delete Author
	public int delete(int id) {
		String sql = "DELETE from authors where id = ?";
		return jdbcTemplate.update(sql,id);
	}
	
				

}
