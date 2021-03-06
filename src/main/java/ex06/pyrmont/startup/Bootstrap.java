package ex06.pyrmont.startup;

import ex06.pyrmont.core.*;
import org.apache.catalina.*;
import org.apache.catalina.connector.http.HttpConnector;

/**
 * Created by ST on 2016/12/29.
 */
public class Bootstrap {

    public static void main(String[] args){
        Connector connector = new HttpConnector();
        Wrapper wrapper1 = new SimpleWrapper();
        wrapper1.setName("Primitive");
        wrapper1.setServletClass("PrimitiveServlet");
        Wrapper wrapper2 = new SimpleWrapper();
        wrapper2.setName("Modern");
        wrapper2.setServletClass("ModernServlet");

        Context context = new SimpleContext();
        context.addChild(wrapper1);
        context.addChild(wrapper2);

        //映射器
        Mapper mapper = new SimpleContextMapper();
        mapper.setProtocol("http");
        context.addMapper(mapper);


        //观察者
        LifecycleListener listener = new SimpleContextLifecycleListener();
        //把观察者注册到目标对象中
        ((Lifecycle)context).addLifecycleListener(listener);



        Loader loader = new SimpleLoader();
        context.setLoader(loader);
        context.addServletMapping("/Primitive","Primitive");
        context.addServletMapping("/Modern","Modern");
        connector.setContainer(context);

        try {
            connector.initialize();
            ((Lifecycle)connector).start();
            ((Lifecycle)context).start();

            //make the application wait until we press a key
            System.in.read();
            ((Lifecycle)context).stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
