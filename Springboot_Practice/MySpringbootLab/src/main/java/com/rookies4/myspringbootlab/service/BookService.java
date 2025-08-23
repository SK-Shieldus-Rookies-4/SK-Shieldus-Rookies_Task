package com.rookies4.myspringbootlab.service;

import com.rookies4.myspringbootlab.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

}
