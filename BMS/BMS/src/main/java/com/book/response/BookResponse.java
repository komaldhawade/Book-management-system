package com.book.response;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
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
