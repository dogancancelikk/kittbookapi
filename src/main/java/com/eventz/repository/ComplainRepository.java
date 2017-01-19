package com.eventz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventz.model.Complain;

public interface ComplainRepository extends JpaRepository<Complain, Long>{

}
