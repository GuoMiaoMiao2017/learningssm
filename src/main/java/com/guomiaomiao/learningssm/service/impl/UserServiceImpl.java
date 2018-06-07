package com.guomiaomiao.learningssm.service.impl;

import com.guomiaomiao.learningssm.common.Const;
import com.guomiaomiao.learningssm.common.ServerResponse;
import com.guomiaomiao.learningssm.dao.UserMapper;
import com.guomiaomiao.learningssm.pojo.User;
import com.guomiaomiao.learningssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 15295 on 2018/6/2.
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public ServerResponse<User> login(String userName, String password) {
        User user = userMapper.selectLogin(userName, password);
        if (user == null)
            return ServerResponse.createByErrorMessage("该用户不存在");
        return ServerResponse.createBySuccess("登录成功", user);
    }

    //检验是否是管理员
    public ServerResponse checkAdminRole(User user) {
        if (user != null && user.getIsVip().intValue() == Const.Role.ROLE_ADMIN)
            return ServerResponse.createBySuccess();
        return ServerResponse.createByError();
    }


}
