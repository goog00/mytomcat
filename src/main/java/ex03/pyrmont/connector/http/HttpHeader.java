package ex03.pyrmont.connector.http;

/**
 * HTTP header enum type
 * Created by ST on 2016/12/8.
 */
final class HttpHeader {

    //---------------------------------------------------------Constants

    public static final int INITIAL_NAME_SIZE = 32;
    public static final int INITIAL_VALUE_SIZE = 64;
    public static final int MAX_NAME_SIZE = 128;
    public static final int MAX_VALUE_SIZE = 4096;

    // ----------------------------------------------------------- Constructors

    public HttpHeader(){
        this(new char[INITIAL_NAME_SIZE],0,new char[INITIAL_VALUE_SIZE],0);
    }

    public HttpHeader(char[] name, int nameEnd, char[] value, int valueEnd) {
        this.name = name;
        this.nameEnd = nameEnd;
        this.value = value;
        this.valueEnd = valueEnd;
    }

    // ----------------------------------------------------- Instance Variables

    public char[] name;
    public int nameEnd;
    public char[] value;
    public int valueEnd;
    public int hashCode = 0;

    /**
     * Release all object references,and initialize instance variables,
     * in preparation for reuse of this object
     */
    public void recycle(){
        nameEnd = 0;
        valueEnd = 0;
        hashCode = 0;
    }

    /**
     *Test if the name of the header is equal to the given char array
     * All the characters must already be lower case
     * @param buf
     * @return
     */
    public boolean equals(char[] buf){
        return equals(buf, buf.length);
    }

    /**
     * Test if the name of the header is equal to the given char array
     * All the characters must already be lower case
     * @param buf
     * @param end
     * @return
     */
    public boolean equals(char[] buf,int end){
        if(end != nameEnd){
            return false;
        }
        for(int i = 0; i < end; i++){
            if(buf[i] != name[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * Test if the name of the header is equal to the given string the String given must be made of lower case characters
     * @param str
     * @return
     */
    public boolean equals(String str){
        return equals(str.toCharArray(),str.length());
    }

    /**
     * Test if the value of the header is equal to the given char array
     * @param buf
     * @return
     */
    public boolean valueEquals(char[] buf){
        return valueEquals(buf,buf.length);
    }
    /**
     * Test if the value of the header is equal to the given char array
     * @param buf
     * @return
     */
    public boolean valueEquals(char[] buf,int end){
        if(end != valueEnd){
            return false;
        }
        for(int i = 0;i < end;i++){
            if(buf[i] != value[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * Test if the value of the header is equal to the given string
     * @param str
     * @return
     */
    public boolean valueEquals(String str){
        return valueEquals(str.toCharArray(),str.length());
    }

    /**
     * Test if the value of the header includer the given char array
     * @param buf
     * @return
     */
    public boolean valueIncludes(char[] buf){
        return valueIncludes(buf,buf.length);
    }

    /**
     * Test if the value of the header include the given char array
     * @param buf
     * @param end
     * @return
     */
    public boolean valueIncludes(char[] buf,int end){
        char firstChar = buf[0];
        int pos = 0;
        while (pos < valueEnd){
            pos = valueIndexOf(firstChar,pos);
            if(pos == -1){
                return false;
            }
            if((valueEnd - pos) < end){
                return false;
            }
            for(int i = 0; i < end; i++){
                if(value[i + pos] != buf[i]){
                    break;
                }
                if(1 == (end-1)){
                    return true;
                }
            }
            pos++;
        }
        return false;
    }


    /**
     * Returns the index of a character in the value
     * @param c
     * @param start
     * @return
     */
    public int valueIndexOf(char c,int start){
        for(int i = start; i < valueEnd;i++){
            if(value[i] == c){
                return i;
            }
        }
        return  -1;
    }

    /**
     * Test if the name of the header is equal to given header
     * All the characters in the name must already be lower case
     * @param header
     * @return
     */
    public boolean equals(HttpHeader header){
        return (equals(header.name,header.nameEnd));
    }

    /**
     * Test if the name and value of the header is equal to the given header
     * all the characters in the name must already be lower case
     * @param header
     * @return
     */
    public boolean headerEquals(HttpHeader header){
        return (equals(header.name,header.nameEnd)) && (valueEquals(header.value,header.valueEnd));
    }



    // --------------------------------------------------------- Object Methods

    /**
     * Return hash code the hash code of the HttpHeader object is the same as return by new String(name,0,nameEnd).hashCode()
     *
     * * @return
     */
    public int hashCode(){
        int h = hashCode;
        if(h == 0){
            int off = 0;
            char val[] = name;
            int len = nameEnd;
            for(int i = 0; i < len; i++){
                h = 31*h + val[off++];
            }
            hashCode = h;
        }
        return h;
    }

    public boolean equals(Object obj){
        if(obj instanceof String){
            return equals(((String) obj).toLowerCase());
        } else if(obj instanceof HttpHeader){
            return equals((HttpHeader) obj);
        }
        return false;
    }

}
