package wq.sell.service;

/**
 * @author wq
 * @date 2019/3/26
 */
public interface SecKillService {
    //查询秒杀商品信息
    String querySecKillProductInfo(String productId);
    //模拟不同用户秒杀同商品的请求
    void orderProductMockDiffUser(String productId);
}
