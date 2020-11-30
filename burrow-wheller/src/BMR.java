import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class BMR {
    public static void main(String[] args) throws Exception {
        //define
        ArrayList<String> names = new ArrayList<String>();
        names.add("sm.txt");
        names.add("sm.pdf");
        names.add("td.txt");
        names.add("td.pdf");
        names.add("sm.jpg");
        names.add("td.jpg");
        names.add("sm.bmp");
        names.add("td.bmp");
        for (String name : names) {
            // read File
            File file = new File("./data/"+name);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = fileInputStream.readAllBytes();
            fileInputStream.close();
            // burrow wheeler
            System.out.println("bắt đầu nén tập "+ name);
            System.out.println("tại thời điểm "+ System.currentTimeMillis());
            byte[] burrowWheeler = BurrowWheeler.longEncode(bytes);
            // MTF
            byte[] mtf = MTF.encode(burrowWheeler);
            // RLE
            byte[] rle = RLEImprove.encode(mtf);
            // write to file
            System.out.println("hoàn thành nén tập "+ name);
            System.out.println("tại thời điểm "+ System.currentTimeMillis());
            FileOutputStream zip = new FileOutputStream("./result/"+name + "bmr");
            assert rle != null;
            zip.write(rle);
            zip.close();
            System.out.println("finish encode RBR");
            System.out.println("bắt đầu giải nén tập "+ name);
            System.out.println("tại thời điểm "+ System.currentTimeMillis());
            // decode
            // RLE decode
            byte[] deRle = RLEImprove.decode(rle);
            // MTF decode
            byte[] deMTF = MTF.decode(deRle);
            // burrow wheeler decode
            System.out.println("decode RLE finish");
            byte[] deBurrowWheeler = BurrowWheeler.longDecode(deMTF);
            // write to file
            System.out.println("hoàn thành giải nén tập "+ name);
            System.out.println("tại thời điểm "+ System.currentTimeMillis());
            System.out.println("decode finish");
            FileOutputStream fileOutputStream = new FileOutputStream(name + "(decode)");
            fileOutputStream.write(deBurrowWheeler);
            fileOutputStream.close();
        }
    }
}
