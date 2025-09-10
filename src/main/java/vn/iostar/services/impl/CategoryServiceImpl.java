package vn.iostar.services.impl;

import java.util.List;

import vn.iostar.dao.CategoryDao;
import vn.iostar.dao.impl.CategoryDaoImpl;
import vn.iostar.entity.Category;
import vn.iostar.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {

    private final CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void insert(Category category) {
        categoryDao.create(category);   // hoặc categoryDao.insert(...) nếu DAO bạn đặt tên insert
    }

    @Override
    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public void deleteById(int id) {
        categoryDao.delete(id);
    }

    @Override
    public List<Category> findByUserId(int id) {
        return categoryDao.findByUserId(id);
    }
}
