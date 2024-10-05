package socialLoginPractice.iseevictP1.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String email, String password, String auth) {

        this.email = email;
        this.password = password;
    }

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("user"));
    }

    // 사용자 id 반환
    @Override
    public String getUsername() {

        return email;
    }

    // 사용자의 패스워드 반환
    @Override
    public String getPassword() {

        return password;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {

        /**
         * 만료 확인 로직 필요
         */
        return true; // 테스트용으로 만료되지 않았음을 의미하는 true를 default로 반환
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {

        /**
         * 계정 잠금 확인 로직 필요
         */
        return true;
    }

    // 패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {

        /**
         * 패스워드 만료 확인 로직 필요
         */
        return true;
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {

        /**
         * 계정이 사용 가능 확인 로직 필요
         */
        return true;
    }
}
