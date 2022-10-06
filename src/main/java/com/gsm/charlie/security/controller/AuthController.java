package com.gsm.charlie.security.controller;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.gsm.charlie.catalog.domain.model.Product;
import com.gsm.charlie.catalog.resource.ProductResource;
import com.gsm.charlie.catalog.resource.SaveProductResource;
import com.gsm.charlie.security.domain.model.User;
import com.gsm.charlie.security.domain.service.AuthService;
import com.gsm.charlie.security.payload.request.FirstUser;
import com.gsm.charlie.security.payload.request.TokenRefreshRequest;
import com.gsm.charlie.security.payload.response.JwtResponse;
import com.gsm.charlie.security.payload.response.MessageResponse;
import com.gsm.charlie.security.payload.response.TokenRefreshResponse;
import com.gsm.charlie.security.domain.model.ERole;
import com.gsm.charlie.security.domain.model.RefreshToken;
import com.gsm.charlie.security.domain.model.Role;
import com.gsm.charlie.security.domain.repository.RoleRepository;
import com.gsm.charlie.security.domain.repository.UserRepository;
import com.gsm.charlie.security.exception.TokenRefreshException;
import com.gsm.charlie.security.payload.request.LoginRequest;
import com.gsm.charlie.security.payload.request.SignupRequest;
import com.gsm.charlie.security.resource.SaveUserResource;
import com.gsm.charlie.security.resource.UserResource;
import com.gsm.charlie.security.service.RefreshTokenService;
import com.gsm.charlie.security.service.UserDetailsImpl;
import com.gsm.charlie.security.util.JwtCenter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AuthService authService;

    @PostMapping("/sign_in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/sign_up")
    public UserResource registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return convertToResource(authService.registerUser(signUpRequest));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        return authService.refreshtoken(request);
    }

    @PostMapping("/sign_out")
    public ResponseEntity<?> logoutUser() {
        return authService.logoutUser();
    }

    @Transactional
    @PostMapping("/create_roles")
    public ResponseEntity<?> createRoles() {
        return authService.createRoles();
    }

    @PostMapping("/first_user")
    public ResponseEntity<?> firstUserAdmin(@Valid @RequestBody FirstUser request) {
        return authService.firstUserAdmin(request);
    }

    @PostMapping("/emergency_user")
    public ResponseEntity<?> emergencyUserAdmin(@Valid @RequestBody FirstUser request) {
        return authService.emergencyUserAdmin(request);
    }

    private User convertToEntity(SignupRequest resource) {
        return mapper.map(resource, User.class);
    }
    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }

}