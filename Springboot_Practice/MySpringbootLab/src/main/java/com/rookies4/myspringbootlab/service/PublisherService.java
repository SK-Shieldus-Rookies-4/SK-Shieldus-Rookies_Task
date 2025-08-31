package com.rookies4.myspringbootlab.service;

import com.rookies4.myspringbootlab.entity.Publisher;
import com.rookies4.myspringbootlab.controller.dto.PublisherDTO;
import com.rookies4.myspringbootlab.exception.BusinessException;
import com.rookies4.myspringbootlab.exception.ErrorCode;
import com.rookies4.myspringbootlab.repository.BookRepository;
import com.rookies4.myspringbootlab.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    /**
     * 모든 출판사 조회
     */
    @Transactional(readOnly = true)
    public List<PublisherDTO.SimpleResponse> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(publisher -> PublisherDTO.SimpleResponse.fromEntityWithCount(
                        publisher,
                        (long) publisher.getBooks().size()
                ))
                .collect(Collectors.toList());
    }

    /**
     * ID로 출판사 조회
     */
    @Transactional(readOnly = true)
    public PublisherDTO.Response getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESOURCE_NOT_FOUND,
                        "Publisher", "id", id
                ));
        return PublisherDTO.Response.fromEntity(publisher);
    }

    /**
     * 이름으로 출판사 조회
     */
    @Transactional(readOnly = true)
    public PublisherDTO.Response getPublisherByName(String name) {
        Publisher publisher = publisherRepository.findByName(name)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESOURCE_NOT_FOUND,
                        "Publisher", "name", name
                ));
        return PublisherDTO.Response.fromEntity(publisher);
    }

    /**
     * 출판사 생성
     */
    public PublisherDTO.Response createPublisher(PublisherDTO.Request request) {
        // 이름 중복 체크
        publisherRepository.findByName(request.getName())
                .ifPresent(existing -> {
                    throw new BusinessException(
                            ErrorCode.PUBLISHER_NAME_DUPLICATE,
                            request.getName()
                    );
                });

        Publisher publisher = Publisher.builder()
                .name(request.getName())
                .establishedDate(request.getEstablishedDate())
                .address(request.getAddress())
                .build();

        return PublisherDTO.Response.fromEntity(publisherRepository.save(publisher));
    }

    /**
     * 출판사 수정
     */
    public PublisherDTO.Response updatePublisher(Long id, PublisherDTO.Request request) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESOURCE_NOT_FOUND,
                        "Publisher", "id", id
                ));

        // 이름 중복 체크 (자기 자신 제외)
        publisherRepository.findByName(request.getName())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new BusinessException(
                            ErrorCode.PUBLISHER_NAME_DUPLICATE,
                            request.getName()
                    );
                });

        publisher.setName(request.getName());
        publisher.setEstablishedDate(request.getEstablishedDate());
        publisher.setAddress(request.getAddress());

        return PublisherDTO.Response.fromEntity(publisherRepository.save(publisher));
    }

    /**
     * 출판사 삭제
     */
    public void deletePublisher(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESOURCE_NOT_FOUND,
                        "Publisher", "id", id
                ));

        long bookCount = publisher.getBooks().size();
        if (bookCount > 0) {
            throw new BusinessException(
                    ErrorCode.PUBLISHER_HAS_BOOKS,
                    id, bookCount
            );
        }

        publisherRepository.delete(publisher);
    }
}