package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.digitalone.houzingapp.Exception.RefreshTokenException;
import uz.digitalone.houzingapp.dto.request.RefreshTokenRequest;
import uz.digitalone.houzingapp.entity.auth.RefreshToken;
import uz.digitalone.houzingapp.repository.RefreshTokenRepository;
import uz.digitalone.houzingapp.service.RefreshTokenService;

import java.time.Instant;
import java.util.UUID;
@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generateRefreshToken(Long id) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpirationTime(Instant.now());
        refreshToken.setUserId(id);
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public void validationToken(String refreshToken) {
        refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() ->
                        new RefreshTokenException("Refresh token invalid"));
    }

    @Override
    public void refreshTokenDelete(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(request.
                getRefreshToken()).orElseThrow(() ->
                new RefreshTokenException("Token not found"));
        refreshTokenRepository.delete(refreshToken);
    }
}
