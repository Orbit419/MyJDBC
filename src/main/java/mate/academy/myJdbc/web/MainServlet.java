package mate.academy.myJdbc.web;

import mate.academy.myJdbc.config.Factory;
import mate.academy.myJdbc.controllers.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {
    private final static Map<Request, Controller> controllers = new HashMap<>();

    static {
        controllers.put(Request.of("/servlet/login", Request.RequestMethod.GET), r -> ViewModel.of("login"));
        controllers.put(Request.of("/servlet/login", Request.RequestMethod.POST), Factory.getLoginController());
        controllers.put(Request.of("/servlet/registration", Request.RequestMethod.POST), Factory.getRegistrationController());
        controllers.put(Request.of("/servlet/registration", Request.RequestMethod.GET), r -> ViewModel.of("registration"));
        controllers.put(Request.of("/servlet/logout", Request.RequestMethod.GET), r -> ViewModel.of("logout"));
        controllers.put(Request.of("/servlet/logout\"", Request.RequestMethod.POST), Factory.getLogoutController());
        controllers.put(Request.of("/servlet/home", Request.RequestMethod.GET), r -> ViewModel.of("home"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath() + req.getPathInfo();
        Map<String, String[]> parameterMap = req.getParameterMap();
        Request request = Request.of(path, Request.RequestMethod.valueOf(req.getMethod()), parameterMap);
        Controller controller = controllers.getOrDefault(request,
                regNotExist -> ViewModel.of("404"));
        ViewModel vm = controller.process(request);
        if (!vm.getAllCookie().isEmpty()) {
            resp.addCookie(vm.getCookie());
        }
        if (!vm.getView().equals("")) {
            sendResponse(vm, req, resp);
        }
    }

    private void sendResponse(ViewModel vm, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectURL = "/WEB-INF/views/%s.jsp";
        vm.getModel().forEach(req::setAttribute);
        req.getRequestDispatcher(String.format(redirectURL, vm.getView()))
                .forward(req, resp);
    }
}
