package com.study.aroundhub.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {

    // http://localhost:8080/api/v1/delete-api/delete/{string 값}
    @DeleteMapping(value = "/delete/{variable}")
    public String DeleteVariable(@PathVariable String variable) {
        return variable;
    }



}
