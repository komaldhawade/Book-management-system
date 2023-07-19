package com.book.repository;

import com.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT p FROM Book p WHERE CONCAT(p.id,' ',p.author,' ',p.availability,' ',p.genre,' ',p.isbn, p.publicationYear, p.title) LIKE %?1%")
    List<Book> findByKeyword(String keyword);


}
