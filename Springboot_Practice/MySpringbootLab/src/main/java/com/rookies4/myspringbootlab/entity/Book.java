package com.rookies4.myspringbootlab.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@DynamicUpdate
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String author;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column(unique = true, nullable = false)
    private int price;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate publishDate = LocalDate.now();
}
