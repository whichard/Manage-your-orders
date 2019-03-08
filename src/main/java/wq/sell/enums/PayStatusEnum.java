package wq.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(0,"支付成功"),
    ;
    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
