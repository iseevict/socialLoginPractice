package socialLoginPractice.iseevictP1.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import socialLoginPractice.iseevictP1.domain.member.dto.UserRequestDto;
import socialLoginPractice.iseevictP1.domain.member.entity.User;
import socialLoginPractice.iseevictP1.domain.member.repository.UserRepository;

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
}
