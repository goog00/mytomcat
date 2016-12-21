package ex03.pyrmont.connector.http;

import org.apache.catalina.util.ParameterMap;
import org.apache.catalina.util.RequestUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  this class copies methods from org.apache cataline.connector.HttpRequestBase and org.apache.catalina.connector.http.HttpRequestImpl.
 * Created by ST on 2016/12/8.
 */
public class HttpRequest implements HttpServletRequest{
    private String contentType;
    private int contentLength;
    private InetAddress inetAddress;
    private InputStream input;
    private String method;
    private String protocol;
    private String queryString;
    private String requestURI;
    private String serverName;
    private int serverPort;
    private Socket socket;
    private boolean requestedSessionCookie;
    private String requestSessionId;
    private boolean requestedSessionURL;

    /**
     * The request attributes for this request
     */
    protected HashMap attributes = new HashMap();
    /**
     * The authorization credentials sent with this request
     */
    protected String authorization = null;

    /**
     * The context path for this request
     */
    protected String contextPath = "";

    /**
     * the set of cookies associated with this request
     */
    protected ArrayList cookies = new ArrayList();

    /**
     * An empty collection to use for returning empty Enumerations
     * Do not add elements to this collection!
     */
    protected static ArrayList empty = new ArrayList();

    /**
     * the set of SimpleDateFormat formats to use in getDateHeader()
     */
    protected SimpleDateFormat formats[] = {
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz",Locale.US),
            new SimpleDateFormat("EEEEEE, dd-MMM-yy HH:mm:ss zzz",Locale.US),
            new SimpleDateFormat("EEE MMMM d HH:mm:ss yyyy",Locale.US)
    };

    /**
     * The HTTP headers associated with this Request,keyed by name.
     * the values are ArrayLists of the corresponding header values
     */
    protected HashMap headers = new HashMap();

    /**
     * The parsed parameters for this request.
     * this is populated only if parameter information is requested via one of the <code>getParameter()</code> family of method calls.
     * The key is the parameter name ,while the value is a String array of values for this parameter.
     * <strong>IMPLEMENTATION NOTE</strong> -Once the parameters for a particular request are parsed and stored here ,they are not modified.
     * Therefore ,application level access to the parameters need not be synchronized
     */
    protected ParameterMap parameters = null;

    /**
     * Have the parameters for this request been parsed yet?
     */
    protected boolean parsed = false;
    protected String pathInfo = null;

    /**
     * The reader that has been returned by <code>getReader</code> if any
     */
    protected BufferedReader reader = null;

    /**
     * The ServletSocketStream that has been returned by <code>getInputStream()</code> if any.
     */
    protected ServletInputStream stream;

    public HttpRequest(InputStream input) {
        this.input = input;
    }

    public void addHeader(String name,String value){
        name = name.toLowerCase();
        synchronized (headers){
            ArrayList values = (ArrayList) headers.get(name);
            if(values == null){
                values = new ArrayList();
                headers.put(name,values);
            }
            values.add(value);
        }
    }

