import java.util.Arrays;

public class Row implements Comparable{
    byte[] bytes;

    public Row(byte[] bytes) {
        this.bytes = bytes;
    }
    public int compare(Row row, int index){
        if(index==bytes.length){return 0;}
        if(this.bytes[index]>row.bytes[index]){
            return 1;
        }
        else if(this.bytes[index]<row.bytes[index]){
            return -1;
        }
        else if(this.bytes[index]==row.bytes[index]){
            return compare(row,index+1);
        }
        return 0;
    }
    @Override
    public int compareTo(Object o) {
        return compare((Row)o, 0);
    }
    public void concatFirst(Row row, int post, int length){
        byte[] temp=new byte[this.bytes.length+length];
        System.arraycopy(this.bytes,0,temp,length-1,this.bytes.length);
        System.arraycopy(row.bytes, 0, temp,0, length);
        this.bytes=temp;
    }
    public void concatLast(Row row, int post, int length){
        byte[] temp=new byte[this.bytes.length+length];
        System.arraycopy(this.bytes,0,temp,0,this.bytes.length);
        System.arraycopy(row.bytes,0,temp, this.bytes.length,length);
        this.bytes=temp;
    }
    public static Row concat(Row row1, Row row2){
        Row row=new Row(new byte[0]);
        row.concatLast(row1,0,row1.bytes.length);
        row.concatLast(row2,0,row2.bytes.length);
        return row;
    }
    public void concat(Row row){
        concatLast(row,0,row.bytes.length);
    }
    public Row sub(int start, int end){
        byte[] temp= Arrays.copyOfRange(bytes,start,end);
        Row row=new Row(temp);
        return row;
    }
    public int length(){
        return bytes.length;
    }
    public void print(){
        System.out.print("đối tượng row: ");
        for (byte b : bytes){
            System.out.print(b+" ");
        }
        System.out.println();
    }

    public Row(byte l, byte value) {
        if(l==0){
            System.out.println("xảy ra l=0_");
        }
        this.bytes=new byte[l&0xff];
        for (int i=0; i<(l&0xff); i++){
            bytes[i]=value;
        }
    }
}
