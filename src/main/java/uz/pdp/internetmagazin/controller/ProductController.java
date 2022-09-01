package uz.pdp.internetmagazin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.FilterDTO;
import uz.pdp.internetmagazin.payload.ProductDTO;

import java.io.IOException;
import java.util.List;


@RequestMapping("/product")
public interface ProductController {

    @PostMapping
    ApiResult add(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException;

    @GetMapping
    ApiResult getAll(@RequestParam Integer page);



    @PutMapping("/{id}")
    ApiResult edit(@PathVariable Integer id,
                   @RequestBody MultipartHttpServletRequest multipartHttpServletRequest) throws IOException;

    @DeleteMapping("/{id}")
    ApiResult delete(@PathVariable Integer id);

    @PostMapping("/filter")
    ApiResult filter(@RequestBody FilterDTO filterDTO);

}