    protected void parseParameters(){
        if(parsed){
            return;
        }
        ParameterMap results = parameters;
        if(results == null){
            results = new ParameterMap();
        }
        results.setLocked(false);
        String encoding = getCharacterEncoding();
        if(encoding == null){
            encoding = "ISO-8859-1";
        }

        //Parse any parameters special in the query String
        String queryString = getQueryString();

        try {
            RequestUtil.parseParameters(results,queryString,encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Parse any parameters specified int the input Stream
        String contentType = getContentType();
        if(contentType == null){
            contentType = "";
        }
        int semicolon = contentType.indexOf(";");
        if(semicolon >= 0){
            contentType = contentType.substring(0,semicolon).trim();
        } else{
            contentType = contentType.trim();
        }
        if("POST".equals(getMethod()) && (getContentLength() > 0) && "application/x-www-form-urlencoded".equals(contentType)){
            try {
                int max = getContentLength();
                int len = 0;
                byte buf[] = new byte[getContentLength()];
                ServletInputStream is = getInputStream();
                while (len < max){
                    int next = is.read(buf,len,max-len);
                    if(next < 0){
                        break;
                    }
                    len += next;
                }
                is.close();
                if(len < max){
                    throw new RuntimeException("Content length mismatch");
                }
                RequestUtil.parseParameters(results,buf,encoding);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //Store the final results
        results.setLocked(true);
        parsed = true;
        parameters = results;
    }

    public void addCookie(Cookie cookie){
        synchronized (cookies){
            cookies.add(cookie);
        }
    }

    /**
     * Create and return a ServletInputStream to read the content associated with this Request the default implementation creates
     * an instance of RequestStream associated with this request but this can be overridden if necessary
     * @return
     * @throws IOException
     */
    public ServletInputStream createInputStream() throws IOException{
        return (new RequestStream(this));
    }


    public InputStream getStream(){
        return input;
    }

    public boolean isRequestedSessionURL() {
        return requestedSessionURL;
    }

    public void setRequestedSessionURL(boolean requestedSessionURL) {
        this.requestedSessionURL = requestedSessionURL;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public InputStream getInput() {
        return input;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isRequestedSessionCookie() {
        return requestedSessionCookie;
    }

    public void setRequestedSessionCookie(boolean requestedSessionCookie) {
        this.requestedSessionCookie = requestedSessionCookie;
    }

    public String getRequestSessionId() {
        return requestSessionId;
    }

    public void setRequestSessionId(String requestSessionId) {
        this.requestSessionId = requestSessionId;
    }

    public String getAuthType() {
        return null;
    }

    public Cookie[] getCookies() {
        return new Cookie[0];
    }

    public long getDateHeader(String s) {
        return 0;
    }

    public String getHeader(String s) {
        return null;
    }

    public Enumeration<String> getHeaders(String s) {
        return null;
    }

    public Enumeration<String> getHeaderNames() {
        return null;
    }

    public int getIntHeader(String s) {
        return 0;
    }

    public String getMethod() {
        return method;
    }

    public String getPathInfo() {
        return null;
    }

    public String getPathTranslated() {
        return null;
    }

    public String getContextPath() {
        return null;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getRemoteUser() {
        return null;
    }

    public boolean isUserInRole(String s) {
        return false;
    }

    public Principal getUserPrincipal() {
        return null;
    }

    public String getRequestedSessionId() {
        return null;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public StringBuffer getRequestURL() {
        return null;
    }

    public String getServletPath() {
        return null;
    }

    public HttpSession getSession(boolean b) {
        return null;
    }

    public HttpSession getSession() {
        return null;
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




    public Object getAttribute(String s) {
        return null;
    }

    public Enumeration<String> getAttributeNames() {
        return null;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

    }

    public int getContentLength() {
        return 0;
    }

    public long getContentLengthLong() {
        return 0;
    }

    public String getContentType() {
        return null;
    }

    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    public String getParameter(String s) {
        return null;
    }

    public Enumeration<String> getParameterNames() {
        return null;
    }

    public String[] getParameterValues(String s) {
        return new String[0];
    }

    public Map<String, String[]> getParameterMap() {
        return null;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getScheme() {
        return null;
    }

    public String getServerName() {
        return serverName;
    }

    public int getServerPort() {
        return serverPort;
    }

    public BufferedReader getReader() throws IOException {
        return reader;
    }

    public String getRemoteAddr() {
        return null;
    }

    public String getRemoteHost() {
        return null;
    }

    public void setAttribute(String s, Object o) {

    }

    public void removeAttribute(String s) {

    }

    public Locale getLocale() {
        return null;
    }

    public Enumeration<Locale> getLocales() {
        return null;
    }

    public boolean isSecure() {
        return false;
    }

    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    public String getRealPath(String s) {
        return null;
    }

    public int getRemotePort() {
        return 0;
    }

    public String getLocalName() {
        return null;
    }

    public String getLocalAddr() {
        return null;
    }

    public int getLocalPort() {
        return 0;
    }

    public ServletContext getServletContext() {
        return null;
    }

    public boolean isAsyncStarted() {
        return false;
    }

    public boolean isAsyncSupported() {
        return false;
    }

}
