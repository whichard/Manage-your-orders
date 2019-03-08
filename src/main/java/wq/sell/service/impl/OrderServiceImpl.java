package wq.sell.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import wq.sell.dataobject.OrderDetail;
import wq.sell.dataobject.OrderMaster;
import wq.sell.dataobject.ProductInfo;
import wq.sell.datatransferobject.CartDTO;
import wq.sell.datatransferobject.OrderDTO;
import wq.sell.enums.ResultEnum;
import wq.sell.exception.SellException;
import wq.sell.repository.OrderDetailRepository;
import wq.sell.repository.OrderMasterRepository;
import wq.sell.service.OrderService;
import wq.sell.service.ProductService;
import wq.sell.utils.KeyUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        List<CartDTO> cartDTOList = new ArrayList<>();
//        1.查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//        2. 计算总价
            orderAmount = orderDetail.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
//            订单详情,写入数据库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail); //使用Spring的对象拷贝方法拷贝属性
            orderDetailRepository.save(orderDetail);
            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }
//
//        3.写入订单数据库（OrderMaster，OrderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMasterRepository.save(orderMaster);

//        4. 扣库存
        productService.decreaseStock(cartDTOList);
        return null;

    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOepnid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
