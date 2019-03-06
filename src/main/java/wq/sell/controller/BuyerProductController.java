package wq.sell.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wq.sell.vo.ProductInfoVO;
import wq.sell.vo.ProductVO;
import wq.sell.vo.ResultVO;

import java.util.Arrays;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @GetMapping("/list")
    public ResultVO list() {
        ResultVO resultVO = new ResultVO();
        ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();
        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));

        resultVO.setCode(0);
        resultVO.setMsg("Succeed");
        resultVO.setData(Arrays.asList(productVO));
        return resultVO;
    }

}
