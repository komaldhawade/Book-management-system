package com.book.service.Implementation;

import com.book.entity.Book;
import com.book.repository.BookRepository;
import com.book.request.BookRequest;
import com.book.response.BookResponse;
import com.book.service.BookService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookServiceImplementation implements BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookResponse createBook(@Valid BookRequest bookRequest) {
        Book book = this.modelMapper.map(bookRequest, Book.class);
        return this.modelMapper.map(this.bookRepository.save(book), BookResponse.class);
    }


    @Override
    public List<BookResponse> getAllBooks() {
        return this.bookRepository.findAll().stream().map(e -> this.modelMapper.map(e, BookResponse.class)).collect(Collectors.toList());
    }

    @Override
    public BookResponse getBookByBookId(Long bookId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book with id %d not found", bookId)));
        return this.modelMapper.map(book, BookResponse.class);

    }

    @Override
    public BookResponse upateBook(BookRequest bookRequest, @Valid Long bookId) {
        BookResponse bookResponse = this.modelMapper.map(this.bookRepository.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book with id %d not found", bookId))), BookResponse.class);
        bookResponse.setTitle(bookRequest.getTitle());
        bookResponse.setGenre(bookRequest.getGenre());
        bookResponse.setAuthor(bookRequest.getAuthor());
        bookResponse.setPublicationYear(bookRequest.getPublicationYear());
        bookResponse.setAvailability(bookRequest.isAvailability());
        return this.modelMapper.map(this.bookRepository.save(this.modelMapper.map(bookResponse, Book.class)), BookResponse.class);

    }

    @Override
    public List<BookResponse> getBookByKeyword(String keyword) {
        return this.bookRepository.findByKeyword(keyword).stream().map(source -> this.modelMapper.map(source, BookResponse.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteBook(Long bookId) {
        this.bookRepository.delete(this.modelMapper.map(this.getBookByBookId(bookId), Book.class));

    }

}
