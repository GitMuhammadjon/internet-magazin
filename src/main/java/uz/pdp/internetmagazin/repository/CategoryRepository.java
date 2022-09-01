package uz.pdp.internetmagazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.internetmagazin.entity.Category;
import uz.pdp.internetmagazin.entity.Product;


public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
