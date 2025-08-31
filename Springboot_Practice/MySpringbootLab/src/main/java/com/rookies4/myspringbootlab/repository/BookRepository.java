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

    // ISBN으로 검색
    Optional<Book> findByIsbn(String isbn);

    // 저자로 검색 (대소문자 무시)
    List<Book> findByAuthorContainingIgnoreCase(String author);

    // 제목으로 검색 (대소문자 무시)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // ID로 Book과 BookDetail 함께 로드
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookDetail WHERE b.id = :id")
    Optional<Book> findByIdWithBookDetail(@Param("id") Long id);

    // ID로 Book, BookDetail, Publisher 모두 로드
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookDetail LEFT JOIN FETCH b.publisher WHERE b.id = :id")
    Optional<Book> findByIdWithAllDetails(@Param("id") Long id);

    // ISBN으로 Book과 BookDetail 함께 로드
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookDetail WHERE b.isbn = :isbn")
    Optional<Book> findByIsbnWithBookDetail(@Param("isbn") String isbn);

    // 특정 출판사의 모든 도서 조회
    List<Book> findByPublisherId(Long publisherId);

    // 특정 출판사의 도서 수 계산
    long countByPublisherId(Long publisherId);

    // ISBN 중복 확인
    boolean existsByIsbn(String isbn);
}