package ex05.pyrmont.startup;

import ex05.pyrmont.core.SimpleLoader;
import ex05.pyrmont.core.SimpleWrapper;
import ex05.pyrmont.valves.ClientIPLoggerValve;
import ex05.pyrmont.valves.HeaderLoggerValve;
import org.apache.catalina.*;
import org.apache.catalina.connector.http.HttpConnector;

/**
 * Created by ST on 2016/12/27.
 */
public class Bootstrap1 {

    public static void main(String[] args){
        HttpConnector connector = new HttpConnector();
        Wrapper wrapper = new SimpleWrapper();
        wrapper.setServletClass("ModernServlet");
        Loader loader = new SimpleLoader();
        Valve valve1 = new HeaderLoggerValve();
        Valve valve2 = new ClientIPLoggerValve();

        wrapper.setLoader(loader);
        ((Pipeline)wrapper).addValve(valve1);
        ((Pipeline)wrapper).addValve(valve2);

        connector.setContainer(wrapper);

        try {
            connector.initialize();
            connector.start();

            //make the application wait until we press a key
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
