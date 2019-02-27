package com.lhc.zshop.backend.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhc.zshop.common.PaginationConstant;
import com.lhc.zshop.pojo.Sysuser;
import com.lhc.zshop.service.SysuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/backend/sysuser")
public class SysuserController {

    @Autowired
    private SysuserService sysuserService;

    @RequestMapping("/login")
    public String login(){
        return "main";
    }

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        List<Sysuser> sysusers = sysuserService.findAll();
        PageInfo pageInfo = new PageInfo(sysusers);
        model.addAttribute("pageInfo", pageInfo);
        return "sysuserManager";
    }
}
