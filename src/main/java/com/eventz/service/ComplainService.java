package com.eventz.service;

import java.util.Collection;

import com.eventz.model.Complain;


public interface ComplainService {
	
	Collection<Complain> findAll();

	Complain create(Complain complain);

}
