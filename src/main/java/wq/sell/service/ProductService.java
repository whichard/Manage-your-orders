package wq.sell.service;

import org.springframework.data.domain.Pageable;
import wq.sell.dataobject.ProductCategory;
import wq.sell.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {
    ProductInfo findOne(String productId);
    /**查询所有在架商品*/
    List<ProductInfo> findUpAll();

    List<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存

    //减库存
}
