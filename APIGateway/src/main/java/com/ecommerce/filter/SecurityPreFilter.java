package com.ecommerce.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.security.JwtSecurity;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class SecurityPreFilter extends ZuulFilter {

	@Autowired
	JwtSecurity jwtSecurity;
	
	@Override
	public boolean shouldFilter() {
		RequestContext ctxContext = RequestContext.getCurrentContext();
		HttpServletRequest request = ctxContext.getRequest();
		String uri = request.getRequestURI();
		if (uri.contains("")) {
			
		} else {
			
		}
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctxContext = RequestContext.getCurrentContext();
		HttpServletRequest request = ctxContext.getRequest();
		String token = (String) request.getParameter("token");
		// Not found token in header
		if (token == null || token.isEmpty()) {
			ctxContext.setSendZuulResponse(false);
			ctxContext.setResponseStatusCode(200);
			ctxContext.setResponseBody("{\"result\":\"need token\"}");
		}
		// Found token in header
		else {
			Long user_id = jwtSecurity.parseTokenGetId(token);
			if (user_id == null) {
				ctxContext.setSendZuulResponse(false);
				ctxContext.setResponseStatusCode(200);
				ctxContext.setResponseBody("{\"result\":\"bad token\"}");
			} else {
				request.getParameterMap();
                Map<String, List<String>> requestQueryParams = ctxContext.getRequestQueryParams();
                if (requestQueryParams == null) {
                    requestQueryParams = new HashMap<>();
                }
                List<String> arrayList = new ArrayList<>();
                arrayList.add(String.valueOf(user_id));
                requestQueryParams.put("userid", arrayList);
                ctxContext.setRequestQueryParams(requestQueryParams);
			}
		}
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
	
}
