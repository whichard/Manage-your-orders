package wq.sell.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import wq.sell.dataobject.OrderMaster;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "110101";
    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerName("二师兄");
        orderMaster.setBuyerPhone("12312312312");
        orderMaster.setBuyerAddress("百老汇");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.3));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(1,3);
        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID,request);
        Assert.assertNotEquals(0,result.getTotalElements());
    }
}