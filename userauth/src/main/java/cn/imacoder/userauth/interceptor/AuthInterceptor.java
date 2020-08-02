package cn.imacoder.userauth.interceptor;

import cn.imacoder.userauth.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");// 从 http 请求头中取出 token

        Method method = ((HandlerMethod) handler).getMethod();

        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(IgnoreAuth.class)) {
            return true;
        }
        if (token == null || token.isEmpty()) {
            this.buildResp(response, "token为空!");
            return false;
        }
        return true;
    }

    private void buildResp(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setStatus(403);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            logger.error("response error", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

}
