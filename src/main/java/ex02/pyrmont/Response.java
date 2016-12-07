package ex02.pyrmont;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * Created by ST on 2016/12/2.
 */
public class Response implements ServletResponse {

    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;
    PrintWriter writer;

    public Response(OutputStream output){
        this.output = output;
    }
    public void setRequest(Request request){
        this.request = request;
    }

    /**
     * This method is used to serve static pages
     * @throws IOException
     */
    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            File file = new File(Constants.WEB_ROOT,request.getUri());
            fis = new FileInputStream(file);
            /**
             * HTTP Response = Status-line ((general-header | response-header | entity-header) CRLF))
             */
            int ch = fis.read(bytes,0,BUFFER_SIZE);
            while(ch != -1){
                output.write(bytes,0,ch);
                ch = fis.read(bytes,0,BUFFER_SIZE);
            }
        }catch (Exception e){
            String errorMessage = "HTTP/1.1 404 File Not Found \r\n"+
                    "Content-Type:text/html\r\n"+
                    "Content-Length:23\r\n"+
                    "\r\n"+
                    "<h1>File Not Found</h1>";
        }finally {
            if(fis != null){
                fis.close();
            }
        }



    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        writer = new PrintWriter(output,true);
        return writer;
    }

    public void setCharacterEncoding(String s) {

    }

    public void setContentLength(int i) {

    }

    public void setContentLengthLong(long l) {

    }

    public void setContentType(String s) {

    }

    public void setBufferSize(int i) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale locale) {

    }

    public Locale getLocale() {
        return null;
    }
}
