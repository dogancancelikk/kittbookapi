package com.eventz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventz.model.ChapterRead;
import com.eventz.model.ChapterReadId;

@Repository
public interface ChapterReadRepository extends JpaRepository<ChapterRead, ChapterReadId> {

}
