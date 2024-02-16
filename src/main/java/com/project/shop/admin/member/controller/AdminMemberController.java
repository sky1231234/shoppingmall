package com.project.shop.admin.member.controller;

import com.project.shop.global.config.security.domain.UserDto;
import com.project.shop.member.dto.request.PointRequest;
import com.project.shop.member.dto.request.PointUseRequest;
import com.project.shop.member.dto.response.MemberResponse;
import com.project.shop.member.service.MemberService;
import com.project.shop.member.service.PointService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/admin/members")
@RequiredArgsConstructor
@Validated
@Tag( name = "AdminMemberController", description = "[관리자] 회원 API")
public class AdminMemberController {

    private final MemberService memberService;

    //회원 전체 조회
    @PostMapping
    public ResponseEntity<List<MemberResponse>> findAll(@AuthenticationPrincipal UserDto userDto){
        return ResponseEntity.ok()
                .body(memberService.findAll(userDto.getLoginId()));
    }


}
