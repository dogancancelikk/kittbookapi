package com.eventz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventz.model.ExceptionLog;

@Repository
public interface ExceptionLogRepository extends JpaRepository<ExceptionLog, Long>{

}
