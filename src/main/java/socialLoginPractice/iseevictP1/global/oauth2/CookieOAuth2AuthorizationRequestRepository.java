package socialLoginPractice.iseevictP1.global.oauth2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import socialLoginPractice.iseevictP1.global.oauth2.utils.CookieUtils;

@Component
@RequiredArgsConstructor
public class CookieOAuth2AuthorizationRequestRepository
        implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    public static final String AUTHORIZATION_REQUEST_COOKIE_NAME = "auth_request_cookie";
    public static final String REDIRECT_URI_COOKIE_NAME = "redirect_uri";
    public static final String MODE_COOKIE_NAME = "mode";
    private static final int COOKIE_EXPIRE_SECONDS = 180;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {

        return CookieUtils.getCookie(request, AUTHORIZATION_REQUEST_COOKIE_NAME)
                .map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
                .orElse(null);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authRequest,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {

        if (authRequest == null) {

            CookieUtils.deleteCookie(request, response, AUTHORIZATION_REQUEST_COOKIE_NAME);
            CookieUtils.deleteCookie(request, response, REDIRECT_URI_COOKIE_NAME);
            CookieUtils.deleteCookie(request, response, MODE_COOKIE_NAME);
            return;
        }

        CookieUtils.addCookie(response,
                AUTHORIZATION_REQUEST_COOKIE_NAME,
                CookieUtils.serialize(authRequest),
                COOKIE_EXPIRE_SECONDS);

        String redirectUriAfterLogin = request.getParameter(REDIRECT_URI_COOKIE_NAME);
        if (StringUtils.hasText(redirectUriAfterLogin)) {

            CookieUtils.addCookie(response,
                    REDIRECT_URI_COOKIE_NAME,
                    redirectUriAfterLogin,
                    COOKIE_EXPIRE_SECONDS);
        }

        String mode = request.getParameter(MODE_COOKIE_NAME);
        if (StringUtils.hasText(mode)) {

            CookieUtils.addCookie(response,
                    MODE_COOKIE_NAME,
                    mode,
                    COOKIE_EXPIRE_SECONDS);
        }
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request, HttpServletResponse response) {

        return this.loadAuthorizationRequest(request);
    }

    public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {

        CookieUtils.deleteCookie(request, response, AUTHORIZATION_REQUEST_COOKIE_NAME);
        CookieUtils.deleteCookie(request, response, REDIRECT_URI_COOKIE_NAME);
        CookieUtils.deleteCookie(request, response, MODE_COOKIE_NAME);
    }
}
