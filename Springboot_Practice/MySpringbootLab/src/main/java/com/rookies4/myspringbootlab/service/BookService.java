package com.rookies4.myspringbootlab.service;

import com.rookies4.myspringbootlab.controller.dto.BookDTO;
import com.rookies4.myspringbootlab.entity.Book;
import com.rookies4.myspringbootlab.entity.BookDetail;
import com.rookies4.myspringbootlab.entity.Publisher;
import com.rookies4.myspringbootlab.exception.BusinessException;
import com.rookies4.myspringbootlab.exception.ErrorCode;
import com.rookies4.myspringbootlab.repository.BookDetailRepository;
import com.rookies4.myspringbootlab.repository.BookRepository;
import com.rookies4.myspringbootlab.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final BookDetailRepository bookDetailRepository;
    private final PublisherRepository publisherRepository;

    /** 전체 Book 조회 (출판사 정보 포함) */
    public List<BookDTO.Response> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDTO.Response::fromEntity)
                .toList();
    }

    /** ID로 Book 조회 (BookDetail + Publisher 포함) */
    public BookDTO.Response getBookById(Long id) {
        Book book = bookRepository.findByIdWithAllDetails(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND.formatMessage("Book", "id", id)));
        return BookDTO.Response.fromEntity(book);
    }

    /** ISBN으로 Book 조회 */
    public BookDTO.Response getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND.formatMessage("Book", "isbn", isbn)));
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

    /** 특정 출판사의 모든 도서 조회 */
    public List<BookDTO.Response> getBooksByPublisherId(Long publisherId) {
        if (!publisherRepository.existsById(publisherId)) {
            throw new BusinessException(ErrorCode.RESOURCE_NOT_FOUND.formatMessage("Publisher", "id", publisherId));
        }
        return bookRepository.findByPublisherId(publisherId)
                .stream()
                .map(BookDTO.Response::fromEntity)
                .toList();
    }

    /** Book 생성 */
    @Transactional
    public BookDTO.Response createBook(BookDTO.Request request) {
        // ISBN 중복 체크
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new BusinessException(ErrorCode.ISBN_DUPLICATE.formatMessage(request.getIsbn()));
        }

        // 출판사 존재 여부 체크
        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND.formatMessage("Publisher", "id", request.getPublisherId())));

        // DTO -> Entity
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .price(request.getPrice())
                .publishDate(request.getPublishDate())
                .publisher(publisher)
                .build();

        // BookDetail 처리
        if (request.getDetailRequest() != null) {
            BookDetail detail = BookDetail.builder()
                    .description(request.getDetailRequest().getDescription())
                    .language(request.getDetailRequest().getLanguage())
                    .pageCount(request.getDetailRequest().getPageCount())
                    .publisher(request.getDetailRequest().getPublisher())
                    .coverImageUrl(request.getDetailRequest().getCoverImageUrl())
                    .edition(request.getDetailRequest().getEdition())
                    .book(book)
                    .build();
            book.setBookDetail(detail);
        }

        Book savedBook = bookRepository.save(book);
        return BookDTO.Response.fromEntity(savedBook);
    }

    /** Book 수정 */
    @Transactional
    public BookDTO.Response updateBook(Long id, BookDTO.Request request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND.formatMessage("Book", "id", id)));

        // ISBN 중복 체크 (자기 자신 제외)
        if (request.getIsbn() != null && !book.getIsbn().equals(request.getIsbn()) && bookRepository.existsByIsbn(request.getIsbn())) {
            throw new BusinessException(ErrorCode.ISBN_DUPLICATE.formatMessage(request.getIsbn()));
        }

        // 출판사 변경 시 존재 여부 확인
        if (request.getPublisherId() != null && (book.getPublisher() == null || !book.getPublisher().getId().equals(request.getPublisherId()))) {
            Publisher publisher = publisherRepository.findById(request.getPublisherId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND.formatMessage("Publisher", "id", request.getPublisherId())));
            book.setPublisher(publisher);
        }

        if (request.getTitle() != null) book.setTitle(request.getTitle());
        if (request.getAuthor() != null) book.setAuthor(request.getAuthor());
        if (request.getIsbn() != null) book.setIsbn(request.getIsbn());
        if (request.getPrice() != null) book.setPrice(request.getPrice());
        if (request.getPublishDate() != null) book.setPublishDate(request.getPublishDate());

        // BookDetail 수정/생성
        if (request.getDetailRequest() != null) {
            BookDetail detail = book.getBookDetail();
            if (detail == null) {
                detail = new BookDetail();
                detail.setBook(book);
                book.setBookDetail(detail);
            }
            detail.setDescription(request.getDetailRequest().getDescription());
            detail.setLanguage(request.getDetailRequest().getLanguage());
            detail.setPageCount(request.getDetailRequest().getPageCount());
            detail.setPublisher(request.getDetailRequest().getPublisher());
            detail.setCoverImageUrl(request.getDetailRequest().getCoverImageUrl());
            detail.setEdition(request.getDetailRequest().getEdition());
        }

        return BookDTO.Response.fromEntity(book);
    }

    /** Book 삭제 */
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND.formatMessage("Book", "id", id)));
        bookRepository.delete(book);
    }
}