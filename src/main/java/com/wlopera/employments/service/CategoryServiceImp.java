package com.wlopera.employments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlopera.employments.model.Category;
import com.wlopera.employments.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements ICategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Override
	public List<Category> getAll() {
		List<Category> categories = categoryRepository.findAll();		
		return categories;
	}

}
