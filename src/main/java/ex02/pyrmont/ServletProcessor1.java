package ex02.pyrmont;

/**
 * Created by ST on 2016/12/6.
 */
public class ServletProcessor1 {

    public void process(Request request,Response response){
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);

    }
}
