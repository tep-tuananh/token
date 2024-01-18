package com.ra.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {
    private Long id;
    private String fullName;
    private String token;
    private final String type = "Bearer Token";
    private Set<String> roles ;
}
