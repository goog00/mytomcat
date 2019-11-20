package ex05.pyrmont.valves;

import org.apache.catalina.*;
import org.apache.catalina.util.Enumerator;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author  by ST on 2016/12/27.
 */
public class HeaderLoggerValve implements Valve,Contained {

    protected Container container;
    public Container getContainer() {
        return null;
    }

    public void setContainer(Container container) {

    }

    public String getInfo() {
        return null;
    }

    public void invoke(Request request, Response response, ValveContext valveContext) throws IOException, ServletException {
        //Pass this request on to the next valve in our pipeline
        valveContext.invokeNext(request,response);

        System.out.println("Header Logger Valve");
        ServletRequest sreq = request.getRequest();
        if(sreq instanceof HttpServletRequest){
            HttpServletRequest hreq = (HttpServletRequest) sreq;
            Enumeration headerNames = hreq.getHeaderNames();
            while (headerNames.hasMoreElements()){
                String headerName = headerNames.nextElement().toString();
                String headerValue = hreq.getHeader(headerName);
                System.out.println(headerName + ":" + headerValue);
            }
        }
        else{
            System.out.println("Not an Http Request");
        }
        System.out.println("--------------------------------------");
    }
}
