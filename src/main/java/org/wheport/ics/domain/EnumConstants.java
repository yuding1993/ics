package org.wheport.ics.domain;

public enum EnumConstants {

    /**
     * 未知
     */
    DEMO_1("1","自动生成"),
    DEMO_2("2","手动生成"),

    /**
     * 业务返回码 code
     */
    RETURN_SUCCESS("0","成功"),
    RETURN_FAIL("-1","失败");

    private String code;
    private String value;

    /**
     * 枚举构造函数
     * @param code 返回码
     * @param value 返回码描述
     */
    EnumConstants(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 返回错误枚举的代码
     *
     * @return 返回错误代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 返回错误枚举的描述
     *
     * @return 返回错误描述
     */
    public String getValue() {
        return value;
    }
}
