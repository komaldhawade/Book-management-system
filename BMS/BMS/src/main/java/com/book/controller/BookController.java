package com.book.controller;


import com.book.request.BookRequest;
import com.book.response.BookResponse;
import com.book.service.BookService;
import com.book.utility.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBooks(@Valid @RequestBody BookRequest bookRequest) {
        BookResponse createBook = this.bookService.createBook(bookRequest);
        return new ResponseEntity<BookResponse>(createBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<BookResponse> books = this.bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getBookByBookId(@RequestParam(value = "bookId", required = false) Long bookId) {
         BookResponse bookById = this.bookService.getBookByBookId(bookId);
        //List<BookResponse> books = this.bookService.getAllBooks();

        return new ResponseEntity<>(bookById, HttpStatus.OK);

    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponse> updateBook(@Valid @PathVariable Long bookId, @RequestBody BookRequest bookRequest) {
        BookResponse updatedBook = this.bookService.upateBook(bookRequest, bookId);
        return new ResponseEntity<BookResponse>(updatedBook, HttpStatus.OK);
    }

   @GetMapping("/search/{keyword}")
    public ResponseEntity<?> getBookByKeyword(@PathVariable String keyword) {
        List<BookResponse> serchedbook = this.bookService.getBookByKeyword(keyword);
        return new ResponseEntity<>((!serchedbook.isEmpty()) ? serchedbook
                : new ApiResponse("Book not found with keyword: " + keyword, true), HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long bookId) {
        this.bookService.deleteBook(bookId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Book Deleted Successfully", true), HttpStatus.OK);
    }
}
