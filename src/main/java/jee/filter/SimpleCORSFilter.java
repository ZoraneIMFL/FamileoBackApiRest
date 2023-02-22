package jee.filter;

import io.vavr.collection.List;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.logging.Logger;

public class SimpleCORSFilter implements Filter {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final String allowedMethods;
    private final String allowedHeader;

    public SimpleCORSFilter() {
        allowedMethods = List.of(
                "GET",
                "POST",
                "PUT",
                "PATCH",
                "DELETE",
                "PATCH",
                "HEAD",
                "OPTIONS"
        ).mkString(",");
        allowedHeader = List.of(
                "Content-Type",
                "X-Requested-With",
                "Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers",
                "Accept",
                "Authorization"
        ).mkString(",");
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        logger.info("Cors filter enabled");
    }

    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain
    ) throws IOException, ServletException {
        if ((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)) {
            final HttpServletResponse httpResponse = (HttpServletResponse) response;

            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Methods", allowedMethods);
            httpResponse.setHeader("Access-Control-Max-Age", "360000");
            httpResponse.setHeader("Access-Control-Allow-Headers", allowedHeader);

            if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
                httpResponse.setStatus(Response.Status.ACCEPTED.getStatusCode());
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        //TODO
    }
}
