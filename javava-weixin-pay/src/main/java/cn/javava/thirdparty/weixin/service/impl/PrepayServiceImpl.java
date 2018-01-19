package cn.javava.thirdparty.weixin.service.impl;

import cn.javava.thirdparty.weixin.entity.Prepay;
import cn.javava.thirdparty.weixin.repository.PrepayRepository;
import cn.javava.thirdparty.weixin.service.PrepayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * Created by wlrllr on 2018/1/12.
 */
@Service
public class PrepayServiceImpl implements PrepayService {

    @Autowired
    private PrepayRepository prepayRepository;

    @Override
    public Prepay findByOutTradeNo(String outTradeNo) {
        Prepay prepay = new Prepay();
        prepay.setOutTradeNo(outTradeNo);
        return prepayRepository.findOne(Example.of(prepay));
    }
}
