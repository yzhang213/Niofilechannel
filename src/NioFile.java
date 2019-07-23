import javax.print.DocFlavor;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;



public class NioFile {
    public static void main(String[] args)  throws  Exception {
     readNIO("aaa.text");
     writeNIO("bbb.text");
    }

    public static void readNIO(String path){
        FileInputStream fileInputStream= null;
        try {
            fileInputStream= new FileInputStream(new File(path));
            FileChannel fileChannel=fileInputStream.getChannel();
            int capacity=100;
            ByteBuffer byteBuffer=ByteBuffer.allocate(capacity);
            System.out.println("限制是：" + byteBuffer.limit() + ",容量是：" + byteBuffer.capacity() + " ,位置是：" + byteBuffer.position());
            int length =-1;
            while ((length=fileChannel.read(byteBuffer))!=-1){
            byteBuffer.clear();
            byte[] bytes =byteBuffer.array();
            String str = new String(bytes,0,length);
            System.out.println(str);
            }
            fileChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static void writeNIO(String pathname){
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream =new FileOutputStream(new File(pathname));
            FileChannel fileChannel= fileOutputStream.getChannel();
            ByteBuffer byteBuffer= Charset.forName("utf-8").encode("NIO测试");
            System.out.println("初始化容量和limit：" + byteBuffer.capacity() + ","+ byteBuffer.limit());
            int length = 0;
            while ((length=fileChannel.write(byteBuffer))!=0){
                System.out.println("写入长度"+length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                       if (fileOutputStream != null) {
                                 try {
                                      fileOutputStream.close();
                                     } catch (IOException e) {
                                       e.printStackTrace();
                                   }
                        }
               }

    }
}
