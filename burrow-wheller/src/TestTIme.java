import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class TestTIme {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("./data/sm.txt"));
        FileWriter fileWriter=new FileWriter(new File("resultTimes.txt"));
        byte[] bytes=fileInputStream.readAllBytes();
        for (int i=200; i<1024; i+=10 ) {
            BurrowWheeler.BlockSize =i;
            long start = System.currentTimeMillis();
            byte[] burrowWheeler = BurrowWheeler.longEncode(bytes);
            // MTF
            byte[] mtf = MTF.encode(burrowWheeler);
            // RLE
            byte[] rle = RLEImprove.encode(mtf);
            // write to file
            long finish = System.currentTimeMillis();
            long times =finish-start;
            System.out.print(i+" "+ times+"\n");
            fileWriter.write(i+" "+ times+" "+rle.length+ "\n");
        }
        fileWriter.close();
    }
}
