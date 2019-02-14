package mate.academy.myJdbc.web;

import mate.academy.myJdbc.controllers.Controller;
import mate.academy.myJdbc.controllers.HomeController;
import mate.academy.myJdbc.controllers.LoginController;
import mate.academy.myJdbc.controllers.RegistrationController;

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
        controllers.put(Request.of("/servlet/login", Request.RequestMethod.GET), new LoginController());
        controllers.put(Request.of("/servlet/registration", Request.RequestMethod.GET), new RegistrationController());
        controllers.put(Request.of("/servlet/home", Request.RequestMethod.GET), new HomeController());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = getController(req);
        ViewModel vm = controller.processGet();
        sendResponse(vm, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = getController(req);
        ViewModel vm = controller.processPost();
        sendResponse(vm, req, resp);
    }

    private Controller getController(HttpServletRequest req) {
        String path = req.getServletPath() + req.getPathInfo();
        Request request = Request.of(path, Request.RequestMethod.valueOf(req.getMethod()));
        return controllers.get(request);
    }

    private void sendResponse(ViewModel vm, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectURL = "/WEB-INF/views/%s.jsp";
        vm.getModel().forEach(req::setAttribute);
        req.getRequestDispatcher(String.format(redirectURL, vm.getView()))
                .forward(req, resp);
    }
}
