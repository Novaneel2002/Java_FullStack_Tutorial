package com.onlinebookshop.shop.repository;

import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.onlinebookshop.shop.model.Order;

@Repository
public class OrderRepository {
    private final JdbcTemplate jdbcTemplate;
    
    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private RowMapper<Order> orderRowMapper = (rs, rowNum) -> 
        new Order(rs.getInt("id"), rs.getInt("bookId"), rs.getInt("quantity"), rs.getDate("date"));
    
    // CRUD Operations
    
    // Create -> insert query
    public int save(Order order) {
        String sql = "INSERT INTO orders (quantity, order_date) VALUES (?, ?)";
        return jdbcTemplate.update(sql, order.getQuantity(), order.getDate());
    }
    
    // Read -> get all orders
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders";
        return jdbcTemplate.query(sql, orderRowMapper);
    }
    
    // Read -> get order by ID
    public Order findById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, orderRowMapper, id);
    }
    
    // Update -> update order
    public int update(Order order) {
        String sql = "UPDATE orders SET  quantity = ?, order_date = ? WHERE id = ?";
        return jdbcTemplate.update(sql, order.getQuantity(), order.getDate(), order.getId());
    }
    
    // Delete -> delete order by ID
    public int delete(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
