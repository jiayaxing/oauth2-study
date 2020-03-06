package com.jiayaxing.order.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user",url = "http://192.168.1.156:8067")
public interface UserFeignClient {
    @RequestMapping(value = "/user-server/userController/getUserToken",method = RequestMethod.GET,consumes = "applacation/json")
    String getUserToken(@RequestHeader("Authorization") String token);

    @GetMapping("/user-server/userController/get2")
    String get2();
}
