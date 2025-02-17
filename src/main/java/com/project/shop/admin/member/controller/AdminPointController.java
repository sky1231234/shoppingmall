package com.project.shop.admin.member.controller;

import com.project.shop.member.dto.request.PointUseRequest;
import com.project.shop.member.service.PointService;
import com.project.shop.global.config.security.domain.UserDto;
import com.project.shop.member.dto.request.PointRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping ("/admin/points")
@RequiredArgsConstructor
@Validated
@Tag( name = "AdminPointController", description = "[관리자] 포인트 API")
public class AdminPointController {

    private final PointService pointService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<HttpStatus> create(@AuthenticationPrincipal UserDto userDto, @RequestBody PointRequest pointRequest){
        long pointId = pointService.create(userDto.getLoginId(), pointRequest);
        return ResponseEntity.created(URI.create("/admin/points"+pointId)).build();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/uses")
    public ResponseEntity<HttpStatus> use(@AuthenticationPrincipal UserDto userDto, @RequestBody PointUseRequest pointUseRequest){
        long pointUseId = pointService.use(userDto.getLoginId(), pointUseRequest);
        return ResponseEntity.created(URI.create("/admin/points/uses"+pointUseId)).build();

    }


}
