package ex03.pyrmont.connector.http;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * Created by ST on 2016/12/20.
 */
public class HttpRequestFacade implements HttpServletRequest {

    private HttpServletRequest request;

    public HttpRequestFacade(HttpRequest request){
        this.request = request;
    }

    public String getAuthType() {
        return request.getAuthType();
    }

    public Cookie[] getCookies() {
        return request.getCookies();
    }

    public long getDateHeader(String s) {
        return request.getDateHeader(s);
    }

    public String getHeader(String s) {
        return request.getHeader(s);
    }

    public Enumeration<String> getHeaders(String s) {
        return request.getHeaders(s);
    }

    public Enumeration<String> getHeaderNames() {
        return request.getHeaderNames();
    }

    public int getIntHeader(String s) {
        return request.getIntHeader(s);
    }

    public String getMethod() {
        return request.getMethod();
    }

    public String getPathInfo() {
        return request.getPathInfo();
    }

    public String getPathTranslated() {
        return request.getPathTranslated();
    }

    public String getContextPath() {
        return request.getContextPath();
    }

    public String getQueryString() {
        return request.getQueryString();
    }

    public String getRemoteUser() {
        return request.getRemoteUser();
    }

    public boolean isUserInRole(String s) {
        return false;
    }

    public Principal getUserPrincipal() {
        return request.getUserPrincipal();
    }

    public String getRequestedSessionId() {
        return request.getRequestedSessionId();
    }

    public String getRequestURI() {
        return request.getRequestURI();
    }

    public StringBuffer getRequestURL() {
        return request.getRequestURL();
    }

    public String getServletPath() {
        return request.getServletPath();
    }

    public HttpSession getSession(boolean b) {
        return request.getSession(b);
    }

    public HttpSession getSession() {
        return request.getSession();
    }

    public String changeSessionId() {
        return null;
    }

    public boolean isRequestedSessionIdValid() {
        return false;
    }

    public boolean isRequestedSessionIdFromCookie() {
        return false;
    }

    public boolean isRequestedSessionIdFromURL() {
        return false;
    }

    public boolean isRequestedSessionIdFromUrl() {
        return false;
    }

    public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
        return false;
    }

    public void login(String s, String s1) throws ServletException {

    }

    public void logout() throws ServletException {

    }


    public Object getAttribute(String name) {
        return request.getAttribute(name);
    }

    public Enumeration<String> getAttributeNames() {
        return request.getAttributeNames();
    }

    public String getCharacterEncoding() {
        return request.getCharacterEncoding();
    }

    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {
        request.setCharacterEncoding(s);
    }

    public int getContentLength() {
        return request.getContentLength();
    }

    public long getContentLengthLong() {
        return 0;
    }

    public String getContentType() {
        return request.getContentType();
    }

    public ServletInputStream getInputStream() throws IOException {
        return request.getInputStream();
    }

    public String getParameter(String s) {
        return request.getParameter(s);
    }

    public Enumeration<String> getParameterNames() {
        return request.getParameterNames();
    }

    public String[] getParameterValues(String s) {
        return request.getParameterValues(s);
    }

    public Map<String, String[]> getParameterMap() {
        return request.getParameterMap();
    }

    public String getProtocol() {
        return request.getProtocol();
    }

    public String getScheme() {
        return request.getScheme();
    }

    public String getServerName() {
        return request.getServerName();
    }

    public int getServerPort() {
        return request.getServerPort();
    }

    public BufferedReader getReader() throws IOException {
        return request.getReader();
    }

    public String getRemoteAddr() {
        return request.getRemoteAddr();
    }

    public String getRemoteHost() {
        return request.getRemoteHost();
    }

    public void setAttribute(String s, Object o) {
        request.setAttribute(s,o);
    }

    public void removeAttribute(String s) {
        request.removeAttribute(s);
    }

    public Locale getLocale() {
        return request.getLocale();
    }

    public Enumeration<Locale> getLocales() {
        return request.getLocales();
    }

    public boolean isSecure() {
        return false;
    }

    public RequestDispatcher getRequestDispatcher(String s) {
        return request.getRequestDispatcher(s);
    }

    public String getRealPath(String s) {
        return request.getRealPath(s);
    }


    public boolean isAsyncStarted() {
        return false;
    }

    public boolean isAsyncSupported() {
        return false;
    }

}
