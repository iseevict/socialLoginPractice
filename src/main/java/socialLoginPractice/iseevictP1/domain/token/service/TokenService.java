package socialLoginPractice.iseevictP1.domain.token.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import socialLoginPractice.iseevictP1.domain.refreshToken.service.RefreshTokenService;
import socialLoginPractice.iseevictP1.domain.user.entity.User;
import socialLoginPractice.iseevictP1.domain.user.service.UserService;
import socialLoginPractice.iseevictP1.global.jwt.TokenProvider;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    // 전달받은 리프레시 토큰으로 유효성 검사 진행 후 유효하면 사용자 ID를 찾고 새로운 액세스 토큰 생성 후 반환하는 메서드
    public String createNewAccessToken(String refreshToken) {

        // 토큰 유효성 검사에 실패하면 예외 발생
        if (!tokenProvider.validToken(refreshToken)) {

            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
