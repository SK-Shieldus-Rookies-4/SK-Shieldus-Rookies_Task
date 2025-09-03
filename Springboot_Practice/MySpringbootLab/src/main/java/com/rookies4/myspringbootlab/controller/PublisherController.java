package com.rookies4.myspringbootlab.controller;

import com.rookies4.myspringbootlab.controller.dto.PublisherDTO;
import com.rookies4.myspringbootlab.controller.dto.BookDTO;
import com.rookies4.myspringbootlab.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    /**
     * 1.1 모든 출판사 조회
     */
    @GetMapping
    public ResponseEntity<List<PublisherDTO.SimpleResponse>> getAllPublishers() {
        List<PublisherDTO.SimpleResponse> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    /**
     * 1.2 특정 출판사 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO.Response> getPublisherById(@PathVariable Long id) {
        PublisherDTO.Response publisher = publisherService.getPublisherById(id);
        return ResponseEntity.ok(publisher);
    }

    /**
     * 1.3 출판사 이름으로 조회
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<PublisherDTO.Response> getPublisherByName(@PathVariable String name) {
        PublisherDTO.Response publisher = publisherService.getPublisherByName(name);
        return ResponseEntity.ok(publisher);
    }

    /**
     * 1.4 출판사별 도서 목록 조회
     */
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDTO.Response>> getBooksByPublisher(@PathVariable Long id) {
        List<BookDTO.Response> books = publisherService.getBooksByPublisher(id);
        return ResponseEntity.ok(books);
    }

    /**
     * 1.5 새 출판사 생성
     */
    @PostMapping
    public ResponseEntity<PublisherDTO.Response> createPublisher(@RequestBody PublisherDTO.Request request) {
        PublisherDTO.Response newPublisher = publisherService.createPublisher(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPublisher);
    }

    /**
     * 1.6 출판사 정보 수정
     */
    @PutMapping("/{id}")
    public ResponseEntity<PublisherDTO.Response> updatePublisher(
            @PathVariable Long id,
            @RequestBody PublisherDTO.Request request) {
        PublisherDTO.Response updatedPublisher = publisherService.updatePublisher(id, request);
        return ResponseEntity.ok(updatedPublisher);
    }

    /**
     * 1.7 출판사 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}