package wq.sell.service.impl;

import wq.sell.datatransferobject.CartDTO;
import wq.sell.enums.ProductStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import wq.sell.dataobject.ProductInfo;
import wq.sell.enums.ResultEnum;
import wq.sell.exception.SellException;
import wq.sell.repository.ProductInfoRepository;
import wq.sell.service.ProductService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).orElse(new ProductInfo());
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(new ProductInfo());
            if(productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(new ProductInfo());
            if(productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0)
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }
}
