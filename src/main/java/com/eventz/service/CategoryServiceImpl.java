package com.eventz.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.model.Category;
import com.eventz.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Collection<Category> getStoryCategories(Long id) {
		return categoryRepository.getStoryCategories(id);
	}
	
	@Override
	public Collection<Category> findAll() {
		Collection<Category> allCategories = categoryRepository.findAll();
		if(allCategories==null)
			return null;
		return allCategories;
	}

	@Override
	public Category findOne(Long id) {
		Category category = categoryRepository.findOne(id);
		return category;
	}

	@Override
	public Category create(Category category) {
		Category newCategory = categoryRepository.save(category);
		return newCategory;
	}

	@Override
	public Category update(Category category) {
		if(category.getId()==null)
			return null;
		Category updatedCategory = categoryRepository.save(category);
		return updatedCategory;
	}

	@Override
	public void delete(Long id) {
		categoryRepository.delete(id);
	}

}
