package com.book.response;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponse {
    private Long id;

    private String name;

    private String address;

    private String contactInformation;
}
