package wq.sell.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wq.sell.dataobject.OrderDetail;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456780");
        orderDetail.setOrderId("123321");
        orderDetail.setProductId("111111123");
        orderDetail.setProductName("煎饼果子");
        orderDetail.setProductPrice(new BigDecimal(3.0));
        orderDetail.setProductQuantity(9);
        orderDetail.setProductIcon("xx.jpg");

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList=  repository.findByOrderId("123321");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}