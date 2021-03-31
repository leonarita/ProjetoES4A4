package br.com.ifsp.es4a4.projeto.utils.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.ifsp.es4a4.projeto.utils.jwt.service.UserSecurityService;
import br.com.ifsp.es4a4.projeto.utils.routes.AllowAnnonymous;
import br.com.ifsp.es4a4.projeto.utils.routes.CacheAnnotedClasses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class Interceptor implements HandlerInterceptor {
	
	private final UserSecurityService userSecurityService;
	private final CacheAnnotedClasses cacheAnnotedClasses;
	
	private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		try {
			if(request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingHandler").toString().startsWith("ResourceHttpRequestHandler")) {
				return true;
			}
			
			HandlerMethod handlerMethod = (HandlerMethod)request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingHandler");
			
			//final AllowAnnonymous allowAnnonymousMethod = ((HandlerMethod)handler).getMethod().getAnnotation((AllowAnnonymous.class));
			final AllowAnnonymous allowAnnonymousMethod = handlerMethod.getMethod().getAnnotation((AllowAnnonymous.class));
			
			if( cacheAnnotedClasses.classHasAnonymousAnnotation(handlerMethod.getBeanType().toString().replace("class ", "")) || allowAnnonymousMethod != null )
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
		catch (Exception e) {
			
			logger.error("Erro ao acessar endpoint: " + e.getMessage());
			
			return false;
		}
		
	}
	
	private Boolean validateToken(String authorization) {
		if(this.userSecurityService.validate(authorization))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

}
