package com.rookies4.myspringbootlab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "publishers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private LocalDate establishedDate;

    @Column(unique = true, nullable = false)
    private List<Book> books;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public void addbook(Book book) {

    }

    public void removeBook(Book book) {

    }
}
