package ex03.pyrmont.connector.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

/**
 * Created by ST on 2016/12/20.
 */
public class HttpResponseFacade implements HttpServletResponse {
    private HttpServletResponse response;

    public HttpResponseFacade(HttpServletResponse response) {
        this.response = response;
    }

    public void addCookie(Cookie cookie) {
        response.addCookie(cookie);
    }

    public boolean containsHeader(String s) {
        return false;
    }

    public String encodeURL(String s) {
        return response.encodeURL(s);
    }

    public String encodeRedirectURL(String s) {
        return response.encodeRedirectURL(s);
    }

    public String encodeUrl(String s) {
        return response.encodeUrl(s);
    }

    public String encodeRedirectUrl(String s) {
        return response.encodeRedirectUrl(s);
    }

    public void sendError(int i, String s) throws IOException {
        response.sendError(i, s);
    }

    public void sendError(int i) throws IOException {
        response.sendError(i);
    }

    public void sendRedirect(String s) throws IOException {
        response.sendRedirect(s);
    }

    public void setDateHeader(String s, long l) {
        response.setDateHeader(s, l);
    }

    public void addDateHeader(String s, long l) {
        response.addDateHeader(s, l);
    }

    public void setHeader(String s, String s1) {
        response.setHeader(s, s1);
    }

    public void addHeader(String s, String s1) {
        response.addHeader(s, s1);
    }

    public void setIntHeader(String s, int i) {
        response.setIntHeader(s, i);
    }

    public void addIntHeader(String s, int i) {
        response.addIntHeader(s, i);
    }

    public void setStatus(int i) {
        response.setStatus(i);
    }

    public void setStatus(int i, String s) {
        response.setStatus(i, s);
    }

    public String getCharacterEncoding() {
        return response.getCharacterEncoding();
    }


    public ServletOutputStream getOutputStream() throws IOException {
        return response.getOutputStream();
    }

    public PrintWriter getWriter() throws IOException {
        return response.getWriter();
    }

    public void setContentLength(int i) {
        response.setContentLength(i);
    }


    public void setContentType(String s) {
        response.setContentType(s);
    }

    public void setBufferSize(int i) {
        response.setBufferSize(i);
    }

    public int getBufferSize() {
        return response.getBufferSize();
    }

    public void flushBuffer() throws IOException {
        response.flushBuffer();
    }

    public void resetBuffer() {
        response.resetBuffer();
    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {
        response.reset();
    }

    public void setLocale(Locale locale) {
        response.setLocale(locale);
    }

    public Locale getLocale() {
        return response.getLocale();
    }
}
