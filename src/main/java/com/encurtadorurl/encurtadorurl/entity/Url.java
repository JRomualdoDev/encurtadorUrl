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
    @Column(nullable = false)
    private String urlOriginal;
    @Column(nullable = false, unique = true, length = 10)
    private String urlShort;
    @Column(nullable = false)
    private LocalDate expirationDate;
    @Column(nullable = false)
    private LocalDate createdAt;
}
