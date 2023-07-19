package com.book.request;



import lombok.*;

@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class BookRequest {


    private Long id;

    private String title;

    private String author;

    private int publicationYear;

    private String isbn;

    private String genre;

    private boolean availability;

    private double price;

    private int quantityInStock;
}

