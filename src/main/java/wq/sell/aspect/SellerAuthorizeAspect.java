package wq.sell.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wq.sell.constant.CookieConstant;
import wq.sell.constant.RedisConstant;
import wq.sell.enums.ResultEnum;
import wq.sell.exception.SellException;
import wq.sell.exception.SellerAthorizeException;
import wq.sell.utils.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wq
 * @date 2019/3/15
 */

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * wq.sell.controller.Seller*.*(..))" +
    "&& !execution(public * wq.sell.controller.SellerUserController.*(..))")
    public void vertify() { }

    @Before("vertify()")
    public void doVertify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//      查询Cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查询不到token");
            //通过 SellerExceptionHandler捕获异常进行处理
            throw new SellerAthorizeException();
        }

        //去redis中查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查询不到token");
            //通过 SellerExceptionHandler捕获异常进行处理
            throw new SellerAthorizeException();
        }

    }
}
