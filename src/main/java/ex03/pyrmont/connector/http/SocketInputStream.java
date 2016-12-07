package ex03.pyrmont.connector.http;


import org.apache.catalina.util.StringManager;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Extends InputStream to be more efficient reading during http header processing
 * Created by ST on 2016/12/7.
 */
public class SocketInputStream extends InputStream {

    private static final byte CR = (byte)'\r';

    private static final byte LF = (byte)'\n';

    private static final byte SP = (byte) ' ';

    /**
     * COLON
     */
    private static final byte COLON =(byte) ':';

    /**
     * LOWER case offset
     */
    private static final int LC_OFFSET = 'A'-'a';

    /**
     * Internal buffer.
     */
    protected byte buf[];

    /**
     * Last valid byte
     */
    protected int count;
    /**
     * Position in the buffer
     */
    protected int pos;

    /**
     * Underlying input stream
     */
    protected InputStream is;


// ----------------------------------------------------------- Constructors

    /**
     * Construct a servlet input stream associated with the specified socket input
     * @param is socket input stream
     * @param bufferSize size of the internal buffer
     */
    public SocketInputStream(InputStream is,int bufferSize){
        this.is = is;
        buf = new byte[bufferSize];
    }

    // -------------------------------------------------------------- Variables
    /**
     * the String manager for this package
     */
    protected static StringManager sm = StringManager.getManager(Constants.Package);
    // ----------------------------------------------------- Instance Variables


    // --------------------------------------------------------- Public Methods

    public void readRequestLine(HttpRequestLine requestLine) throws IOException{

        //Recycling check
        if(requestLine.methodEnd != 0){
            requestLine.recycle();
        }
        //checking for a blank line
        int chr = 0;
        do{//Skipping CR or LF
            try{
                chr = read();
            }catch (IOException e){
                chr = -1;
            }
        }while ((chr == CR) || (chr ==LF));
        if(chr == -1){
            throw new EOFException(sm.getString("requestStream.readline.error"));
        }
        pos--;

        //reading the method name

        int maxRead = requestLine.method.length;
        int readStart = pos;
        int readCount = 0;
        boolean space = false;

        while (!space){
            //if the buffer is full ,extend it
            if(readCount >= maxRead){
                if((2 * maxRead) <= HttpRequestLine.MAX_METHOD_SIZE){
//                    char[] newBuffer = new char[]
                }
            }
        }


    }


    @Override
    public int read() throws IOException {
        return 0;
    }
}
