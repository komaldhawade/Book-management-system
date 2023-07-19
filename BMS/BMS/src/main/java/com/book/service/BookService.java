package com.book.service;

import com.book.request.BookRequest;
import com.book.response.BookResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface BookService {

    BookResponse createBook(@Valid BookRequest bookRequest);

    List<BookResponse> getAllBooks();

    BookResponse getBookByBookId(Long bookId);

    BookResponse upateBook(BookRequest bookRequest, @Valid Long bookId);

    List<BookResponse> getBookByKeyword(String keyword);

    void deleteBook(Long bookId);

}
