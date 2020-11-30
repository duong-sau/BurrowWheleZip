import java.util.Arrays;

public class MTF {
    public static int tableLength = 256;

    public static byte[] createTable (){
        byte[] table =new byte[tableLength];
        for (int i=0; i<tableLength; i++){
            table[i] = (byte) i;
        }
        return table;
    }

    public static byte[] encode(byte[] bytes){
         byte[] table=createTable();
        byte[] result = new byte[bytes.length];
        for (int i=0 ; i<bytes.length; i++){
            int index = search(table,bytes[i]);
            result[i] = (byte) index;
            table= move(table, index);
        }
        return result;
    }
    public static byte[] decode(byte[] bytes){
        byte[] table=createTable();
        byte[] result = new byte[bytes.length];
        for (int i=0 ; i<bytes.length; i++){
            int index = (int) bytes[i]&0xff;
            result[i] = table[index];
            table = move(table, index);
        }
        return result;
    }
    public static byte[] move (byte[] bytes, int index){
        byte[] result = new byte[bytes.length];
        result[0] = bytes[index];
        System.arraycopy(bytes,0,result, 1,index);
        System.arraycopy(bytes,index+1,result,index+1,bytes.length-index-1);
        return result;
    }
    public static int search(byte[] bytes, byte b){
        for (int i= 0 ; i < bytes.length; i++){
            if(b==bytes[i]){
                return i;
            }
        }
        return -1;
    }
}
