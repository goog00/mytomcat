package ex05.pyrmont.core;

import org.apache.catalina.*;

import javax.naming.directory.DirContext;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * @author  by ST on 2016/12/26.
 */
public class SimpleWrapper implements Wrapper, Pipeline {

    /**
     * the servlet instance
     */
    private Servlet instance = null;

    private String servletClass;
    /**
     * 载入servlet类要使用的载入器
     */
    private Loader loader;

    private String name;

    private SimplePipeline pipeline = new SimplePipeline(this);

    /**
     * 父容器
     */
    private Container parent = null;

    public SimpleWrapper() {
        pipeline.setBasic(new SimpleWrapperValve());
    }


    public void invoke(Request request, Response response) throws IOException, ServletException {
        pipeline.invoke(request, response);
    }


    public Servlet allocate() throws ServletException {
        //Load and initialize our instance if necessary
        if (instance == null) {
            try {
                instance = loadServlet();
            } catch (ServletException e) {
                throw e;
            } catch (Throwable e) {
                throw new ServletException("Cannot allocate a servlet instance ", e);
            }
        }
        return instance;
    }

    private Servlet loadServlet() throws ServletException {
        if (instance != null) {
            return instance;
        }
        Servlet servlet = null;
        String actualClass = servletClass;
        if (actualClass == null) {
            throw new ServletException("servlet class has not been specified");
        }
        Loader loader = getLoader();
        //Acquire an instance of the class loader to be used
        if (loader == null) {
            throw new ServletException("No loader");
        }

        ClassLoader classLoader = loader.getClassLoader();

        //Load the specified servlet class from the appropriate class loader
        Class classClass = null;

        try {
            if (classLoader != null) {
                classClass = classLoader.loadClass(actualClass);
            }
        } catch (ClassNotFoundException e) {
            throw new ServletException("Servlet class not found");
        }
        try {
            servlet = (Servlet) classClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            servlet.init(null);
        } catch (Throwable f) {
            throw new ServletException("Failed initialize servlet");
        }
        return servlet;
    }

    public Loader getLoader() {

        if (loader != null){
            return (loader);
        }

        if (parent != null) {
            return parent.getLoader();
        }
        return null;
    }

    public Valve getBasic() {
        return null;
    }

    public void setBasic(Valve valve) {

    }

    public synchronized void addValve(Valve valve) {
        pipeline.addValve(valve);
    }

    public Valve[] getValves() {
        return new Valve[0];
    }

    public void removeValve(Valve valve) {

    }

    public long getAvailable() {
        return 0;
    }

    public void setAvailable(long available) {

    }

    public String getJspFile() {
        return null;
    }

    public void setJspFile(String jspFile) {

    }

    public int getLoadOnStartup() {
        return 0;
    }

    public void setLoadOnStartup(int value) {

    }

    public String getRunAs() {
        return null;
    }

    public void setRunAs(String runAs) {

    }

    public String getServletClass() {
        return null;
    }

    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    public boolean isUnavailable() {
        return false;
    }

    public void addInitParameter(String name, String value) {

    }

    public void addInstanceListener(InstanceListener listener) {

    }

    public void addSecurityReference(String name, String link) {

    }


    public void deallocate(Servlet servlet) throws ServletException {

    }

    public String findInitParameter(String name) {
        return null;
    }

    public String[] findInitParameters() {
        return new String[0];
    }

    public String findSecurityReference(String name) {
        return null;
    }

    public String[] findSecurityReferences() {
        return new String[0];
    }

    public void load() throws ServletException {
        instance = loadServlet();
    }

    public void removeInitParameter(String name) {

    }

    public void removeInstanceListener(InstanceListener listener) {

    }

    public void removeSecurityReference(String name) {

    }

    public void unavailable(UnavailableException unavailable) {

    }

    public void unload() throws ServletException {

    }

    public String getInfo() {
        return null;
    }



    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    public Logger getLogger() {
        return null;
    }

    public void setLogger(Logger logger) {

    }

    public Manager getManager() {
        return null;
    }

    public void setManager(Manager manager) {

    }

    public Cluster getCluster() {
        return null;
    }

    public void setCluster(Cluster cluster) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ClassLoader getParentClassLoader() {
        return null;
    }

    public void setParentClassLoader(ClassLoader parent) {

    }

    public Realm getRealm() {
        return null;
    }

    public void setRealm(Realm realm) {

    }

    public Container getParent() {
        return parent;
    }

    public void setParent(Container container) {
        parent = container;
    }

    public DirContext getResources() {
        return null;
    }

    public void setResources(DirContext resources) {

    }

    public void addChild(Container child) {

    }

    public void addContainerListener(ContainerListener listener) {

    }

    public void addMapper(Mapper mapper) {

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    public Container findChild(String name) {
        return null;
    }

    public Container[] findChildren() {
        return new Container[0];
    }

    public ContainerListener[] findContainerListeners() {
        return new ContainerListener[0];
    }

    public Mapper findMapper(String protocol) {
        return null;
    }

    public Mapper[] findMappers() {
        return new Mapper[0];
    }


    public Container map(Request request, boolean update) {
        return null;
    }

    public void removeChild(Container child) {

    }

    public void removeContainerListener(ContainerListener listener) {

    }

    public void removeMapper(Mapper mapper) {

    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }
}
