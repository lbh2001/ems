package com.lbh.controller;

import com.lbh.entity.User;
import com.lbh.service.UserService;
import com.lbh.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @Author lbh
 * @Date 2021/3/26
 * @Description:
 */
@RestController
@Slf4j
public class UserserverController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    /**
     * 生成验证码图片
     * @return
     */
    @GetMapping("/user/getImage")
    public Map<String,Object> getImage() throws IOException {
        Map<String,Object> map = new HashMap<>();
        //生成验证码
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        String codeKey = UUID.randomUUID().toString();
        //redis存储验证码
        stringRedisTemplate.opsForValue().set(codeKey,verifyCode,60, TimeUnit.SECONDS);
        //base64转换验证码
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(120,60, byteArrayOutputStream,verifyCode);
        String data ="data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
        //响应数据
        map.put("src",data);
        map.put("codeKey",codeKey);
        log.info(verifyCode);
        return map;
    }

    @PostMapping("/user/register")
    @ResponseBody
    public Map<String,Object> register(User user,String code,String codeKey){
        Map<String,Object> map = new HashMap<>();
        try {
            if (!stringRedisTemplate.hasKey(codeKey)) throw new RuntimeException("验证码已过期");
            String oldCode = stringRedisTemplate.opsForValue().get(codeKey);
            if(oldCode.equals(code)){
                //注册用户
                userService.save(user);
                map.put("msg","注册成功");
                map.put("state",true);
            }else{
                throw new RuntimeException("验证码不正确");
            }
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("state",false);
        }
        return map;
    }

    @PostMapping("/user/login")
    @ResponseBody
    public Map<String,Object> login(User user){
        Map<String,Object> map = new HashMap<>();
        try{
            User userDB = userService.login(user);
            map.put("msg","登录成功");
            map.put("state",true);
            map.put("user",userDB);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg",e.getMessage());
            map.put("state",false);
        }

        return map;
    }

}
