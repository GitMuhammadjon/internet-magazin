package uz.pdp.internetmagazin.service;


import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.FilterDTO;
import uz.pdp.internetmagazin.payload.ProductDTO;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ApiResult add(MultipartHttpServletRequest request) throws IOException;

    ApiResult getAll(Integer page);


    ApiResult edit(Integer id, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException;

    ApiResult delete(Integer id);

    ApiResult filter(FilterDTO filterDTO);
}
