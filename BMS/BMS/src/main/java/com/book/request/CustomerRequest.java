package com.book.request;

import lombok.*;

@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class CustomerRequest {

    private Long id;

    private String name;
    private String address;
    private String contactInformation;

    // Constructors, getters, and setters
}
