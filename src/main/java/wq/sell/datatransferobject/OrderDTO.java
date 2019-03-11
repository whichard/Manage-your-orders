package wq.sell.datatransferobject;

//DTO, data transfer object 数据传输对象，用于在各个层（Service层， DAO层，Controller层）之间传输数据

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import wq.sell.dataobject.OrderDetail;
import wq.sell.enums.OrderStatusEnum;
import wq.sell.enums.PayStatusEnum;
import wq.sell.utils.serializer.Date2LongSerializer;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    /*   订单状态， 默认新订单*/
    private Integer OrderStatus;
    //    支付状态，默认未支付
    private Integer payStatus;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private  Date updateTime;

    List<OrderDetail> orderDetailList;
}
