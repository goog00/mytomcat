package ex05.pyrmont.core;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import java.io.IOException;

/**
 * Created by ST on 2016/12/26.
 */
public class SimpleWrapperValve implements Valve,Contained {
    protected Container container;

    public Container getContainer() {
        return null;
    }

    public void setContainer(Container container) {

    }

    public String getInfo() {
        return null;
    }

    public void invoke(Request request, Response response, ValveContext context) throws IOException, ServletException {
        SimpleWrapper wrapper = (SimpleWrapper) getContainer();
    }
}
