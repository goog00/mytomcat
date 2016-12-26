package ex05.pyrmont.core;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ST on 2016/12/26.
 */
public class SimpleContextValve implements Valve,Contained{
    protected Container container;


    public Container getContainer() {
        return this.container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public String getInfo() {
        return null;
    }

    public void invoke(Request request, Response response, ValveContext valveContext) throws IOException, ServletException {
        //Validate the request and response object types
        if(!(request.getRequest() instanceof HttpServletRequest) ||
                !(response.getResponse() instanceof HttpServletResponse)){
            return;//NOTE - Not much else we can do generically
        }

        //Disallow any direct access to resource under WEB-INF or META-INF
        HttpServletRequest hreq = (HttpServletRequest) request.getRequest();
        String contextPath = hreq.getContextPath();
        String requestURI = ((HttpRequest)request).getDecodedRequestURI();
        String relativeURI = requestURI.substring(contextPath.length()).toUpperCase();

        Context context = (Context) getContainer();
        //Select the Wrapper to be used for this Request
        Wrapper wrapper = null;
        try{
            wrapper = (Wrapper) context.map(request,true);
        }catch (IllegalArgumentException e){
            badRequest(requestURI, (HttpServletResponse) response.getResponse());
        }
        if(wrapper == null){
            notFound(requestURI, (HttpServletResponse) response.getResponse());
            return;
        }
        //Ask this Wrapper to process this request
        response.setContext(context);
        wrapper.invoke(request,response);
    }


    private void badRequest(String requestURI,HttpServletResponse response){
        try {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,requestURI);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void notFound(String requestURI,HttpServletResponse response){
        try {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,requestURI);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
