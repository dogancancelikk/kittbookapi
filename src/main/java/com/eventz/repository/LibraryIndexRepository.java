package com.eventz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventz.model.LibraryIndex;
import com.eventz.model.LibraryIndexId;

public interface LibraryIndexRepository extends JpaRepository<LibraryIndex, LibraryIndexId> {

}
