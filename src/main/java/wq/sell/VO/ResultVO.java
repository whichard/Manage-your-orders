package wq.sell.VO;

import lombok.Data;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;

}
