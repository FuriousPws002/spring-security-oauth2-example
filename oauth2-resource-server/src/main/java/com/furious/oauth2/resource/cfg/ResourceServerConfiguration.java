package com.furious.oauth2.resource.cfg;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenExtractor(new CustomizeTokenExtractor());
    }

    private static class CustomizeTokenExtractor extends BearerTokenExtractor {
        @Override
        public Authentication extract(HttpServletRequest request) {
            Authentication extract = super.extract(request);
            if (extract != null) {
                return extract;
            }

            String token = request.getParameter("token");
            if (token != null) {
                return new PreAuthenticatedAuthenticationToken(token, "");
            }

            return null;
        }
    }
}
