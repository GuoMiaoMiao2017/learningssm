package com.guomiaomiao.learningssm.controller.portal;

import com.guomiaomiao.learningssm.other.Single2;
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
    @Autowired
    private Single2 s2;
    @RequestMapping("/login")
    public String login(String userName, String password, HttpSession session) {
        //Single s = Single.getSingle();
        System.out.println("count = " + s2.getCount());

        System.out.println(userName + "***********" + password);
        ServerResponse<User> response = iUserService.login(userName, password);
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
