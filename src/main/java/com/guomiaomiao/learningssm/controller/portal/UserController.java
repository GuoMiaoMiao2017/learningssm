package com.guomiaomiao.learningssm.controller.portal;

import com.guomiaomiao.learningssm.common.Const;
import com.guomiaomiao.learningssm.common.ServerResponse;
import com.guomiaomiao.learningssm.pojo.User;
import com.guomiaomiao.learningssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by 15295 on 2018/6/3.
 */
@Controller
//@RequestMapping("/user/")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @RequestMapping("/login")
    public String managerLogin(String userName, String password, HttpSession session) {
        System.out.println(userName + "***********" + password);
        ServerResponse<User> response = iUserService.login(userName, password);
//        if (("a".equals(userName) && "1".equals(password))) {
//            session.setAttribute(Const.CURRENT_USER, new User(null,userName,password,null,null));
//            return "web-resources/html/welcome.html";
//        }
        if (response.isSuccess()) {
            System.out.println("输对了！！！！！");
            session.setAttribute(Const.CURRENT_USER, response.getData());
            return "web-resources/html/welcome.html";
        }
        else {
            System.out.println("错了错了！！！！！");
            return "web-resources/html/login_error.html";
        }
    }
}
