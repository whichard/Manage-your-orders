package wq.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import wq.sell.dataobject.ProductCategory;
import wq.sell.dataobject.ProductInfo;
import wq.sell.repository.ProductCategoryRepository;
import wq.sell.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return null;
    }

    @Override
    public List<ProductInfo> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return null;
    }
}
