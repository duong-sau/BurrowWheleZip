import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DiRLE {
    public static byte[] encode(byte[] bytes) throws UnsupportedEncodingException {
        System.out.println("bắt đầu nén thứ");
        byte[] diListByte=new byte[bytes.length/2];
        for(int i=0; i<bytes.length;i+=2){
            diListByte[i/2]=bytes[i+1];

        }
        Row diList= new Row(diListByte);
        Row diResult=new Row(BurrowWheeler.longEncode(diList.bytes));
        byte[] len=BurrowWheeler.intTo4byte(diResult.length());
        Row length=new Row(len);

        byte[] listByte=new byte[bytes.length/2];
        for(int i=0; i<bytes.length;i+=2) {
            listByte[i/2]=bytes[i];
        }
        Row listLast=new Row(listByte);
        Row list=new Row(new byte[0]);
        list.concat(length);
        list.concat(diResult);
        list.concat(listLast);
        return list.bytes;
    }
    public static byte[] decode(byte[] bytes) throws IOException {
        System.out.println("bắt đầu giải nén thứ cấp");
        Row decodeRow=new Row(bytes);
        Row len=decodeRow.sub(0,4);
        int length= BurrowWheeler.byteToInt(len.bytes);
        System.out.println("độ dài: "+length);
        Row result=decodeRow.sub(4+length,bytes.length);
        Row diResult=decodeRow.sub(4,4+length);
        Row diList=new Row(BurrowWheeler.longDecode(diResult.bytes));
        Row decode=new Row(new byte[0]);
        byte[] resultByte=new byte[diList.length()*2];
        for(int i=0;i< resultByte.length; i+=2){
            resultByte[i]=result.bytes[i/2];
            resultByte[i+1]=diList.bytes[i/2];
        }
        System.out.print("chuõi cuối");
        return resultByte;
    }

}
