package com.javava.thirdparty.weixin.constant;

/**
 * 常量
 */
public class WxConstants {

    public enum SignType {
        MD5, HMACSHA256
    }

    public static final String RESPONSE_CODE = "codeUrl";
    public static final String RESPONSE_TRADE_NO = "tradeNo";
    /**
     * 充值
     */
    public static final String OPERATE_TYPE_RECHARGE = "recharge";
    /**
     * 消费
     */
    public static final String OPERATE_TYPE_PURCHASE = "purchase";


    public static final String FAIL     = "FAIL";
    public static final String SUCCESS  = "SUCCESS";
    public static final String DEALING  = "DEALING";

    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";

    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";

}

