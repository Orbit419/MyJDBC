package mate.academy.myJdbc.web.filters;

import mate.academy.myJdbc.util.ServletsFactory;
import mate.academy.myJdbc.model.Role;
import mate.academy.myJdbc.model.User;
import mate.academy.myJdbc.service.UserService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserFilter implements Filter {
    private UserService userService;

    private Map<String, String> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = ServletsFactory.getUserService();
        protectedUrls.put("/servlet/home", "ADMIN");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Cookie[] cookies = req.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("MATE") && (c.getValue() != null && !c.getValue().equals(""))) {
                    token = c.getValue();
                }
            }
        }

        String path = req.getServletPath() + req.getPathInfo();
        if (token == null) {
            if (path.equals("/servlet/login") || path.equals("/servlet/registration")) {
                processAuthorized(req, resp, filterChain);
            } else {
                processUnauthorized(req, resp);
            }
        } else {
            User user = userService.findByToken(token);
            if (user == null) {
                processUnauthorized(req, resp);
            } else {
                if (path.equals("/servlet/login") || path.equals("/servlet/registration")) {
                    resp.sendRedirect("/servlet/home");
                }
                if (verifyRole(user, path)) {
                    processAuthorized(req, resp, filterChain);
                }
                processDenied(req, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private void processDenied(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/403.jsp").forward(req, resp);
    }

    private void processAuthorized(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    private void processUnauthorized(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/servlet/login");
    }

    private boolean verifyRole(User user, String path) {
        String role = protectedUrls.get(path);
        if(role == null)
            return true;

        boolean result = false;
        for(Role r: user.getRoles()) {
            if (role.equals(r.getRole())) {
                result = true;
            }
        }
        return result;
    }
}
