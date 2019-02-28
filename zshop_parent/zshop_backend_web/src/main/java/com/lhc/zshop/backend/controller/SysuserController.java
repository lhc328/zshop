package com.lhc.zshop.backend.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhc.zshop.common.PaginationConstant;
import com.lhc.zshop.params.SysuserParam;
import com.lhc.zshop.pojo.Role;
import com.lhc.zshop.pojo.Sysuser;
import com.lhc.zshop.service.RoleService;
import com.lhc.zshop.service.SysuserService;
import com.lhc.zshop.util.ResponseResult;
import com.lhc.zshop.vo.SysuserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/backend/sysuser")
public class SysuserController {

    @Autowired
    private SysuserService sysuserService;

    @Autowired
    private RoleService roleService;

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

    @RequestMapping("/findByParams")
    public String findByParams(SysuserParam sysuserParam, Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        List<Sysuser> sysusers = sysuserService.findParams(sysuserParam);
        PageInfo pageInfo = new PageInfo(sysusers);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("sysuserParam", sysuserParam);
        return "sysuserManager";
    }

    @ModelAttribute("roles")
    public List<Role> loadRoles(){
        return roleService.findAll();
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult addSysuser(SysuserVo sysuserVo){
        sysuserService.add(sysuserVo);
        return ResponseResult.success();
    }

    @RequestMapping("/modifyStatus")
    @ResponseBody
    public ResponseResult modifyStatus(Integer id){
        sysuserService.modifyStatus(id);
        return ResponseResult.success();
    }
}
