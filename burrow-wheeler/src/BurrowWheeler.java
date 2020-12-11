
import java.util.ArrayList;
import java.util.Arrays;

public class BurrowWheeler {
    public static float i=0;
    public static float j=0;
    public static float k=0;
    public static int BlockSize=1024;
    public static byte[] longEncode(byte[] bytes)  {
        Row row=new Row(new byte[0]);
        ArrayList<byte[]> bytesArrayList=subBytes(bytes);
        for (byte[] b:bytesArrayList){
            Row row1=new Row(encode(b));
            row.concat(row1);
        }
        return row.bytes;
    }
    public static byte[] encode(byte[] bytes) {
        Row source= new Row(bytes);
        ArrayList<Row> rowArrayList = new ArrayList<>();
        for (int i = 0; i < bytes.length ; i++) {
            Row before = source.sub(0,i);
            Row after=source.sub(i,source.length());
            Row row=Row.concat(after,before);
            rowArrayList.add(row);
        }
        Row first= rowArrayList.get(0);
        rowArrayList.sort(Row::compareTo);
        int index=rowArrayList.indexOf(first);
        Row encodeRow=new Row(intTo4byte(index));
        for (int i=0; i<bytes.length;i++){
            Row row=rowArrayList.get(i).sub(bytes.length-1,bytes.length);
            encodeRow.concat(row);
        }
        if(encodeRow.bytes[0]==1){
            System.out.println("to long code");
        }
        return encodeRow.bytes;
    }
    public static byte[] longDecode(byte[]bytes){
        Row row=new Row(new byte[0]);
        ArrayList<byte[]> bytesArrayList=subBytesDecode(bytes);
        for (byte[] b:bytesArrayList){
            Row row1=new Row(decode(b));
            row.concat(row1);
        }
        return row.bytes;
    }
    public static byte[] decode(byte[] bytes)  {
        if(bytes.length==4){return new byte[0];}
        byte[] indexByte=Arrays.copyOfRange(bytes,0,4);
        int index= byteToInt(indexByte);
        byte[] decodeByte = Arrays.copyOfRange(bytes,4,bytes.length);
        byte[] result = new byte[BlockSize];
        ArrayList<Row> F = new ArrayList<>();
        ArrayList<Row> L = new ArrayList<>();
        for (byte b : decodeByte) {
            Row row = new Row(new byte[]{b});
            F.add(row);
            L.add(row);
        }
        F.sort(Row::compareTo);
        int ID=index;
        for (int l = 0; l < result.length; l++) {
            result[l] = F.get(ID).bytes[0];
            ID=L.indexOf(F.get(ID));
        }
        return result;
    }


    public static byte[] intTo4byte(int x){
        byte[] bytes=new byte[4];
        bytes[0] = (byte) ((x / 256 / 256 / 256) % 256);
        bytes[1] = (byte) ((x / 256 / 256) % 256);
        bytes[2] = (byte) ((x / 256) % 256);
        bytes[3] = (byte) (x % 256);
        return bytes;
    }
    public static int byteToInt(byte[] bytes){
        int x=0;
        for (int i=0; i<bytes.length;i++){
            x=x+((bytes[3-i]&0xff)*(int)Math.pow(256,i));
        }
        return x;
    }

    public static ArrayList<byte[]> subBytes(byte[] bytes){
        ArrayList<byte[]> byteArrayList=new ArrayList<>();
        int nBlock=bytes.length/BurrowWheeler.BlockSize;
        int du=bytes.length%BurrowWheeler.BlockSize;
        for (int i=0; i<nBlock;i++){
            j++;
            //System.out.println(j*100/6144+"cắt");
            byte[] temp=Arrays.copyOfRange(bytes,i*BlockSize,(i+1)*BlockSize);
            byteArrayList.add(temp);
        }
        if(du!=0){
            byte[] temp=Arrays.copyOfRange(bytes,nBlock*BlockSize,bytes.length);
            byteArrayList.add(temp);
        }
        return byteArrayList;
    }
    public static ArrayList<byte[]> subBytesDecode(byte[] bytes){
        int BloclSizeDecode=BurrowWheeler.BlockSize+4;
        ArrayList<byte[]> byteArrayList=new ArrayList<>();
        int nBlock=bytes.length/BloclSizeDecode;
        int du=bytes.length%BloclSizeDecode;
        for (int i=0; i<nBlock;i++){
            byte[] temp=Arrays.copyOfRange(bytes,i*BloclSizeDecode,(i+1)*BloclSizeDecode);
            //System.out.println("kết quản giải mã rle");
            //print(temp);
            byteArrayList.add(temp);
        }
        if(du!=0){
            byte[] temp=Arrays.copyOfRange(bytes,nBlock*BloclSizeDecode,bytes.length);
            byteArrayList.add(temp);
            //System.out.println("kết quản giải mã rle khối cuối");
            //print(temp);
        }
        return byteArrayList;
    }
}
