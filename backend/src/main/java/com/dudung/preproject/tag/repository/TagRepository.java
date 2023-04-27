package com.dudung.preproject.tag.repository;

import com.dudung.preproject.tag.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Page<Tag> findAllByTagNameContaining(String keyword, Pageable pageable);

}
