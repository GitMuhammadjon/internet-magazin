package uz.pdp.internetmagazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.internetmagazin.entity.Category;
import uz.pdp.internetmagazin.entity.Product;
import uz.pdp.internetmagazin.payload.AddResult;
import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.FilterDTO;
import uz.pdp.internetmagazin.repository.CategoryRepository;
import uz.pdp.internetmagazin.repository.PhotoRepository;
import uz.pdp.internetmagazin.repository.Photo_ContentRepository;
import uz.pdp.internetmagazin.repository.ProductRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final PhotoRepository photoRepository;

    private final Photo_ContentRepository photo_contentRepository;

    private final ProductRepository productRepository;

    private final PhotoService photoService;

    private final JdbcTemplate jdbcTemplate;

    private final CategoryRepository categoryRepository;

    @Override
    public ApiResult add(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        Product product = new Product();
        product.setName(multipartHttpServletRequest.getParameter("name"));
        product.setPrice(Double.valueOf(multipartHttpServletRequest.getParameter("price")));
        Optional<Category> category = categoryRepository.findById(Integer.valueOf(multipartHttpServletRequest.getParameter("category")));
        if (category.isPresent()) {
            product.setCategory(category.get());
            Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
            MultipartFile file = multipartHttpServletRequest.getFile(fileNames.next());
            if (file != null) {
                AddResult add = photoService.add(file, multipartHttpServletRequest);
                if (add.isSuccess()) {
                    product.setPhoto(add.getPhoto());
                    productRepository.save(product);
                    return new ApiResult(true, "OK");
                } else {
                    return new ApiResult(false, "File save error");
                }
            } else {
                productRepository.save(product);
                return new ApiResult(true, "OK");
            }
        } else {
            return new ApiResult(false, "Category is empty");
        }
    }

    @Override
    public ApiResult getAll(Integer page) {
        int i = page * 20;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select p.id, p.name, p.price, ph.name as photo_name, ph.id as photo_id, p.category_id, c.name as category_name from product p left join category c on c.id = p.category_id left join photo ph on ph.id = p.photo_id offset ? limit 20", i);
        return new ApiResult(true, "OK", maps);
    }


    @Override
    public ApiResult edit(Integer id, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            Optional<Category> category = categoryRepository.findById(Integer.valueOf(multipartHttpServletRequest.getParameter("category")));
            if (category.isPresent()) {
                Product product = byId.get();
                product.setName(multipartHttpServletRequest.getParameter("name"));
                product.setPrice(Double.valueOf(multipartHttpServletRequest.getParameter("price")));
                product.setCategory(category.get());

                Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
                MultipartFile file = multipartHttpServletRequest.getFile(fileNames.next());

                if (file != null) {
                    if (product.getPhoto() == null) {
                        AddResult add = photoService.add(file, multipartHttpServletRequest);
                        if (add.isSuccess()) {
                            product.setPhoto(add.getPhoto());
                            productRepository.save(product);
                            return new ApiResult(true, "OK");
                        } else {
                            return new ApiResult(false, "File save error");
                        }
                    } else {
                        AddResult edit = photoService.edit(file, multipartHttpServletRequest, product.getPhoto().getId());
                        if (edit.isSuccess()) {
                            return new ApiResult(true, "OK");
                        } else {
                            return new ApiResult(true, "photo not found");
                        }
                    }
                } else {
                    productRepository.save(product);
                    return new ApiResult(true, "OK");
                }
            } else {
                return new ApiResult(false, "Category is empty");
            }
        } else {
            return new ApiResult(false, "Product is empty");
        }
    }

    @Override
    public ApiResult delete(Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            product.setActive(false);
            return new ApiResult(true,"success");
        }
        return new ApiResult(false,"error");
    }

    @Override
    public ApiResult filter(FilterDTO filterDTO) {
        String sql = "";
        if (filterDTO.getName() != null) {
            sql += " where upper(p.name) like upper('%" + filterDTO.getName() + "%') ";
        }
        if (filterDTO.getPriceOT() != null && filterDTO.getPriceDO() != null) {
            sql += (sql.length() > 0 ? " and " : " where ") + " p.price between '" + filterDTO.getPriceOT() + "' and '" + filterDTO.getPriceDO() + "' ";
        }
        if (filterDTO.getCategory_id() != null) {
            sql += (sql.length() > 0 ? " and " : " where ") + " p.category_id = " + filterDTO.getCategory_id();
        }

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select p.id, p.name, p.price, ph.name as photo_name, ph.id as photo_id, p.category_id, c.name as category_name from product p left join category c on c.id = p.category_id left join photo ph on ph.id = p.photo_id " + sql);


        return new ApiResult(true,"OK", maps);
    }

}
