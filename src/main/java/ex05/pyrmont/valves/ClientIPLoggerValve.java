package ex05.pyrmont.valves;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import java.io.IOException;

/**
 * Created by ST on 2016/12/27.
 */
public class ClientIPLoggerValve implements Valve,Contained {
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
        System.out.println("Client IP Logger Valve");
        ServletRequest sreq = request.getRequest();
        System.out.println(sreq.getRemoteAddr());
        System.out.println("-----------------------------------");
    }
}
