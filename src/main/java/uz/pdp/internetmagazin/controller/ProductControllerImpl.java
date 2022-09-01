package uz.pdp.internetmagazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.FilterDTO;
import uz.pdp.internetmagazin.service.ProductService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;


    @Override
    public ApiResult add(MultipartHttpServletRequest request) throws IOException {
        return productService.add(request);
    }

    @Override
    public ApiResult getAll(Integer page) {
        return productService.getAll(page);
    }


    @Override
    public ApiResult edit(Integer id, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        return productService.edit(id, multipartHttpServletRequest);
    }

    @Override
    public ApiResult delete(Integer id) {
        return productService.delete(id);
    }

    @Override
    public ApiResult filter(FilterDTO filterDTO) {
        return productService.filter(filterDTO);
    }
}
