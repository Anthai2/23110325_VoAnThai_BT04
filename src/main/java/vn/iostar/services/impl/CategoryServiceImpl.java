package vn.iostar.services.impl;

import java.util.List;
import vn.iostar.dao.CategoryDao;
import vn.iostar.dao.impl.CategoryDaoImpl;
import vn.iostar.entity.Category;
import vn.iostar.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {

    private final CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public List<Category> findByUserId(int userId) {
        return categoryDao.findByUserId(userId);
    }

    @Override
    public boolean insert(Category category) {
        // DAO.create(...) trả về Category hoặc null
        return categoryDao.create(category) != null;
    }

    @Override
    public boolean update(Category category) {
        // DAO.update(...) trả về Category hoặc null
        return categoryDao.update(category) != null;
    }

    @Override
    public boolean delete(int id) {
        try {
            categoryDao.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
