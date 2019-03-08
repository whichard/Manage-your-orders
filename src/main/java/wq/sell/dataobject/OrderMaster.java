package wq.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import wq.sell.enums.OrderStatusEnum;
import wq.sell.enums.PayStatusEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
/*   订单状态， 默认新订单*/
    private Integer OrderStatus = OrderStatusEnum.NEW.getCode();
//    支付状态，默认未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;
    private  Date updateTime;

}
