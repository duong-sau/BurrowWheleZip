import java.util.ArrayList;

public class Table {
    public static int search(byte b, byte[] bytes){
        for (int i = 0; i < bytes.length; i++) {
            if(b==bytes[i]){
                return i;
            }
        }
        return -1;
    }

    static byte[] creatTable(byte[] bytes){
        bytes= subB(bytes);
        byte[] result = new byte[256];
        byte[] last = new byte[256];
        int index = 0;
        int index2;
        for (int i = 0; i <256 ; i++) {
            if(search((byte)i,bytes)==-1){
                last[index] = (byte)i;
                index++;
            }
        }
        index2=index;
        last[index]=bytes[0];
        Point[] points = new Point[256];
        points[index]= new Point(bytes[0],bytes[0]);
        for (int i = 0; i < 256-index; i++) {
            ArrayList<Node> nodes = new ArrayList<>();
            for (int j = 0; j < 256; j++) {
                Node node = new Node(sxp((byte)points[index2].max,(byte)j,bytes),j);
                nodes.add(node);
            }

            nodes.sort(Node::compare);
            for (int j = 0; j < 256; j++) {
                if(search((byte)nodes.get(j).n, last)==-1){
                    index2++;
                    last[index2]=(byte)nodes.get(j).n;
                    points[index2]=new Point(last[index2],getMax(last[index2],bytes));
                    break;
                }
            }
        }
        System.arraycopy(last,0,result, 256-index,index);
        System.arraycopy(last,index,result,0,256-index);
        for (int i = 0; i < 256; i++) {
            if(search((byte)i,result)==-1){
                System.out.println("error to create chain");
            }
        }
        return result;
    }
    public static int getMax(byte b, byte[] bytes){
        ArrayList<Node> nodes = new ArrayList<>();
        for (int j = 0; j < 256; j++) {
            Node node = new Node(sxp((byte)j,b,bytes),j);
            nodes.add(node);
        }
        nodes.sort(Node::compare);
        return nodes.get(0).n;
    }
    static double sxp(byte b1,byte b2, byte[] bytes){
        double count = 0.0000;
        for (int i = 0; i <bytes.length-1 ; i++) {
            if(bytes[i]==b1&& bytes[i+1]==b2){
                count++;
            }
        }
        return count/bytes.length;
    }
    public static byte[] subB(byte[] bytes){
        if(bytes.length<90001){
            return bytes;
        }
        else {
            byte[] result = new byte[90000];
            System.arraycopy(bytes,0,result,0,30000);
            System.arraycopy(bytes,bytes.length/2-15001,result,30000,30000);
            System.arraycopy(bytes,bytes.length-60001,result,60000,30000);
            return result;
        }
    }
}
