package com.ra.controller.auth;


import com.ra.model.dto.requset.UserLogin;
import com.ra.model.dto.response.UserResponse;
import com.ra.model.entity.User;
import com.ra.service.auth_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user)
    {
        User userNew =userService.register(user);
        if(userNew!=null)
        {
            return new ResponseEntity<>("Thêm thành công", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Thêm thất bại",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogin userLogin) {
        UserResponse userResponse = userService.login(userLogin);
        if(userResponse !=null)
        {
            return new ResponseEntity<>(userResponse,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Đăng nhập thất bại",HttpStatus.NO_CONTENT);
        }
    }
}
