package com.rookies4.myspringbootlab.repository;

import com.rookies4.myspringbootlab.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // ISBN 검색
    Optional<Book> findByIsbn(String isbn);

    // 저자로 검색
    List<Book> findByAuthor(String author);

    // 제목으로 검색
    List<Book> findByAuthorContainingIgnoreCase(String title);

    // ISBN 중복 확인
    boolean existsByIsbn(String isbn);

    // ID로 Book과 BookDetail 함께 로드
    @Query("SELECT b FROM Book b JOIN FETCH b.bookDetail WHERE b.id = :id")
    Optional<Book> findByIdWithBookDetail(@Param("id") Long id);

    // ISBN으로 Book과 BookDetail 함께 로드
    @Query("SELECT b FROM Book b JOIN FETCH b.bookDetail WHERE b.isbn = :isbn")
    Optional<Book> findByIsbnWithBookDetail(@Param("isbn") String isbn);


}
