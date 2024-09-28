package socialLoginPractice.iseevictP1.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import socialLoginPractice.iseevictP1.domain.member.entity.User;
import socialLoginPractice.iseevictP1.domain.member.repository.UserRepository;

// UserDetailsService : 사용자 정보 가져오는 인터페이스
@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 사용자 이름(email)으로 사용자의 정보를 가져오는 메서드
    @Override
    public User loadUserByUsername(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }
}
