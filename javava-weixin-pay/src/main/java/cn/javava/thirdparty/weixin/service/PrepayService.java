package cn.javava.thirdparty.weixin.service;

import cn.javava.thirdparty.weixin.entity.Prepay;

/**
 * Created by wlrllr on 2018/1/16.
 */
public interface PrepayService {
    Prepay findByOutTradeNo(String outTradeNo);
}
