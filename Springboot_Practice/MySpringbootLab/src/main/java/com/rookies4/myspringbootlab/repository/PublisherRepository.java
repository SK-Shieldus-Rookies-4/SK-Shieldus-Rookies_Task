package com.rookies4.myspringbootlab.repository;

import com.rookies4.myspringbootlab.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Optional<Publisher> findByName(String name);

    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.students WHERE d.id = :id")
    Optional<Publisher> findByIdWithBooks(@Param("id") Long Id);

    boolean existsByName(String name);
}
