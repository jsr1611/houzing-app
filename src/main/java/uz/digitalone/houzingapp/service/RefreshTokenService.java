package uz.digitalone.houzingapp.service;

import uz.digitalone.houzingapp.dto.request.RefreshTokenRequest;
import uz.digitalone.houzingapp.entity.auth.RefreshToken;

public interface RefreshTokenService {

    RefreshToken generateRefreshToken();

    void validationToken(String refreshToken);

    void refreshTokenDelete(RefreshTokenRequest request);
}
