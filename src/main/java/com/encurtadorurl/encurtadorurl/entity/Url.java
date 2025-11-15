package com.encurtadorurl.encurtadorurl.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "UrlApp")
@Getter
@Setter
@RequiredArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String urlOriginal;
    private String  urlShortened;
    private LocalDate expirationDate;
    private LocalDate createdAt;
}
