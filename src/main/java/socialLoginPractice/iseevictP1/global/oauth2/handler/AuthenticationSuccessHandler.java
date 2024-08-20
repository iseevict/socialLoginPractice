package socialLoginPractice.iseevictP1.global.oauth2.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import socialLoginPractice.iseevictP1.global.oauth2.CookieOAuth2AuthorizationRequestRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final CookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository;
    private final 
}
