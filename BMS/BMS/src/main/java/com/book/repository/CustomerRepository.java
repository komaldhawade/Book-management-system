package com.book.repository;


import com.book.entity.Book;
import com.book.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT p FROM Customer p WHERE CONCAT(p.id,' ',p.name,' ',p.address,' ',p.contactInformation) LIKE %?1%")
    List<Book> findByKeyword(String keyword);

}