import java.util.Arrays;

public class Row implements Comparable {
    static byte[] table;
    byte[] bytes;
    public Row(byte[] var1) {
        this.bytes = var1;
    }
/*
    public int compare(Row var1, int var2) {
        if (var2 == this.bytes.length) {
            return 0;
        } else if (this.bytes[var2] > var1.bytes[var2]) {
            return 1;
        } else if (this.bytes[var2] < var1.bytes[var2]) {
            return -1;
        } else {
            return this.bytes[var2] == var1.bytes[var2] ? this.compare(var1, var2 + 1) : 0;
        }
    }


 */
public int compare(Row var1, int var2) {
    if (var2 == this.bytes.length) {
        return 0;
    } else if (Table.search(this.bytes[var2],table) > Table.search(var1.bytes[var2],table)) {
        return 1;
    } else if (Table.search(this.bytes[var2],table) < Table.search(var1.bytes[var2],table)) {
        return -1;
    } else {
        return Table.search(this.bytes[var2],table) == Table.search(var1.bytes[var2],table) ? this.compare(var1, var2 + 1) : 0;
    }
}
    public int compareTo(Object var1) {
        return this.compare((Row)var1, 0);
    }

    public void concatLast(Row var1, int var2) {
        byte[] var3 = new byte[this.bytes.length + var2];
        System.arraycopy(this.bytes, 0, var3, 0, this.bytes.length);
        System.arraycopy(var1.bytes, 0, var3, this.bytes.length, var2);
        this.bytes = var3;
    }

    public static Row concat(Row var0, Row var1) {
        Row var2 = new Row(new byte[0]);
        var2.concatLast(var0, var0.bytes.length);
        var2.concatLast(var1, var1.bytes.length);
        return var2;
    }

    public void concat(Row var1) {
        this.concatLast(var1, var1.bytes.length);
    }

    public Row sub(int var1, int var2) {
        byte[] var3 = Arrays.copyOfRange(this.bytes, var1, var2);
        return new Row(var3);
    }

    public int length() {
        return this.bytes.length;
    }
}
