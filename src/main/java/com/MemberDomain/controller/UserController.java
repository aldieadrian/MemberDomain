package com.MemberDomain.controller;

import com.MemberDomain.mapper.Balance_mapper;
import com.MemberDomain.mapper.User_mapper;
import com.MemberDomain.model.request.LoginRequest;
import com.MemberDomain.model.request.RegisterRequest;
import com.MemberDomain.model.response.RegisterLoginResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private User_mapper userMapper;

    @Autowired
    private Balance_mapper balanceMapper;

    //get all user
    @GetMapping("/auth/user/all")
    public ResponseEntity<?> getAllUser(){
        List<RegisterLoginResponse> allUser=  userMapper.getAll();
        if (allUser.isEmpty()) {
            JSONObject jsonObject = new JSONObject();
            JSONObject empty = new JSONObject();
            jsonObject.put("data", empty);
            jsonObject.put("message", "User not found");
            jsonObject.put("status", "404");
            return new ResponseEntity<>(jsonObject, HttpStatus.NOT_FOUND);
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", allUser);
            jsonObject.put("message", "All user data has successfully sent");
            jsonObject.put("status", "200");
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
    }

    // insert user
    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest register) {
        if (register.getName().isEmpty() || register.getEmail().isEmpty() || register.getPhoneNumber().isEmpty() || register.getPassword().isEmpty() || register.getConfirmPassword().isEmpty()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Message", "Please fill in all the forms!");
            jsonObject.put("Code", "002");
            jsonObject.put("Http Status", "400");
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
//        if (register.getPassword() != register.getConfirmPassword()){
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("Message", "Password is missed-match.");
//            jsonObject.put("Code", "009");
//            jsonObject.put("Http Status", "400");
//            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
//        }

        RegisterLoginResponse checkEmail = userMapper.emailCheck(register.getEmail());
        if (checkEmail != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Message", "Email already exists.");
            jsonObject.put("Code", "007");
            jsonObject.put("Http Status", "400");
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }

        RegisterLoginResponse checkPhone = userMapper.phoneCheck(register.getPhoneNumber());
        if(checkPhone != null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Message", "Phone number already exists.");
            jsonObject.put("Code", "008");
            jsonObject.put("Http Status", "400");
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        else{
            String uuidUser = UUID.randomUUID().toString();
            register.setIdUser(uuidUser);
            userMapper.registerUser(register);
            balanceMapper.registerBalance(register.getIdUser());
            RegisterLoginResponse registered = userMapper.getUserProfile(register.getIdUser());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Code", "001");
            jsonObject.put("Data", registered);
            jsonObject.put("Message", "Registration is successful.");
            jsonObject.put("Status", "201");
            return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);
        }
    }

    // insert user
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        if (login.getPhoneNumber().isEmpty() || login.getPassword().isEmpty()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Message", "Please fill in all the forms!");
            jsonObject.put("Code", "002");
            jsonObject.put("Http Status", "400");
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
        RegisterLoginResponse checkUser = userMapper.login(login.getPhoneNumber(), login.getPassword());
        if (checkUser == null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Message", "Your data do not match our records.");
            jsonObject.put("Code", "011");
            jsonObject.put("Http Status", "404");
            return new ResponseEntity<>(jsonObject, HttpStatus.NOT_FOUND);
        }
        else{
            RegisterLoginResponse loggedIn = userMapper.getUserProfile(checkUser.getIdUser());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Code", "010");
            jsonObject.put("Data", loggedIn);
            jsonObject.put("Message", "You are logged in.");
            jsonObject.put("Status", "200");
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }
    }
}
