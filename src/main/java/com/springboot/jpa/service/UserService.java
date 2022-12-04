package com.springboot.jpa.service;

import com.springboot.jpa.dto.UserRequest;
import com.springboot.jpa.dto.UserResponse;
import com.springboot.jpa.entity.User;
import com.springboot.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUser(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()) {
            return new UserResponse(id, "", "해당 id의 유저가 없습니다.");
        } else {
            User user = optUser.get();
            return new UserResponse(user.getId(), user.getUsername(), "");
        }
    }

    public UserResponse addUser(UserRequest dto) {
        // Dto를 Entity로
        User user = dto.toEntity();
        // 저장하기 전에 Username으로 select 해보기
        // 있으면 중복되었습니다. 메세지 출력하고 save하지 않음
        // Talend api Tester로 육안으로 테스트해보기
        Optional<User> selectedUser = userRepository.findByUsername(dto.getUsername());
        if (selectedUser.isEmpty()) {
            User savedUser = userRepository.save(user);
            return new UserResponse(savedUser.getId(), savedUser.getUsername(), "회원 등록 성공");
        } else {
            return new UserResponse(null, dto.getUsername(), "이 User는 이미 존재합니다. 다른 이름을 사용하세요.");
        }
    }
}
