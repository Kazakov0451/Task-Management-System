package com.effective.mobile.service.auth;

import com.effective.mobile.exception.LoginOrPasswordException;
import com.effective.mobile.model.dto.auth_token.JwtAuthenticationResponseDto;
import com.effective.mobile.model.dto.auth_token.RefreshTokenRequest;
import com.effective.mobile.model.dto.user.LoginFromRequestDto;
import com.effective.mobile.security.JWT.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDetailsService userDetailsService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public JwtAuthenticationResponseDto login(LoginFromRequestDto loginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password()));
        } catch (BadCredentialsException e) {
            throw new LoginOrPasswordException(HttpStatus.FORBIDDEN, "Необходимо указать корректный логин и пароль");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.email());

        String token = jwtTokenUtils.generateAccessToken(userDetails);
        String refreshToken = jwtTokenUtils.generateRefreshToken(userDetails);

        return new JwtAuthenticationResponseDto(token, refreshToken);
    }

    @Override
    public JwtAuthenticationResponseDto refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String refreshUserName = jwtTokenUtils.getUserNameFromRefreshToken(refreshTokenRequest.refreshToken());
        UserDetails userDetails = userDetailsService.loadUserByUsername(refreshUserName);
        if (jwtTokenUtils.isTokenValid(refreshTokenRequest.refreshToken(), userDetails)) {
            String accessToken = jwtTokenUtils.generateAccessToken(userDetails);
            String refreshToken = jwtTokenUtils.generateRefreshToken(userDetails);
            return new JwtAuthenticationResponseDto(accessToken, refreshToken);
        } else {
            return null;
        }
    }
}
