package socialLoginPractice.iseevictP1.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

public class UserRequestDto {

    @Getter
    @Setter
    public class AddUserRequestDto {

        private String email;
        private String password;
    }
}
