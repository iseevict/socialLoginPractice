package socialLoginPractice.iseevictP1.domain.refreshToken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import socialLoginPractice.iseevictP1.domain.refreshToken.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUserId(Long userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
