package com.rookies4.myspringbootlab.controller;

import com.rookies4.myspringbootlab.entity.Book;
import com.rookies4.myspringbootlab.exception.BusinessException;
import com.rookies4.myspringbootlab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookRestController {

    private final BookRepository bookRepository;

    // POST /api/books : 새 도서 등록
    @PostMapping
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // GET /api/books : 모든 도서 조회
    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    // GET /api/books/{id} : ID로 특정 도서 조회
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
    }

    // GET /api/books/isbn/{isbn} : ISBN으로 도서 조회
    @GetMapping("/isbn/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
    }

    // PUT /api/books/{id} : 도서 정보 수정
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetail) {
        Book existBook = getExistBook(id);

        existBook.setTitle(bookDetail.getTitle());
        existBook.setAuthor(bookDetail.getAuthor());
        existBook.setIsbn(bookDetail.getIsbn());
        existBook.setPrice(bookDetail.getPrice());
        existBook.setPublishDate(bookDetail.getPublishDate());

        return bookRepository.save(existBook);
    }

    private Book getExistBook(Long id) {
        Book existBook = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        return existBook;
    }

    // DELETE /api/books/{id} : 도서 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        Book existBook = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));

        bookRepository.delete(existBook);
        return ResponseEntity.ok("Book이 삭제되었습니다.");
    }
}