package uz.pdp.internetmagazin.service;

import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {
    ApiResult add(CategoryDTO categoryDTO);

    ApiResult getAll();

    ApiResult edit(Integer id, CategoryDTO categoryDTO);

    ApiResult delete(Integer id);
}
