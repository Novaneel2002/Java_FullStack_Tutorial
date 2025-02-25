package com.onlinebookshop.shop.repository;

import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.onlinebookshop.shop.model.Book;

@Repository
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;
    
    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private RowMapper<Book> bookRowMapper = (rs, rowNum) -> 
        new Book(rs.getInt("id"), rs.getInt("author_id"), rs.getString("title"), rs.getDouble("price"));
    
    // CRUD Operations
    
    // Create -> insert query
    public int save(Book book) {
        String sql = "INSERT INTO books (author_id, title, price) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, book.getAuthor_id(), book.getTitle(), book.getPrice());
    }
    
    // Read -> get all books
    public List<Book> findAll() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, bookRowMapper);
    }
    
    // Read -> get book by ID
    public Book findById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, bookRowMapper, id);
    }
    
    // Update -> update book
    public int update(Book book) {
        String sql = "UPDATE books SET author_id = ?, title = ?, price = ? WHERE id = ?";
        return jdbcTemplate.update(sql, book.getAuthor_id(), book.getTitle(), book.getPrice(), book.getId());
    }
    
    // Delete -> delete book by ID
    public int delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
