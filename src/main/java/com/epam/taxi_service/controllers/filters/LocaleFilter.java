package com.epam.taxi_service.controllers.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.epam.taxi_service.controllers.actions.implementation.Parameters.LOCALE;

public class LocaleFilter implements Filter{
        private static final String REFERER = "referer";
        private String defaultLocale;

        /**
         * Sets default locale
         * @param config passed by application
         */
        @Override
        public void init(FilterConfig config) {
            defaultLocale = config.getInitParameter("defaultLocale");
        }

        /**
         * Checks if request contains locale parameter and sets locale to session as attribute if present.
         * Returns previous page in this case.
         * In other case checks if locale presents in session. If not check cookies for last locale and sets either locale
         * from cookies or default locale. doFilter after that.
         * @param request passed by application
         * @param response passed by application
         * @param chain passed by application
         */
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String locale = httpRequest.getParameter(LOCALE);
            if (isNotBlank(locale)) {
                httpRequest.getSession().setAttribute(LOCALE, locale);
                httpResponse.addCookie(new Cookie(LOCALE, locale));
                httpResponse.sendRedirect(httpRequest.getHeader(REFERER));
            } else {
                String sessionLocale = (String) httpRequest.getSession().getAttribute(LOCALE);
                if (isBlank(sessionLocale)) {
                    httpRequest.getSession().setAttribute(LOCALE, getCookiesLocale(httpRequest));
                }
                chain.doFilter(request, response);
            }
        }

        /**
         * Obtains locale value. Checks if Cookies are present, if so then checks if Cookie locale is present.
         * @param httpRequest to get Cookies
         * @return either cookie locale or default locale
         */
        private String getCookiesLocale(HttpServletRequest httpRequest) {
            return Stream.ofNullable(httpRequest.getCookies())
                    .flatMap(Arrays::stream)
                    .filter(cookie -> cookie.getName().equals(LOCALE))
                    .map(Cookie::getValue)
                    .findAny().orElse(defaultLocale);
        }

        private boolean isBlank(String locale) {
            return locale == null || locale.isEmpty();
        }

        private boolean isNotBlank(String locale) {
            return !isBlank(locale);
        }
}
