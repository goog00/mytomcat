package ex06.pyrmont.core;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

/**
 * 具体观察者实现
 * @author  by ST on 2016/12/29.
 */
public class SimpleContextLifecycleListener implements LifecycleListener {

    public void lifecycleEvent(LifecycleEvent event) {
        Lifecycle lifecycle = event.getLifecycle();
        System.out.println("SimpleContextLifecycleListener's event  " + event.getType());
        if(Lifecycle.START_EVENT.equals(event.getType())){
            System.out.println("Starting context");
        }
        else if(Lifecycle.STOP_EVENT.equals(event.getType())){
            System.out.println("Stopping context");
        }
    }
}
