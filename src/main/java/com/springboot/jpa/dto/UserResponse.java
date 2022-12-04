package com.springboot.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
// password 같은거는 보여주기 싫으니까 제외
public class UserResponse {
    private Long id;
    private String username;
    private String message;

}
