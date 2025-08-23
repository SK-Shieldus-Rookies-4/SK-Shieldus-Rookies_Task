package com.rookies4.myspringbootlab.service;

import com.rookies4.myspringbootlab.controller.dto.BookDTO;
import com.rookies4.myspringbootlab.entity.Book;
import com.rookies4.myspringbootlab.exception.BusinessException;
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

    /** Book 생성 */
    @Transactional
    public BookDTO.BookResponse createBook(BookDTO.BookCreateRequest request) {
        bookRepository.findByIsbn(request.getIsbn())
                .ifPresent(book -> {
                    throw new BusinessException("Book with this ISBN already exists", HttpStatus.CONFLICT);
                });

        // DTO -> Entity
        Book book = request.toEntity();
        Book savedBook = bookRepository.save(book);

        // Entity -> DTO
        return BookDTO.BookResponse.from(savedBook);
    }

    /** Book 수정 */
    @Transactional
    public BookDTO.BookResponse updateBook(Long id, BookDTO.BookUpdateRequest request) {
        Book existBook = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book not found", HttpStatus.NOT_FOUND));

        if (request.getTitle() != null) {
            existBook.setTitle(request.getTitle());
        }
        if (request.getAuthor() != null) {
            existBook.setAuthor(request.getAuthor());
        }
        if (request.getPrice() != null) {
            existBook.setPrice(request.getPrice());
        }
        if (request.getPublishDate() != null) {
            existBook.setPublishDate(request.getPublishDate());
        }

        return BookDTO.BookResponse.from(existBook);
    }

    /** Book 삭제 */
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book not found", HttpStatus.NOT_FOUND));

        bookRepository.delete(book);
    }

    /** 전체 Book 조회 */
    public List<BookDTO.BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDTO.BookResponse::from)
                .toList();
    }

    /** ID로 Book 조회 */
    public BookDTO.BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book not found", HttpStatus.NOT_FOUND));

        return BookDTO.BookResponse.from(book);
    }

    /** ISBN으로 Book 조회 */
    public BookDTO.BookResponse getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("Book with ISBN not found", HttpStatus.NOT_FOUND));

        return BookDTO.BookResponse.from(book);
    }

    /** 저자로 Book 조회 */
    public List<BookDTO.BookResponse> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author)
                .stream()
                .map(BookDTO.BookResponse::from)
                .toList();
    }
}