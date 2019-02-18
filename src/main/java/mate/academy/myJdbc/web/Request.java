package mate.academy.myJdbc.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Request {
    private String url;
    private RequestMethod method;
    private Map<String, String[]> parameterMap;

    public enum RequestMethod {
        GET,
        POST
    }

    public Request(String url, RequestMethod method, Map<String, String[]> params) {
        this.url = url;
        this.method = method;
        parameterMap = params;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, String[]> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public static Request of(String url, RequestMethod method, Map map) {
        return new Request(url, method, map);
    }

    public static Request of(String url, RequestMethod method) {
        return new Request(url, method, new HashMap<>());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(url, request.url) &&
                method == request.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, method);
    }

    public String getParamByName(String name) {
        return parameterMap.get(name)[0];
    }
}
