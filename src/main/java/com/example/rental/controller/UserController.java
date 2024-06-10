package com.example.rental.controller;

import com.example.rental.entity.User;
import com.example.rental.service.IUserService;
import com.example.rental.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author teacher_shi
 * @since 2024-06-08
 */
@RestController
@RequestMapping("/rental/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
    @GetMapping
    public Result<List<User>> list(){
        return Result.success(userService.list());
    }
//
//    @PostMapping("/login")
//    public Result<String> login(String username, String password) {
//        return Result.success(userService.login(username, password));

//}


//    public Result<User> login(String username, String password) {
//        User user = userService.selectByUsername(username);
//        if (user == null) {
//            return Result.fail();
//        }
//        if (!user.getPassword().equals(password)) {
//            return Result.fail();
//        }
//        return Result.success(user);
//    }


}
