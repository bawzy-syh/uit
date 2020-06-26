package com.syh.uit.auth_service.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtGenerator {

    /** token秘钥，请勿泄露，请勿随便修改 backups:secret */
    public static final String SECRET = "secret";
    /** token 过期时间: 10天 */
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 10;

    /**
     * JWT生成Token.<br/>
     * @param user_id
     *            登录成功后用户user_id, 参数user_id不可传空
     */
    public static String createToken(int user_id) {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:uit, aud:oauth2-resource}
        String token = JWT.create().withHeader(map) // header
                .withClaim("iss", "uit") // payload
                .withClaim("aud", "oauth2-resource")
                .withClaim("sub", user_id)
                //.withJWTId()
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET)); // signature
        /*  iss: jwt签发者
            sub: jwt所面向的用户
            aud: 接收jwt的一方
            exp: jwt的过期时间，这个过期时间必须要大于签发时间
            nbf: 定义在什么时间之前，该jwt都是不可用的.
            iat: jwt的签发时间
            jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
         */
        return token;
    }
}
