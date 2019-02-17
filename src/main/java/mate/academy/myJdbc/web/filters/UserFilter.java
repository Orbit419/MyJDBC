package mate.academy.myJdbc.web.filters;

import mate.academy.myJdbc.config.Factory;
import mate.academy.myJdbc.dao.UserDao;
import mate.academy.myJdbc.model.User;

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

public class UserFilter implements Filter {
    private UserDao userDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = Factory.getUserDao();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Cookie[] cookies = req.getCookies();
        String token = null;
        if(cookies.length > 0) {
            for(Cookie c : cookies) {
                if(c.getName().equals("MATE") && (c.getValue() != null && !c.getValue().equals(""))) {
                    token = c.getValue();
                }
            }
        }

        String path = req.getServletPath() + req.getPathInfo();
        if(token == null) {
            if(path.equals("/servlet/login") || path.equals("/servlet/registration")) {
                processAuthorized(req, resp, filterChain);
            } else {
                processUnauthorized(req, resp);
            }
        } else {
            User user = userDao.findByToken(token);
            if(user == null) {
                processUnauthorized(req, resp);
            } else {
                if(path.equals("/servlet/login") || path.equals("/servlet/registration")) {
                    req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
                }
                processAuthorized(req, resp, filterChain);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private void processAuthorized(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    private void processUnauthorized(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectURL = "/WEB-INF/views/login.jsp";
        req.getRequestDispatcher(redirectURL).forward(req, resp);
    }
}
