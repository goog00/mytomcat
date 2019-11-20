package ex05.pyrmont.core;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author  by ST on 2016/12/26.
 */
public class SimplePipeline implements Pipeline {


    /**
     * The basic Valve (if any) associated with this Pipeline
     */
    protected Valve basic = null;
    /**
     * the Container with which this Pipeline is associated
     */
    protected Container container = null;

    protected Valve[] valves = new Valve[0];


    /**
     * 设置容器
     * @param container 容器
     */
    public SimplePipeline(Container container) {
        setContainer(container);
    }

    public void setContainer(Container container) {
        this.container = container;
    }


    public Valve getBasic() {
        return basic;
    }

    public void setBasic(Valve valve) {
        this.basic = valve;
        //设置valve关联的容器
        ((Contained) valve).setContainer(container);
    }

    public void addValve(Valve valve) {
        if (valve instanceof Contained){
            ((Contained) valve).setContainer(this.container);
        }

        synchronized (valves) {
            Valve[] results = new Valve[valves.length + 1];
            System.arraycopy(valves, 0, results, 0, valves.length);
            results[valves.length] = valve;
            valves = results;
        }
    }


    public Valve[] getValves() {
        return valves;
    }

    public void invoke(Request request, Response response) throws IOException, ServletException {
        //Invoke the first Valve in this pipeline for this request
        (new SimplePipelineValveContext()).invokeNext(request, response);
    }

    public void removeValve(Valve valve) {

    }

    protected class SimplePipelineValveContext implements ValveContext {

        protected int stage = 0;

        public String getInfo() {
            return null;
        }

        public void invokeNext(Request request, Response response) throws IOException, ServletException {

            int subscript = stage;
            stage = stage + 1;

            //Invoke the requested valve for the current request thread
            if (subscript < valves.length) {

                valves[subscript].invoke(request, response, this);

            }else if ((subscript == valves.length) && (basic != null)) {

                basic.invoke(request, response, this);

            } else {
                throw new ServletException("No valve");
            }
        }
    }
}
