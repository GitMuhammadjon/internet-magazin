package uz.pdp.internetmagazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.internetmagazin.entity.Category;
import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.CategoryDTO;
import uz.pdp.internetmagazin.repository.CategoryRepository;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepositary;



    @Override
    public ApiResult add(CategoryDTO categoryDTO) {
        return new ApiResult(true,"success");
    }

    @Override
    public List<CategoryDTO> getAll() {
        return null;
    }

    @Override
    public CategoryDTO get(Integer id) {
        return null;
    }

    @Override
    public ApiResult edit(Integer id, CategoryDTO categoryDTO) {
        return new ApiResult(true,"success");
    }

    @Override
    public ApiResult delete(Integer id) {
        categoryRepositary.delete(categoryRepositary.findById(id).orElseThrow(IllegalStateException::new));
        return new ApiResult(true, "success");
    }


    public Category categoryDTOToCategory(Category category , CategoryDTO categoryDTO){
        category.setName(categoryDTO.getName());

        return category;
    }
}
