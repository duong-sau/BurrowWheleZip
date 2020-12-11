//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Encode {
    public Encode() {
    }

    public static void main(String[] var0) throws Exception {
        String var1 = var0[0];
        File var2 = new File(var1);
        FileInputStream var3 = new FileInputStream(var2);
        byte[] var4 = new byte[var3.available()];
        var3.read(var4);
        var3.close();
        System.out.println("read file oke " + var1);
        Row.table = Table.creatTable(var4);
        System.out.println("complete create chain");
        byte[] var5 = BurrowWheeler.longEncode(var4);
        byte[] var6 = RLEImprove.encode(var5);
        Row row = new Row(Row.table);
        Row row1 = new Row(var6);
        Row result = Row.concat(row, row1);
        System.out.println("Finish encode, writing to file");
        FileOutputStream var7 = new FileOutputStream(var1 + "mbr");
        System.out.print('\u000C');
        assert result.bytes != null;
        var7.write(result.bytes);
        var7.close();
        System.out.println("all done");

    }
}
