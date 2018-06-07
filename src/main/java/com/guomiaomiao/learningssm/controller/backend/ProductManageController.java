package com.guomiaomiao.learningssm.controller.backend;

import com.guomiaomiao.learningssm.common.Const;
import com.guomiaomiao.learningssm.common.ResponseCode;
import com.guomiaomiao.learningssm.common.ServerResponse;
import com.guomiaomiao.learningssm.pojo.Product;
import com.guomiaomiao.learningssm.pojo.User;
import com.guomiaomiao.learningssm.service.IProductService;
import com.guomiaomiao.learningssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15295 on 2018/6/1.
 */

@Controller
@RequestMapping("/manage/product/")
public class ProductManageController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IUserService iUserService;

    @RequestMapping("list")
    @ResponseBody
    public List<Product> list(HttpSession session) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
            System.out.println("请输入账号与密码");
            return null;
        } else
            return iProductService.getProductList();
    }

    @RequestMapping("add")
    @ResponseBody
    public void add(HttpSession session, Product product) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
            System.out.println("请输入账号与密码");
        }
        if (iUserService.checkAdminRole(user).isSuccess())
            iProductService.add(product);
        else
            System.out.println("无权限操作");
    }

    @RequestMapping("update")
    @ResponseBody
    public void update(HttpSession session, Product product) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
            System.out.println("请输入账号与密码");
        }
        if (iUserService.checkAdminRole(user).isSuccess())
            iProductService.update(product);
        else
            System.out.println("无权限操作");
    }
    @RequestMapping("delete")
    @ResponseBody
    public void delete(HttpSession session, Integer id) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
            System.out.println("请输入账号与密码");
        }
        if (iUserService.checkAdminRole(user).isSuccess())
            iProductService.delete(id);
        else
            System.out.println("无权限操作");
    }

    @RequestMapping("get")
    @ResponseBody
    public Product get(HttpSession session, Integer id) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
            System.out.println("请输入账号与密码");
        }
        if (iUserService.checkAdminRole(user).isSuccess())
            return iProductService.get(id);
        else {
            System.out.println("无权限操作");
            return null;
        }
    }
}
