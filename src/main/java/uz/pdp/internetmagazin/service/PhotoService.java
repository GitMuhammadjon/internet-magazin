package uz.pdp.internetmagazin.service;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.internetmagazin.payload.AddResult;
import uz.pdp.internetmagazin.payload.ApiResult;

import java.io.IOException;
import java.util.List;

public interface PhotoService {
    AddResult add(MultipartFile request, MultipartHttpServletRequest  multipartHttpServletRequest) throws IOException;

    AddResult edit(MultipartFile request, MultipartHttpServletRequest  multipartHttpServletRequest,Integer photo_id) throws IOException;
}
