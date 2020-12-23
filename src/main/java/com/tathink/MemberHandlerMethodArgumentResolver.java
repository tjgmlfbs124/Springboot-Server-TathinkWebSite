package com.tathink;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.tathink.database.model.Member;


public class MemberHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver
{
	@Override
	public boolean supportsParameter(MethodParameter parameter)
	{
		return Member.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception
	{
		Authentication auth = (Authentication) webRequest.getUserPrincipal();
		return auth != null && auth.getPrincipal() instanceof Member ? auth.getPrincipal() : null;
	}
}
