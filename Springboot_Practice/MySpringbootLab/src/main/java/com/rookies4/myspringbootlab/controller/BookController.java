package com.rookies4.myspringbootlab.controller;

import com.rookies4.myspringbootlab.controller.dto.BookDTO;
import com.rookies4.myspringbootlab.entity.Book;
import com.rookies4.myspringbootlab.exception.BusinessException;
import com.rookies4.myspringbootlab.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    /** Book 생성 */
    @PostMapping
    public ResponseEntity<BookDTO.BookResponse> createBook(
            @RequestBody @Valid BookDTO.BookCreateRequest request) {
        BookDTO.BookResponse response = bookService.createBook(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /** Book 수정 */
    @PatchMapping("/{id}")
    public ResponseEntity<BookDTO.BookResponse> updateBook(
            @PathVariable Long id,
            @RequestBody @Valid BookDTO.BookUpdateRequest request) {

        BookDTO.BookResponse response = bookService.updateBook(id, request);
        return ResponseEntity.ok(response);
    }

    /** Book 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book이 삭제되었습니다.");
    }

    /** 전체 Book 조회 */
    @GetMapping
    public ResponseEntity<List<BookDTO.BookResponse>> getAllBooks() {
        List<BookDTO.BookResponse> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    /** ID로 Book 조회 */
    @GetMapping("/id/{id}")
    public ResponseEntity<BookDTO.BookResponse> getBookById(@PathVariable Long id) {
        BookDTO.BookResponse book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    /** ISBN으로 Book 조회 */
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO.BookResponse> getBookByIsbn(@PathVariable String isbn) {
        BookDTO.BookResponse book = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    /** 저자로 Book 조회 */
    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookDTO.BookResponse>> getBooksByAuthor(@PathVariable String author) {
        List<BookDTO.BookResponse> books = bookService.getBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }
}