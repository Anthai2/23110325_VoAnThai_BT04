package vn.iostar.dao;

import java.util.List;
import vn.iostar.entity.Category;

public interface CategoryDao {
    
    Category findById(int id);

    List<Category> findAll();

    List<Category> findAll(int page, int pageSize);

    List<Category> findByCategoryName(String name);

    List<Category> findByUserId(int userId);

    Category create(Category c);

    Category update(Category c);

    void delete(int id);
}
