package com.lambakean.RationPlanner.representation.controller;

import com.lambakean.RationPlanner.representation.dto.*;
import com.lambakean.RationPlanner.representation.dto.form.UserAuthenticationForm;
import com.lambakean.RationPlanner.domain.mapper.SecurityTokensHolderMapper;
import com.lambakean.RationPlanner.domain.mapper.UserMapper;
import com.lambakean.RationPlanner.data.model.SecurityTokensHolder;
import com.lambakean.RationPlanner.data.model.User;
import com.lambakean.RationPlanner.domain.service.PrincipalService;
import com.lambakean.RationPlanner.domain.service.SecurityTokensService;
import com.lambakean.RationPlanner.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PrincipalService principalService;
    private final SecurityTokensService securityTokensService;
    private final UserMapper userMapper;
    private final SecurityTokensHolderMapper securityTokensHolderMapper;

    @PostMapping
    public ResponseEntity<SecurityTokensHolderDto> register(@RequestBody UserAuthenticationForm userAuthenticationForm,
                                                            HttpServletResponse httpServletResponse) {

        SecurityTokensHolder securityTokensHolder = userService.register(
                userMapper.toUser(userAuthenticationForm),
                httpServletResponse
        );

        SecurityTokensHolderDto securityTokensHolderDto =
                securityTokensHolderMapper.toSecurityTokensHolderDto(securityTokensHolder);

        return new ResponseEntity<>(securityTokensHolderDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<SecurityTokensHolderDto> login(@RequestBody UserAuthenticationForm userAuthenticationForm,
                                                         HttpServletResponse httpServletResponse) {

        SecurityTokensHolder securityTokensHolder = userService.login(
                userMapper.toUser(userAuthenticationForm),
                httpServletResponse
        );

        SecurityTokensHolderDto securityTokensHolderDto =
                securityTokensHolderMapper.toSecurityTokensHolderDto(securityTokensHolder);

        return ResponseEntity.ok(securityTokensHolderDto);
    }

    @GetMapping()
    public ResponseEntity<UserDto> getCurrentUser() {

        User user = principalService.getPrincipal();
        UserDto userDto = userMapper.toUserDto(user);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/token")
    public ResponseEntity<SecurityTokensHolderDto> updateTokens(HttpServletRequest httpServletRequest,
                                                                HttpServletResponse httpServletResponse) {

        SecurityTokensHolder securityTokensHolder = securityTokensService.updateTokens(
                httpServletRequest,
                httpServletResponse
        );

        SecurityTokensHolderDto securityTokensHolderDto =
                securityTokensHolderMapper.toSecurityTokensHolderDto(securityTokensHolder);

        return ResponseEntity.ok(securityTokensHolderDto);
    }
}