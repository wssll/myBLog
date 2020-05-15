package com.sll.blog.service;

import com.sll.blog.dao.UserRepository;
import com.sll.blog.po.User;
import com.sll.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding.Use;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public User checkUser(String username) {
        User user = userRepository.findByUsername(username);
        System.out.println("--------------------------"+user);
        return user;
    }

    @Override
    public User save(String username, String password1, String password2, String email, String nickname) {
        if (!password1.equals(password2)){
            return null;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Utils.code(password1));
        user.setEmail(email);
        user.setNickname(nickname);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setRole("0");
        user.setAvatar("https://unsplash.it/1000/400?image=1005");
        User saveuser = userRepository.save(user);
        return saveuser;
    }

    @Override
    public User checkUser(String username, String password, String  role) {
        User user = userRepository.findByUPR(username,MD5Utils.code(password),role);
        return user;
    }
}
