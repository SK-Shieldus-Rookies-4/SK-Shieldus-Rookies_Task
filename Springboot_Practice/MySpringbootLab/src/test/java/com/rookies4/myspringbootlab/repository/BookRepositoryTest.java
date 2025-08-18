package com.rookies4.myspringbootlab.repository;

import com.rookies4.myspringbootlab.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@DataJpaTest
@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book createBook(String title, String author, String isbn, int price, LocalDate publishDate) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setPrice(price);
        book.setPublishDate(publishDate);
        return book;
    }

    @Test
    @DisplayName("도서 등록 테스트")
    void testCreateBook() {
        Book book1 = createBook(
                "스프링 부트 입문",
                "홍길동",
                "9788956746425",
                30000,
                LocalDate.of(2025, 5, 7)
        );

        Book book2 = createBook(
                "JPA 프로그래밍",
                "박둘리",
                "9788956746432",
                35000,
                LocalDate.of(2025, 4, 30)
        );

        bookRepository.save(book1);
        bookRepository.save(book2);

        Book savedBook = bookRepository.save(book1);

        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("스프링 부트 입문");
        assertThat(savedBook.getAuthor()).isEqualTo("홍길동");
    }


    @Test
    @DisplayName("ISBN으로 도서 조회 테스트")
    void testFindByIsbn() {
        Book book = createBook(
                "JPA 프로그래밍",
                "박둘리",
                "9788956746431",
                35000,
                LocalDate.of(2024, 3, 24)
        );
        bookRepository.save(book);

        Optional<Book> foundBook = bookRepository.findByIsbn("9788956746431");

        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getAuthor()).isEqualTo("박둘리");
    }

    @Test
    @DisplayName("저자명으로 도서 목록 조회 테스트")
    void testFindByAuthor() {
        Book book1 = createBook(
                "스프링 부트 입문",
                "홍길동",
                "9788956746425",
                30000,
                LocalDate.of(2025, 5, 7)
        );

        Book book2 = createBook(
                "JPA 프로그래밍",
                "박둘리",
                "9788956746432",
                35000,
                LocalDate.of(2025, 4, 30)
        );

        bookRepository.save(book1);
        bookRepository.save(book2);

        // ✅ 홍길동으로 검색하면 1권만 나와야 함
        List<Book> booksByHong = bookRepository.findByAuthor("홍길동");
        assertThat(booksByHong).hasSize(1);
        assertThat(booksByHong.get(0).getTitle()).isEqualTo("스프링 부트 입문");

        // ✅ 박둘리로 검색하면 1권만 나와야 함
        List<Book> booksByPark = bookRepository.findByAuthor("박둘리");
        assertThat(booksByPark).hasSize(1);
        assertThat(booksByPark.get(0).getTitle()).isEqualTo("JPA 프로그래밍");
    }

    @Test
    @DisplayName("도서 정보 수정 테스트")
    void testUpdateBook() {
        Book book = createBook(
                "스프링 부트 입문",
                "홍길동",
                "9788956746424",
                30000,
                LocalDate.of(2025, 5, 7)
        );
        Book savedBook = bookRepository.save(book);

        savedBook.setPrice(32000);
        Book updatedBook = bookRepository.save(savedBook);

        assertThat(updatedBook.getPrice()).isEqualTo(32000);
    }

    @Test
    @DisplayName("도서 삭제 테스트")
    void testDeleteBook() {
        Book book = createBook(
                "JPA 프로그래밍",
                "박둘리",
                "9788956746431",
                35000,
                LocalDate.of(2024, 3, 24)
        );
        Book savedBook = bookRepository.save(book);

        bookRepository.delete(savedBook);

        Optional<Book> deletedBook = bookRepository.findByIsbn("9788956746431");
        assertThat(deletedBook).isNotPresent();
    }
}