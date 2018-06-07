package com.guomiaomiao.learningssm.service;


import com.guomiaomiao.learningssm.common.ServerResponse;
import com.guomiaomiao.learningssm.pojo.User;

/**
 * Created by 15295 on 2018/6/2.
 */
public interface IUserService {
    ServerResponse<User> login(String userName, String password);
    ServerResponse checkAdminRole(User user);
}
