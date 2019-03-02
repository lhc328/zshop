package com.lhc.zshop.front.controller;


import com.lhc.zshop.common.ResponseResultConstant;
import com.lhc.zshop.exception.LoginErrorException;
import com.lhc.zshop.exception.PhoneNotRegisterException;
import com.lhc.zshop.pojo.Customer;
import com.lhc.zshop.service.CustomerService;
import com.lhc.zshop.util.RedisUtils;
import com.lhc.zshop.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/front/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/loginByAccount")
    @ResponseBody
    public ResponseResult loginByAccount(String loginName, String password, HttpSession session){
        ResponseResult responseResult = new ResponseResult();
        try {
            Customer customer = customerService.login(loginName, password);
            session.setAttribute("customer", customer);
            responseResult.setStatus(ResponseResultConstant.RESPONSE_STATUS_SUCCESS);
            responseResult.setData(customer);
        }catch (LoginErrorException e){
            responseResult.setStatus(ResponseResultConstant.RESPONSE_STATUS_FAIL);
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ResponseResult logout(HttpSession session){
        session.invalidate();
        return ResponseResult.success();
    }

    @RequestMapping("/loginBySms")
    @ResponseBody
    public ResponseResult loginBySms(String phone, Integer verificationCode, HttpSession session){
        ResponseResult result = ResponseResult.fail();
        try {
            //判断手机号是否注册
            Customer customer = customerService.findByPhone(phone);
            //判断验证码是否存在
            //Object obj = session.getAttribute("randVerificationCode");
            //从Redis中获取验证码
            String obj = RedisUtils.get(session.getId());
            if(!ObjectUtils.isEmpty(obj)){
                //判断验证码是否正确
                Integer randVerificationCode = Integer.parseInt(obj);
                System.out.println(randVerificationCode);
                if(randVerificationCode.equals(verificationCode)){
                    session.setAttribute("customer", customer);
                    result.setData(customer);
                    result.setStatus(ResponseResultConstant.RESPONSE_STATUS_SUCCESS);
                }else{
                    result.setMessage("验证码不正确");
                }
            }else{
                result.setMessage("验证码不存在或已过期，请重新输入");
            }
        }catch (PhoneNotRegisterException e){
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
