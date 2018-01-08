package com.jeorgio.javava.thirdparty.zego.web;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.jeorgio.javava.thirdparty.zego.config.ApiConfig;
import com.jeorgio.javava.thirdparty.zego.util.ZegoConstants;
import com.jeorgio.javava.thirdparty.zego.service.NotificationService;
import com.jeorgio.javava.thirdparty.zego.service.OpenStreamHandler;
import com.jeorgio.javava.thirdparty.zego.vo.OpenStreamVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.jeorgio.javava.thirdparty.zego.util.ZegoUtil.createSign;
import static java.lang.Math.abs;
import static java.time.LocalDateTime.ofEpochSecond;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.join;

@RequestMapping("/v1")
@RestController
public class OpenStreamController {

    private Logger logger = LoggerFactory.getLogger(OpenStreamHandler.class);

    @Autowired
    private ApiConfig apiConfig;

    @Autowired
    @Qualifier("openStreamHandlerChain")
    private OpenStreamHandler openStreamHandler;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/openStream")
    public int openStream(HttpServletRequest request) {
        OpenStreamVo vo = assignVo(request);
        LocalDateTime timestamp = ofEpochSecond(Longs.tryParse(vo.getTimestamp()), 0, ZoneOffset.of("+8"));
        Duration duration = Duration.between(LocalDateTime.now(), timestamp);
        //if (abs(duration.toDays()) > 1) {
        //    notificationService.sendMail("[加一联萌] 时间调整通知", join("创建流回调的娃娃机时间与业务服务器的时间不一致，请运维人员调整娃娃机、服务器的时间。\n\n", vo.toString()));
        //    return ZegoConstants.CODE_DATETIME_MISMATCH;
        //}
        String signature = createSign(apiConfig.getSecret(), vo.getTimestamp(), vo.getNonce());
        if (isNotBlank(signature) && signature.equals(vo.getSignature())) {
            openStreamHandler.handle(vo);
        } else {
            notificationService.sendMail("[加一联萌] 签名错误通知", join("创建流回调的签名不正确，请核查密钥是否已修改。\n\n", vo.toString()));
            if (logger.isErrorEnabled()) {
                logger.error("创建流回调接口 -> 签名不一致");
            }
            return ZegoConstants.CODE_ILLEGAL_SIGN;
        }
        return ZegoConstants.CODE_SUCCESS;
    }

    private OpenStreamVo assignVo(HttpServletRequest request) {
        OpenStreamVo vo = new OpenStreamVo();

        String id = request.getParameter("id");
        if (isNotBlank(id)) {
            vo.setId(Ints.tryParse(id));
        }

        String live_id = request.getParameter("live_id");
        if (isNotBlank(live_id)) {
            vo.setLiveId(Ints.tryParse(live_id));
        }

        String channel_id = request.getParameter("channel_id");
        if (isNotBlank(channel_id)) {
            vo.setChannelId(channel_id);
        }

        String title = request.getParameter("title");
        if (isNotBlank(title)) {
            vo.setTitle(title);
        }

        String stream_alias = request.getParameter("stream_alias");
        if (isNotBlank(stream_alias)) {
            vo.setStreamAlias(stream_alias);
        }

        String publish_id = request.getParameter("publish_id");
        if (isNotBlank(publish_id)) {
            vo.setPublishId(publish_id);
        }

        String publish_name = request.getParameter("publish_name");
        if (isNotBlank(publish_name)) {
            vo.setPublishName(publish_name);
        }

        String[] rtmp_url = request.getParameterValues("rtmp_url[]");
        if (isNotEmpty(rtmp_url)) {
            vo.setRtmpUrl(rtmp_url);
        }

        String[] hls_url = request.getParameterValues("hls_url[]");
        if (isNotEmpty(hls_url)) {
            vo.setHlsUrl(hls_url);
        }

        String[] hdl_url = request.getParameterValues("hdl_url[]");
        if (isNotEmpty(hdl_url)) {
            vo.setHdlUrl(hdl_url);
        }

        String[] pic_url = request.getParameterValues("pic_url[]");
        if (isNotEmpty(pic_url)) {
            vo.setPicUrl(pic_url);
        }

        String create_time = request.getParameter("create_time");
        if (isNotBlank(create_time)) {
            vo.setCreateTime(Longs.tryParse(create_time));
        }

        String timestamp = request.getParameter("timestamp");
        if (isNotBlank(timestamp)) {
            vo.setTimestamp(timestamp);
        }

        String nonce = request.getParameter("nonce");
        if (isNotBlank(nonce)) {
            vo.setNonce(nonce);
        }

        String signature = request.getParameter("signature");
        if (isNotBlank(signature)) {
            vo.setSignature(signature);
        }

        System.out.println(vo.toString());
        return vo;
    }
}
