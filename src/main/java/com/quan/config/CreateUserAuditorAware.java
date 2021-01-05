package com.quan.config;

import com.quan.constant.Constant;
import com.quan.util.JwtUtil;
import com.quan.util.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/14
 */
@Configuration
public class CreateUserAuditorAware implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String token = request.getHeader(Constant.jwtHeader);
        if (StringUtils.isEmpty(token)){
            return Optional.of(0);
        }
        String subject = JwtUtil.getSubjectFromToken(token);
        if (subject != null) {
            return Optional.of(Integer.parseInt(subject));
        }
        return Optional.of(0);
    }
}
