package com.book.repository;

import com.book.entity.Book;
import com.book.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT p FROM Order p WHERE CONCAT(p.id,' ',p.customer,' ',p.orderDate) LIKE %?1%")
    List<Book> findByKeyword(String keyword);
}