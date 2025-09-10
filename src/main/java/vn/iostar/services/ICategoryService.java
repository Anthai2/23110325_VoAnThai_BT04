package vn.iostar.services;

import vn.iostar.entity.Category;

import java.util.List;

public interface ICategoryService {
    void insert(Category category);

    Category findById(int id);

    void update(Category category);

    List<Category> findAll();

    void deleteById(int id);

    List<Category> findByUserId(int id);
    
}