package socialLoginPractice.iseevictP1.global.oauth2.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationProcessingException extends AuthenticationException {

    public AuthenticationProcessingException(String message) {

        super(message);
    }
}
