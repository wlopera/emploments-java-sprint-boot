package com.wlopera.employments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wlopera.employments.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
