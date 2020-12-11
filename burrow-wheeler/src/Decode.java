//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

public class Decode {
    public Decode() {
    }

    public static void main(String[] var0) throws Exception {
        String var1 = var0[0];
        File var2 = new File(var1);
        FileInputStream var3 = new FileInputStream(var2);
        byte[] var4 = new byte[var3.available()];
        var3.read(var4);
        var3.close();
        System.out.println("read file oke " + var1);
        Row.table = Arrays.copyOfRange(var4,0,256);
        var4= Arrays.copyOfRange(var4,256,var4.length);
        System.out.println("read chain oke");
        byte[] var5 = RLEImprove.decode(var4);
        byte[] var6 = BurrowWheeler.longDecode(var5);
        System.out.println("Finish decode, writing to file");
        FileOutputStream var7 = new FileOutputStream(var1.substring(0, var1.length() - 3));
        var7.write(var6);
        var7.close();
        System.out.println("all done");
    }
}
