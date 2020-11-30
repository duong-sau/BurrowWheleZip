
import java.util.ArrayList;
import java.util.Arrays;

public class RLE {
    public static byte[] encode(byte[] bytes){
        byte[] result=new byte[bytes.length*2];
        int index=0;
        try {
            ArrayList<Row> wordList = new ArrayList<>();
            Row word = new Row(new byte[2]);
            int count = -1;
            word.bytes[0] = bytes[0];
            byte[] temp=new byte[bytes.length+1];
            System.arraycopy(bytes,0,temp,0,bytes.length);
            for (long i = 0; i < temp.length; i++) {
                if (word.bytes[0] != temp[(int)i]||count==254||i==temp.length-1) {
                    count++;
                    word.bytes[1] = (byte) count;
                    wordList.add(word);
                    result[index+1]=(byte) count;
                    result[index]=word.bytes[0];
                    word = new Row(new byte[2]);
                    word.bytes[0] = temp[(int) i];
                    count = 0;
                    index+=2;
                }
                else {
                    count++;
                }
            }
            byte[] kq = Arrays.copyOfRange(result,0,index+2);
            return kq;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static byte[] decode(byte[] bytes) {
        Row result=new Row(new byte[0]);
        for(int   i=0; i<bytes.length;i+=2){
            Row row=new Row(bytes[i+1],bytes[i]);
            result.concat(row);
        }
        return result.bytes;
    }
}
