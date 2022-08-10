package com.study.aroundhub.controller;

import com.study.aroundhub.data.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    //url : http://localhost:8080/api/v1/get-api/name
    @GetMapping("/name")
    public String getName() {
        return "Jini";
    }

    //url : http://localhost:8080/api/v1/get-api/variable1/testname
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        return variable;
    }

    //url : http://localhost:8080/api/v1/get-api/variable2/testname
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable")  String var) {
        return var;
    }

    //url : http://localhost:8080/api/v1/get-api/request1?email=jini@gmail.com&name=jini&organization=Home
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization
    )  {
        return name + " " + email + " " + organization;
    }


    //url : http://localhost:8080/api/v1/get-api/request2?email=jini@gmail.com&name=jini&organization=Home
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey()).append(" : ").append(map.getValue()).append("\n");
        });

        return sb.toString();
    }

    //url : http://localhost:8080/api/v1/get-api/request3?email=jini@gmail.com&name=jini&organization=Home
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDTO memberDTO) {
        return memberDTO.toString();
    }
}
