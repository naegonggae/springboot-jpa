package com.springboot.jpa.service;

import com.springboot.jpa.dto.UserRequest;
import com.springboot.jpa.dto.UserResponse;
import com.springboot.jpa.entity.User;
import com.springboot.jpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

// @없이 테스트 할 수 있으면 가장 좋음
//왜냐하면 속도가 빠르기 때문 – ApplicationContext에 Bean을 덜 로드 해도 되기때문
//Service는 Pojo로 만드는 것이 좋습니다 Plain Old Java Object
//Dependency 없이 Business로직만 존재하도록 만드는 것이 중요합니다. Dao, Repository Dependency는 있을 수 있음.
class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private UserService userService;

    @BeforeEach
    void set() {
        userService = new UserService(userRepository); // 수동 DI
    }

    @Test
    @DisplayName("회원 등록 성공 메세지가 잘 나오는지")
    void addTest() {
        Mockito.when(userRepository.save(any()))
                .thenReturn(new User(1l, "sanghun", "1234"));

        UserResponse userResponse = userService.addUser(new UserRequest("sanghun", "1234"));

        assertEquals("sanghun", userResponse.getUsername());
        assertEquals("회원 등록 성공", userResponse.getMessage());
    }
}