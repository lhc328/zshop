package com.lhc.zshop.front.controller;


import com.lhc.zshop.util.HttpClientUtils;
import com.lhc.zshop.util.RedisUtils;
import com.lhc.zshop.util.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/sms")
public class SmsController {

    @Value("${sms.url}")
    private String url;

    @Value("${sms.key}")
    private String key;

    @Value("${sms.tplId}")
    private String tplId;

    @Value("${sms.tplValue}")
    private String tplValue;

    @RequestMapping("/sendVerificationCode")
    @ResponseBody
    public ResponseResult sendVerificationCode(String phone, HttpSession session){
        try {
            Random random = new Random();
            int randVerificationCode = random.nextInt(999999);
            session.setAttribute("randVerificationCode", randVerificationCode);

            //将验证码放到redis
            RedisUtils.set(session.getId(), randVerificationCode+"", 2 * 60);

            Map<String, String> map = new HashMap<>();
            map.put("key", key);
            map.put("mobile", phone);
            map.put("tpl_id", tplId);
            map.put("tpl_value", tplValue + randVerificationCode);
            String result = HttpClientUtils.doPost(url, map);
            System.out.println(result);
            return ResponseResult.success("验证码发送成功");
        }catch (Exception e){
            return ResponseResult.fail("验证码发送失败");
        }
    }
}
