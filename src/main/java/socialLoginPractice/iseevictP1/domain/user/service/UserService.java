package socialLoginPractice.iseevictP1.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import socialLoginPractice.iseevictP1.domain.user.dto.UserRequestDto;
import socialLoginPractice.iseevictP1.domain.user.entity.User;
import socialLoginPractice.iseevictP1.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원 추가 메서드
    public Long save(UserRequestDto.AddUserRequestDto addUserRequestDto) {

        return userRepository.save(User.builder()
                .email(addUserRequestDto.getEmail())
                .password(bCryptPasswordEncoder.encode(addUserRequestDto.getPassword()))
                .build()).getId();
    }

    // 전달받은 유저 ID를 검색해서 전달하는 findById() 메서드
    public User findById(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
