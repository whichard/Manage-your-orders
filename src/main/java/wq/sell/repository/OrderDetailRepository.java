package wq.sell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wq.sell.dataobject.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);
}
