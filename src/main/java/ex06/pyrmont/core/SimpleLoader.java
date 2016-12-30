package ex06.pyrmont.core;

import org.apache.catalina.*;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by ST on 2016/12/26.
 */
public class SimpleLoader implements Loader ,Lifecycle {
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "webroot";

    ClassLoader classLoader = null;
    Container container = null;

    public SimpleLoader(){
        URL[] urls = new URL[1];
        URLStreamHandler streamHandler = null;
        File classPath = new File(WEB_ROOT);
        try {
            String repository = (new URL("file",null,classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null,repository,streamHandler);
            classLoader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public DefaultContext getDefaultContext() {
        return null;
    }

    public void setDefaultContext(DefaultContext defaultContext) {

    }

    public boolean getDelegate() {
        return false;
    }

    public void setDelegate(boolean delegate) {

    }

    public String getInfo() {
        return null;
    }

    public boolean getReloadable() {
        return false;
    }

    public void setReloadable(boolean reloadable) {

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    public void addRepository(String repository) {

    }

    public String[] findRepositories() {
        return new String[0];
    }

    public boolean modified() {
        return false;
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }

    //implementation of the Lifecycle interface's method
    public void addLifecycleListener(LifecycleListener listener) {

    }

    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];
    }

    public void removeLifecycleListener(LifecycleListener listener) {

    }

    public void start() throws LifecycleException {
        System.out.println("Starting SimpleLoader");
    }

    public void stop() throws LifecycleException {

    }
}
