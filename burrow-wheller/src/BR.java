import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BR {
    public static void main(String[] args) throws Exception {
        //define
        String name = "file";
        String extend = ".txt";
        // read File
        File file=new File(name+extend);
        FileInputStream fileInputStream=new FileInputStream(file);
        byte[] bytes= fileInputStream.readAllBytes();
        fileInputStream.close();
        // burrow wheeler
        byte[] burrowWheeler=BurrowWheeler.longEncode(bytes);
        // RLE
        byte[] rle =RLEImprove.encode(burrowWheeler);
        // write to file
        FileOutputStream zip=new FileOutputStream(name+".rbr");
        assert rle != null;
        zip.write(rle);
        zip.close();
        System.out.println("finish encode RBR");
        // decode
        // RLE decode
        byte[] deRle = RLEImprove.decode(rle);
        // burrow wheeler decode
        System.out.println("decode RLE finish");
        byte[] deBurrowWheeler=BurrowWheeler.longDecode(deRle);
        // write to file
        System.out.println("decode finish");
        FileOutputStream fileOutputStream=new FileOutputStream(name+"(decode)"+extend);
        fileOutputStream.write(deBurrowWheeler);
        fileOutputStream.close();
    }
}
