package com.auth.service;

import com.auth.jwt.TokenManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auth.config.JWTProps;
import com.auth.Util;
import com.auth.Constants;


@Service
public class VerifyTokenProvider extends TokenManager {
    private final long authTokenExpirationMinutes;
    private final long registerTokenExpirationMinutes;

    public  VerifyTokenProvider(JWTProps props){
        super(props);
        this.authTokenExpirationMinutes = props.getAuthTokenExpirationMinutes();
        this.registerTokenExpirationMinutes = props.getRegisterTokenExpirationMinutes();
    }

    public String getPreAuthenticationToken(String authDetails, String verifyNumber){
        Map<String, String> data = new HashMap<String, String>();
        data.put("vnm", verifyNumber);
        return createToken(
                Util.getCurrentDatePlusMinutes(this.authTokenExpirationMinutes),
                authDetails,
                AuthType.PreAuth.toString(),
                data
        );
    }

    public String getPostAuthenticationToken(String authDetails){
        return createToken(
                Util.getCurrentDatePlusMinutes(this.registerTokenExpirationMinutes),
                authDetails,
                AuthType.PostAuth.toString()
        );
    }

    public VerifyData getPreAuthenticationTokenData(String token){
        List<String> keys = new ArrayList<String>();
        keys.add(Constants.VERIFY_CODE_KEY_NAME);
        TokenData dt =  getTokenDataFromToken(token, keys);
        return new VerifyData(dt.subject(), dt.type(), dt.data().get(Constants.VERIFY_CODE_KEY_NAME));
    }

    public VerifyData getPostAuthenticationTokenData(String token){
        TokenData dt = getTokenDataFromToken(token);
        return new VerifyData(dt.subject(), dt.type(), null);
    }

    @Getter
    @AllArgsConstructor
    public static class VerifyData{
        private String authDetails;
        private String authType;
        private String verifyNumber;
    }
    public enum AuthType{
        PreAuth, PostAuth
    }
}
