package com.eventz.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.model.Complain;
import com.eventz.repository.ComplainRepository;

@Service
public class ComplainServiceImpl implements ComplainService {
	
	@Autowired
	ComplainRepository complainRepository;
	
	@Override
	public Collection<Complain> findAll() {
		Collection<Complain> complains = complainRepository.findAll();
		if(complains==null)
			return null;
		return complains;
	}
	
	@Override
	public Complain create(Complain complain){
		return complainRepository.save(complain);
		
	}
}
