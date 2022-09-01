package uz.pdp.internetmagazin.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.CategoryDTO;

import java.util.List;

@RequestMapping("/category")
public interface CategoryController {

    @PostMapping
    ApiResult add(@RequestBody CategoryDTO categoryDTO);

    @GetMapping
    List<CategoryDTO> getAll();

    @GetMapping("/{id}")
    CategoryDTO get(@PathVariable Integer id);

    @PutMapping("/{id}")
    ApiResult edit(@PathVariable Integer id,
                   @RequestBody CategoryDTO categoryDTO);

    @DeleteMapping("/{id}")
    ApiResult delete(@PathVariable Integer id);
}
