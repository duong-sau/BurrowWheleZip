
import java.util.Arrays;

public class RLEImprove {
    public static byte[] encode(byte[] bytes){
        byte[] result=new byte[bytes.length*2];
        int index=0;
        try {
            Row word = new Row(new byte[1]);
            int count = -1;
            word.bytes[0] = bytes[0];
            byte[] temp=new byte[bytes.length+1];
            System.arraycopy(bytes,0,temp,0,bytes.length);
            for (int i = 0; i < temp.length; i++) {
                if (word.bytes[0] != temp[i]||count==254||i==temp.length-1) {
                    if(count==254){
                        System.out.println("---------------------------------------------------------------------------");
                    }
                    count++;
                    byte[] c;
                    if(count==1){
                        byte[] c1 = new byte[1];
                        c1[0] = word.bytes[0];
                        c = c1;
                    }
                    else if(count==2){
                        byte[] c2 = new byte[2];
                        c2[0] = word.bytes[0];
                        c2[1] = c2[0];
                        c = c2;
                    }else {
                        byte[] c3 = new byte[4];
                        c3[0] = word.bytes[0];
                        c3[1] = c3[0];
                        c3[2] = c3[0];
                        c3[3] = (byte) count;
                        c = c3;
                    }
                    System.arraycopy(c,0,result,index, c.length);
                    index=index+c.length;
                    word = new Row(new byte[2]);
                    word.bytes[0] = temp[ i];
                    count = 0;
                }
                else {
                    count++;
                }
            }
            byte[] kq = Arrays.copyOfRange(result,0,index);
            return kq;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static byte[] decode(byte[] bytes) {
        byte[] temp=new byte[bytes.length+2];
        byte[] result = new byte[0];
        System.arraycopy(bytes,0,temp,0,bytes.length);
        for(int i=0; i < bytes.length; i++){
            byte current = temp[i];
            int c=1;
            for (int j=0; j < 3; j++){
                if(current!=temp[i+j]||i==bytes.length-1||i==bytes.length-2||i==bytes.length-3){
                    c=0;
                    break;
                }
            }
            if(c==0){
                result = ArrayCopy.concat(result,new byte[]{current} );
            }
            else {
                byte[] t = new byte[temp[i+3]&0xff];
                if((temp[i+3]&0xff)==0){
                    throw new RuntimeException("decode improve rle ex");
                }
                Arrays.fill(t, current);
                result = ArrayCopy.concat(result,t );
                i = i+3;
            }
        }
        return result;
    }
}
