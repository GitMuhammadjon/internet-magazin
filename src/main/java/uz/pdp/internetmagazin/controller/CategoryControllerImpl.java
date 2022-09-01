package uz.pdp.internetmagazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.CategoryDTO;
import uz.pdp.internetmagazin.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController{

    private final CategoryService categoryService;

    @Override
    public ApiResult add(CategoryDTO categoryDTO) {
        return categoryService.add(categoryDTO);
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryService.getAll();
    }

    @Override
    public CategoryDTO get(Integer id) {
        return categoryService.get(id);
    }

    @Override
    public ApiResult edit(Integer id, CategoryDTO categoryDTO) {
        return categoryService.edit(id, categoryDTO);
    }

    @Override
    public ApiResult delete(Integer id) {
        return categoryService.delete(id);
    }
}
