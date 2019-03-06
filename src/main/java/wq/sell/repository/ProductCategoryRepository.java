package wq.sell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wq.sell.dataobject.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
