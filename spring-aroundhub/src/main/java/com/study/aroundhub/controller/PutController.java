package com.study.aroundhub.controller;

import com.study.aroundhub.data.dto.MemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    // http://localhost:8080/api/v1/put-api/default
    @PutMapping(value = "/default")
    public String putMethod() {
        return "Hello World!";
    }

    // http://localhost:8080/api/v1/put-api/member
    @PutMapping(value = "/member")
    public String putMember(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder();

        postData.forEach((key, value) -> sb.append(key).append(" : ").append(value).append("\n"));

        return sb.toString();
    }

    // http://localhost:8080/api/v1/put-api/member1
    @PutMapping(value = "/member1")
    public String putMemberDto1(@RequestBody MemberDTO memberDTO) {
        return memberDTO.toString();
    }

    // http://localhost:8080/api/v1/put-api/member2
    @PutMapping(value = "/member2")
    public MemberDTO putMemberDto2(@RequestBody MemberDTO memberDTO) {
        return memberDTO;
    }

    // http://localhost:8080/api/v1/put-api/member3
    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDTO> putMemberDto3(@RequestBody MemberDTO memberDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDTO);
    }

}
