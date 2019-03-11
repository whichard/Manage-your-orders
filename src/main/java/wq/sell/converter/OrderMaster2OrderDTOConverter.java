package wq.sell.converter;

import org.springframework.beans.BeanUtils;
import wq.sell.dataobject.OrderMaster;
import wq.sell.datatransferobject.OrderDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for(OrderMaster orderMaster : orderMasterList) {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }
}
