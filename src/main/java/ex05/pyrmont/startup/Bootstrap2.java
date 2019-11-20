package ex05.pyrmont.startup;


import ex05.pyrmont.core.SimpleContext;
import ex05.pyrmont.core.SimpleContextMapper;
import ex05.pyrmont.core.SimpleLoader;
import ex05.pyrmont.core.SimpleWrapper;
import ex05.pyrmont.valves.ClientIPLoggerValve;
import ex05.pyrmont.valves.HeaderLoggerValve;
import org.apache.catalina.*;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.logger.FileLogger;

/**
 * Created by ST on 2016/12/27.
 */
public class Bootstrap2 {
    public static void main(String[] args){
        /**
         * 连接器与servlet容器的关系
         * 连接器创建request，response
         * 主容器SimpleContext，子容器SimpleWrapper
         * wrapper对应一个servlet（加载servlet）
         * 管道 与 阀 是抽象出来用来处理request请求，每个容器对应一个管道pipeline，每个pipeline对应多个阀
         * 但每个容器都会有一个默认阀（最后执行），
         */

        //create a connector
        HttpConnector connector = new HttpConnector();
        //every wrapper container have only one servlet
        Wrapper wrapper1 = new SimpleWrapper();
        wrapper1.setName("Primitive");
        wrapper1.setServletClass("PrimitiveServlet");
        Wrapper wrapper2 = new SimpleWrapper();
        wrapper2.setName("Modern");
        wrapper2.setServletClass("ModernServlet");

        //context container include wrapper child container
        Context context = new SimpleContext();
        // wrapper container is added to father container context
        context.addChild(wrapper1);
        context.addChild(wrapper2);

        // create the custom valve(阀）
        Valve valve1 = new HeaderLoggerValve();
        Valve valve2 = new ClientIPLoggerValve();
        // add into context(Pipeline)
        ((Pipeline)context).addValve(valve1);
        ((Pipeline)context).addValve(valve2);

        //servlet 映射器
        Mapper mapper = new SimpleContextMapper();
        mapper.setProtocol("http");
        //把映射器添加到容器中
        context.addMapper(mapper);
        Loader loader = new SimpleLoader();
        context.setLoader(loader);
        //将servlet配置到容器中（类似在web.xml中配置的servlet，读取到容器中去）
        context.addServletMapping("/Primitive","Primitive");
        context.addServletMapping("/Modern","Modern");
        // ------ add logger --------
        System.setProperty("catalina.base", System.getProperty("user.dir"));
        FileLogger logger = new FileLogger();
        logger.setPrefix("FileLog_");
        logger.setSuffix(".txt");
        logger.setTimestamp(true);
        logger.setDirectory("webroot");
        context.setLogger(logger);


        //connector set main context container
        connector.setContainer(context);

        try {
            //
            connector.initialize();
            connector.start();

            // make the application wait until we press a key.
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
