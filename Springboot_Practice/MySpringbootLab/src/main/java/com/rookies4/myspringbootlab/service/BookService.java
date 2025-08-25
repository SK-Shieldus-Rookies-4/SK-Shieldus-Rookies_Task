package com.rookies4.myspringbootlab.service;

import com.rookies4.myspringbootlab.controller.dto.BookDTO;
import com.rookies4.myspringbootlab.entity.Book;
import com.rookies4.myspringbootlab.entity.BookDetail;
import com.rookies4.myspringbootlab.exception.BusinessException;
import com.rookies4.myspringbootlab.repository.BookDetailRepository;
import com.rookies4.myspringbootlab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final BookDetailRepository bookDetailRepository;


    /** Book 생성 */
    @Transactional
    public BookDTO.Response createBook(BookDTO.Request request) {
        // ISBN 중복 체크
        bookRepository.findByIsbn(request.getIsbn())
                .ifPresent(book -> {
                    throw new BusinessException("Book with this ISBN already exists", HttpStatus.CONFLICT);
                });

        // DTO -> Entity
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .price(request.getPrice())
                .publishDate(request.getPublishDate())
                .build();

        Book savedBook = bookRepository.save(book);

        // Entity -> DTO
        return BookDTO.Response.fromEntity(savedBook);
    }

    /** Book 수정 */
    @Transactional
    public BookDTO.Response updateBook(Long id, BookDTO.Request request) {
        Book existBook = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book not found", HttpStatus.NOT_FOUND));

        if (request.getTitle() != null) existBook.setTitle(request.getTitle());
        if (request.getAuthor() != null) existBook.setAuthor(request.getAuthor());
        if (request.getIsbn() != null) existBook.setIsbn(request.getIsbn());
        if (request.getPrice() != null) existBook.setPrice(request.getPrice());
        if (request.getPublishDate() != null) existBook.setPublishDate(request.getPublishDate());

        return BookDTO.Response.fromEntity(existBook);
    }

    /** Book 삭제 */
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book not found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
    }

    /** 전체 Book 조회 */
    public List<BookDTO.Response> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDTO.Response::fromEntity)
                .toList();
    }

    /** ID로 Book 조회 */
    public BookDTO.Response getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book not found", HttpStatus.NOT_FOUND));
        return BookDTO.Response.fromEntity(book);
    }

    /** ISBN으로 Book 조회 */
    public BookDTO.Response getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("Book with ISBN not found", HttpStatus.NOT_FOUND));
        return BookDTO.Response.fromEntity(book);
    }

    /** 저자로 Book 조회 */
    public List<BookDTO.Response> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author)
                .stream()
                .map(BookDTO.Response::fromEntity)
                .toList();
    }

    /** 제목으로 Book 조회 */
    public List<BookDTO.Response> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(BookDTO.Response::fromEntity)
                .toList();
    }
}