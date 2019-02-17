package mate.academy.myJdbc.web;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewModel {
    private final String view;
    private final List<Cookie> cookies = new ArrayList<>();
    private final Map<String, Object> model = new HashMap<>();

    public ViewModel(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public static ViewModel of(String view) {
        return new ViewModel(view);
    }

    public void addCookie(Cookie cookie) {
        this.cookies.add(cookie);
    }

    public Cookie getCookie() {
        return cookies.get(0);
    }

    public List<Cookie> getAllCookie() {
        return cookies;
    }

    public void addAttribute(String k, Object v) {
        model.put(k, v);
    }
}
