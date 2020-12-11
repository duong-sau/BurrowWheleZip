//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class ArrayCopy {
    public ArrayCopy() {
    }

    public static byte[] concat(byte[] var0, byte[] var1) {
        byte[] var2 = new byte[var0.length + var1.length];
        System.arraycopy(var0, 0, var2, 0, var0.length);
        System.arraycopy(var1, 0, var2, var0.length, var1.length);
        return var2;
    }
}
