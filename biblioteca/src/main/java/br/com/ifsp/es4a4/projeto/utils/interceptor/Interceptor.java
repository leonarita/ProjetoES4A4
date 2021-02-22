package br.com.ifsp.es4a4.projeto.utils.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.ifsp.es4a4.projeto.utils.jwt.service.UserSecurityService;
import br.com.ifsp.es4a4.projeto.utils.routes.AllowAnnonymous;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class Interceptor implements HandlerInterceptor {
	
	private final UserSecurityService userSecurityService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		final AllowAnnonymous allowAnnonymousClass = AnnotationUtils.findAnnotation(AopProxyUtils.ultimateTargetClass(handler), AllowAnnonymous.class);
		final AllowAnnonymous allowAnnonymousMethod = ((HandlerMethod)handler).getMethod().getAnnotation((AllowAnnonymous.class));
		
		if(allowAnnonymousClass != null || allowAnnonymousMethod != null)
			return true;
		
		if (validateToken(request.getHeader("Authorization"))) {
			
			response.addHeader("Interceptor", "Authorization OK");
            log.info("Autenticação válida e autorização permitida!");

            return true;
        }
		
		response.addHeader("Interceptor", "Authorization Invalid");
		log.error("Autenticação inválida e autorização não permitida!");

		return false;
		
	}
	
	private Boolean validateToken(String authorization) {
		if(this.userSecurityService.validate(authorization))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

}
