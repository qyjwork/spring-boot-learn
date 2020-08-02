package cn.imacoder.userauth.utils;

import io.jsonwebtoken.*;
import org.apache.logging.log4j.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


public class JWTUtils {

    private static Logger log = LoggerFactory.getLogger(JWTUtils.class);
    public static final String AUTH_HEADER_KEY = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_PRIVATEKEY = "87cea299-dee2-458d-9754-0954a498c6dd";
    public static final int TOKEN_EXPIRETOME = 30000;

    /**
     * 解析jwt
     *
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
        } catch (ExpiredJwtException eje) {
            log.error("===== Token过期 =====", eje);
            //throw new  Exception();
        } catch (Exception e) {
            log.error("===== token解析异常 =====", e);
            //throw new Exception(ResultCode.PERMISSION_TOKEN_INVALID);
        }
        return claims;
    }

    /**
     * 构建jwt
     *
     * @param userId
     * @param username
     * @return
     */
    public static String createJWT(String userId, String username) {
        String token = "";
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(TOKEN_PRIVATEKEY);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            //userId是重要信息，进行加密下
            //String encryId = Base64Util.encode(userId);
            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder().setHeaderParam("type", "JWT")
                    // 可以将基本不重要的对象信息放到claims
                    .claim("userId", userId)
                    .setSubject(username)           // 代表这个JWT的主体，即它的所有人
                    .setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            int TTLMillis = TOKEN_EXPIRETOME;
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                        .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
            }
            //生成JWT
            token = builder.compact();
        } catch (Exception e) {
            log.error("签名失败", e);
            //throw new CustomException(ResultCode.PERMISSION_SIGNATURE_ERROR);
        }
        return token;
    }

    /**
     * 从token中获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        return parseJWT(token, TOKEN_PRIVATEKEY).getSubject();
    }

    /**
     * 从token中获取用户ID
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        String userId = parseJWT(token, TOKEN_PRIVATEKEY).get("userId", String.class);
        return userId;
    }

    /**
     * 是否已过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        return parseJWT(token, TOKEN_PRIVATEKEY).getExpiration().before(new Date());
    }
}
