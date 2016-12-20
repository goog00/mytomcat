package ex03.pyrmont.startup;

import ex03.pyrmont.connector.http.HttpConnector;

/**
 * 启动类
 * Created by ST on 2016/12/7.
 */
public class Bootstrap {
    public static void main(String[] args){
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
