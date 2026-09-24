package com.linkpulse.service;

import com.linkpulse.dto.AuthDTOs.AuthResponse;
import com.linkpulse.dto.AuthDTOs.LoginRequest;
import com.linkpulse.dto.AuthDTOs.RegisterRequest;
import com.linkpulse.entities.Organization;
import com.linkpulse.entities.User;
import com.linkpulse.repositories.OrganizationRepository;
import com.linkpulse.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("User with email '" + request.getEmail() + "' already exists.");
        }

        String orgName = (request.getOrganizationName() != null && !request.getOrganizationName().isBlank())
                ? request.getOrganizationName()
                : request.getName() + "'s Org";

        Organization org = new Organization();
        org.setName(orgName);
        org.setPlanType("FREE");
        Organization savedOrg = organizationRepository.save(org);

        User user = new User();
        user.setEmail(request.getEmail().toLowerCase());

        user.setPasswordHash(request.getPassword()); // In production, BCryptPasswordEncoder
        user.setName(request.getName());
        user.setRole("ROLE_USER");
        user.setVerified(true);
        user.setOrganization(savedOrg);

        User savedUser = userRepository.save(user);

        return AuthResponse.builder()
                .token("mock-jwt-token-" + savedUser.getId())
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .role(savedUser.getRole())
                .organizationId(savedOrg.getId())
                .organizationName(savedOrg.getName())
                .build();
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!request.getPassword().equals(user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        Organization org = user.getOrganization();

        return AuthResponse.builder()
                .token("mock-jwt-token-" + user.getId())
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .organizationId(org != null ? org.getId() : null)
                .organizationName(org != null ? org.getName() : "Default Org")
                .build();
    }
}
