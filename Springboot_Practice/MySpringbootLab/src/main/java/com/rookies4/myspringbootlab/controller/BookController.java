package com.rookies4.myspringbootlab.controller;

import com.rookies4.myspringbootlab.controller.dto.BookDTO;
import com.rookies4.myspringbootlab.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BookDTO.Response> createBook(
            @RequestBody @Valid BookDTO.Request request) {
        BookDTO.Response response = bookService.createBook(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /** Book 수정 */
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO.Response> updateBook(
            @PathVariable Long id,
            @RequestBody @Valid BookDTO.Request request) {
        BookDTO.Response response = bookService.updateBook(id, request);
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
    public ResponseEntity<List<BookDTO.Response>> getAllBooks() {
        List<BookDTO.Response> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    /** ID로 Book 조회 */
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO.Response> getBookById(@PathVariable Long id) {
        BookDTO.Response book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    /** ISBN으로 Book 조회 */
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO.Response> getBookByIsbn(@PathVariable String isbn) {
        BookDTO.Response book = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    /** 저자로 Book 조회 */
    @GetMapping("/search/author")
    public ResponseEntity<List<BookDTO.Response>> getBooksByAuthor(
            @RequestParam String author) {
        List<BookDTO.Response> books = bookService.getBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }

    /** 제목으로 Book 조회 */
    @GetMapping("/search/title")
    public ResponseEntity<List<BookDTO.Response>> getBooksByTitle(
            @RequestParam String title) {
        List<BookDTO.Response> books = bookService.getBooksByTitle(title);
        return ResponseEntity.ok(books);
    }
}