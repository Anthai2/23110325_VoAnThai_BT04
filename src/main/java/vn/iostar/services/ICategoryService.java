package vn.iostar.services;

import vn.iostar.entity.Category;
import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(int id);
    List<Category> findByUserId(int userId);

    boolean insert(Category category); 
    boolean update(Category category); 
    boolean delete(int id);           
}
