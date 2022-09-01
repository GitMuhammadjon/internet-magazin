package uz.pdp.internetmagazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.internetmagazin.entity.Category;
import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.CategoryDTO;
import uz.pdp.internetmagazin.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepositary;



    @Override
    public ApiResult add(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());

        categoryRepositary.save(category);
        return new ApiResult(true,"success");
    }

    @Override
    public ApiResult getAll() {
        List<Category> all = categoryRepositary.findAll();
        return new ApiResult(true,"OK",all);
    }



    @Override
    public ApiResult edit(Integer id, CategoryDTO categoryDTO) {
        Optional<Category> byId = categoryRepositary.findById(id);
        if(byId.isPresent()){
            Category category = byId.get();
            category.setName(category.getName());

            categoryRepositary.save(category);
            return new ApiResult(true,"success category");
        }
        return new ApiResult(false,"bunday category yoq");
    }

    @Override
    public ApiResult delete(Integer id) {
        Optional<Category> byId = categoryRepositary.findById(id);
        if(byId.isPresent()) {
            Category category = byId.get();
            category.setActive(false);
            return new ApiResult(true, "delete boldi!");
        }
        return new ApiResult(false, "bunday category yoq!");
    }
}
