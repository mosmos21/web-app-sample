package jp.co.unirita.webapp.sample.service;

import jp.co.unirita.webapp.sample.domain.master.category.CategoryMaster;
import jp.co.unirita.webapp.sample.domain.master.category.CategoryMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryMasterRepository categoryMasterRepository;

    public List<CategoryMaster> getAllCategory() {
        return categoryMasterRepository.findAll();
    }

    public Map<Long, String> getCategoryMap() {
        return categoryMasterRepository.findAll()
                .stream().collect(Collectors.toMap(CategoryMaster::getId, CategoryMaster::getName));
    }
}
