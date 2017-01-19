package com.eventz.service;

import java.util.Collection;

import com.eventz.model.Category;

public interface CategoryService {

	Collection<Category> getStoryCategories(Long id);

	Collection<Category> findAll();

	Category findOne(Long id);

	Category create(Category category);

	Category update(Category category);

	void delete(Long id);

}
