//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Arrays;

public class RLEImprove {
    public RLEImprove() {
    }

    public static byte[] encode(byte[] var0) {
        byte[] var1 = new byte[var0.length * 2];
        int var2 = 0;

        try {
            Row var3 = new Row(new byte[1]);
            int var4 = -1;
            var3.bytes[0] = var0[0];
            byte[] var5 = new byte[var0.length + 1];
            System.arraycopy(var0, 0, var5, 0, var0.length);

            for(int var6 = 0; var6 < var5.length; ++var6) {
                if (var3.bytes[0] == var5[var6] && var4 != 254 && var6 != var5.length - 1) {
                    ++var4;
                } else {
                    ++var4;
                    byte[] var7;
                    byte[] var8;
                    if (var4 == 1) {
                        var8 = new byte[]{var3.bytes[0]};
                        var7 = var8;
                    } else if (var4 == 2) {
                        var8 = new byte[]{var3.bytes[0], 0};
                        var8[1] = var8[0];
                        var7 = var8;
                    } else {
                        var8 = new byte[4];
                        var8[0] = var3.bytes[0];
                        var8[1] = var8[0];
                        var8[2] = var8[0];
                        var8[3] = (byte)var4;
                        var7 = var8;
                    }

                    System.arraycopy(var7, 0, var1, var2, var7.length);
                    var2 += var7.length;
                    var3 = new Row(new byte[2]);
                    var3.bytes[0] = var5[var6];
                    var4 = 0;
                }
            }

            return Arrays.copyOfRange(var1, 0, var2);
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public static byte[] decode(byte[] var0) {
        byte[] var1 = new byte[var0.length + 2];
        byte[] var2 = new byte[0];
        System.arraycopy(var0, 0, var1, 0, var0.length);

        for(int var3 = 0; var3 < var0.length; ++var3) {
            byte var4 = var1[var3];
            boolean var5 = true;

            for(int var6 = 0; var6 < 3; ++var6) {
                if (var4 != var1[var3 + var6] || var3 == var0.length - 1 || var3 == var0.length - 2 || var3 == var0.length - 3) {
                    var5 = false;
                    break;
                }
            }

            if (!var5) {
                var2 = ArrayCopy.concat(var2, new byte[]{var4});
            } else {
                byte[] var7 = new byte[var1[var3 + 3] & 255];
                if ((var1[var3 + 3] & 255) == 0) {
                    throw new RuntimeException("decode improve rle ex");
                }

                Arrays.fill(var7, var4);
                var2 = ArrayCopy.concat(var2, var7);
                var3 += 3;
            }
        }

        return var2;
    }
}
