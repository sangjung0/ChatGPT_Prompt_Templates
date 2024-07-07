package com.auth.service;

import com.auth.jwt.TokenManager;
import lombok.Getter;
import org.springframework.stereotype.Service;
import com.auth.config.JWTProps;
import com.auth.Constants;
import com.auth.Util;

import java.util.Date;


@Service
public class RefreshAccessTokenProvider extends TokenManager {
    private final long refreshTokenExpirationMinutes;
    private final long accessTokenExpirationMinutes;

    public RefreshAccessTokenProvider(JWTProps props){
        super(props);
        this.refreshTokenExpirationMinutes = props.getRefreshTokenExpirationMinutes();
        this.accessTokenExpirationMinutes = props.getAccessTokenExpirationMinutes();
    }

    public TokenInfo getAccessToken(String userName){
            Date accessTokenExpiration = Util.getCurrentDatePlusMinutes(this.accessTokenExpirationMinutes);
            String accessToken = createToken(
                    accessTokenExpiration,
                    userName,
                    Constants.ACCESS_TOKEN
            );
            return new TokenInfo(accessToken, null);
    }

    public TokenInfo getToken(String userName){
        Date accessTokenExpiration = Util.getCurrentDatePlusMinutes(this.accessTokenExpirationMinutes);
        Date refreshTokenExpiration = Util.getCurrentDatePlusMinutes(this.refreshTokenExpirationMinutes);
        String accessToken = createToken(
                accessTokenExpiration,
                userName,
                Constants.ACCESS_TOKEN
        );
        String refreshToken = createToken(
                refreshTokenExpiration,
                userName,
                Constants.REFRESH_TOKEN
        );
        return new TokenInfo(accessToken, refreshToken);
    }

    @Getter
    public static class TokenInfo {
        private final String accessToken;
        private final String refreshToken;

        private TokenInfo(String accessToken, String refreshToken){
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }
}
