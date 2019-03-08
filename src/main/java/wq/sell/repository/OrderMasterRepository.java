package wq.sell.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import wq.sell.dataobject.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
//    按照买家openid查询并且分页
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
