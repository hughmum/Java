

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209221854926.png" alt="image-20220922185443546" style="zoom: 33%;" />

# 文件

保存数据的地方

### 文件流

文件在程序中是以流的形式来操作的

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209221856896.png" alt="image-20220922185631727" style="zoom:25%;" />

人喝水， 输入流

水吐杯，输出流

# 创建文件

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209230946775.png" alt="image-20220923094622518" style="zoom: 33%;" />

```java
public class FileDemo {
    public static void main(String[] args) {

    }

    //方式1 new File(String pathname)
    @Test
    public void create01() {
        String filePath = "C:\\Users\\muyub\\Desktop\\new.txt";
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件创建成功");
    }
    //方式二 new File(File parent, String child)//根据父目录文件+子路径创建
    @Test
    public void create02() throws IOException {
        File parentFile = new File("C:\\Users\\muyub\\Desktop\\");
        String fileName = "new.txt";
        File file = new File(parentFile, fileName);//这个只是创建对象还在内存里面
        file.createNewFile();//这个才是真正的创建文件
        System.out.println("创建成功");
    }
    //方式三 new File(String parent, String child)
    @Test
    public void create03() throws IOException {
        String parentFile = "C:\\Users\\muyub\\Desktop\\";
        String fileName = "new.txt";
        File file = new File(parentFile, fileName);//这个只是创建对象还在内存里面
        file.createNewFile();//这个才是真正的创建文件
        System.out.println("创建成功");
    }
}
```

# 获取文件信息

```java
public class FileDemo {
    public static void main(String[] args) {
        //先创建文件对象
        File file = new File("C:\\Users\\muyub\\Desktop\\new.txt");
//调用相应的方法，得到对应信息
        System.out.println("文件名字=" + file.getName());
//getName、getAbsolutePath、getParent、length、exists、isFile、isDirectory
        System.out.println("文件绝对路径=" + file.getAbsolutePath());
        System.out.println("文件父级目录=" + file.getParent());
        System.out.println("文件大小(字节)=" + file.length());
        System.out.println("文件是否存在=" + file.exists());//T
        System.out.println("是不是一个文件=" + file.isFile());//T
        System.out.println("是不是一个目录=" + file.isDirectory());
    }
}
```

# 目录操作

mkdir()

mkdirs()

# IO流原理和分类



InputStream 和 OutputStream、Reader和Writer都是抽象类，只能通过实现子类来创建相应的流对象

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209231038028.png" alt="image-20220923103828853" style="zoom:50%;" />

# FileInputStream

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209231654241.png" alt="image-20220923165423182" style="zoom: 67%;" />



- FileInputStream :文件输入流

- BufferedInputStream ：缓冲字节输入流

- ObjectInputStream :对象字节输入流

	

**使用FileInputStream 读取txt文件，并将内容打印控制台**

```java
public class FileDemo {
    public static void main(String[] args) {

    }

    /**
     * 演示读取文件
     * 单个字节的读取
     * 使用read Byte方式优化
     */
    @Test
    public void readFile01() {
        String filePath = "C:\\Users\\muyub\\Desktop\\new.txt";
        int readData = 0;
        FileInputStream fileInputStream = null;
        try {
            //创建了fileinputstream对象，用于读取文件
            fileInputStream = new FileInputStream(filePath);
            //从该输入流读取一个字节的数据。 如果没有输入可用，此方法将阻止。
            //数据的下一个字节，如果达到文件的末尾， -1
            while ((readData = fileInputStream.read()) != -1) {
                System.out.print((char)readData);//转成char显示，他只会读取一个字节，所以汉字就是乱码
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//在finally中一定要关闭这个流，因为流是一种资源，资源浪费
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用read(byte[] b)读取文件提高效率
     */
    @Test
    public void readFile02() {
        String filePath = "C:\\Users\\muyub\\Desktop\\new.txt";
        int readLen = 0;//实际读取到的字节数，因为read byte不一定读取8个，结尾处有可能不够8个
        //字节数组
        byte[] buf = new byte[8];//一次读取8个字节
        FileInputStream fileInputStream = null;
        try {
            //创建了fileinputstream对象，用于读取文件
            fileInputStream = new FileInputStream(filePath);
            //从该输入流读取最多b.length字节的数据到字节数组。 此方法将阻塞，直到某些输入可用。
            //末尾， -1
            //如果读取正常，返回实际读取到的字节数
            while ((readLen = fileInputStream.read(buf)) != -1) {
                System.out.println(new String(buf, 0, readLen));//buf转为字符串显示
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//在finally中一定要关闭这个流，因为流是一种资源，资源浪费
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

# FileOutputStream

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209231719788.png" alt="image-20220923171942715" style="zoom: 33%;" />



**将内容写到txt文件中，如果文件不存在，自动创建他**

```java
    /**
     * 演示使用FileOutPutStream写
     * 如果不存在，直接创建
     */
    @Test
    public void writeFile() {
        String filePath = "C:\\Users\\muyub\\Desktop\\a.txt";
        FileOutputStream fileOutputStream = null;

        try {
            //1.new FileOutputStream(filePath)这个创建方式是覆盖，
            //2.new FileOutputStream(filePath, true) 这种是追加到文件末尾
            fileOutputStream = new FileOutputStream(filePath, true);
//            //写入一个字节
//            fileOutputStream.write('M');
            //写入字符串
            String str = "mumm沐";
            //str.getBytes()可以把 字符串 -> 字节数组
            fileOutputStream.write(str.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
```

# 文件拷贝

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209231742522.png" alt="image-20220923174217406" style="zoom: 50%;" />

```java
    @Test
    public void FileCopy() {
        String srcPath = "C:\\Users\\muyub\\Desktop\\QQ图片20220922122956.jpg";
        String copyPath = "C:\\Users\\muyub\\Desktop\\QQ图片202209221229561.jpg";
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(srcPath);
            fileOutputStream = new FileOutputStream(copyPath);
            //定义一个字节数组，提高读取效果
            byte[] buf = new byte[1024];
            int readLen = 0;
            while ((readLen = fileInputStream.read(buf)) != -1) {
                fileOutputStream.write(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
```

# 文件字符流

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209231827813.png" alt="image-20220923182729699" style="zoom: 25%;" />

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209231843293.png" alt="image-20220923184334733" style="zoom: 25%;" />

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209231850497.png" alt="image-20220923185055815" style="zoom:25%;" />

### FileReader

```java
public class FileReader_ {
    public static void main(String[] args) {

    }

    /**
     * 单个字符读取文件
     */
    @Test
    public void readFile01() {
        String filepath = "C:\\Users\\muyub\\Desktop\\new.txt";
        FileReader fileReader = null;
        int data = 0;
        //1.创建FileReader对象
        try {
            fileReader = new FileReader(filepath);
            //循环读取，单个字符
            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 字符数组读取文件
     */
    @Test
    public void readFile02() {
        String filepath = "C:\\Users\\muyub\\Desktop\\new.txt";
        FileReader fileReader = null;
        int readLen = 0;
        char[] buf = new char[8];
        //1.创建FileReader对象
        try {
            fileReader = new FileReader(filepath);
            //循环读取,使用read buf返回的是实际读取到的字符数
            while ((readLen = fileReader.read(buf)) != -1) {
                System.out.print(new String(buf, 0, readLen));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```



### FileWriter

```java
  @Test
    public void writerFile01() {
        String filePath = "e:\\note.txt";
//创建 FileWriter 对象
        FileWriter fileWriter = null;
        char[] chars = {'a', 'b', 'c'};
        try {
            fileWriter = new FileWriter(filePath);//默认是覆盖写入
// 3) write(int):写入单个字符
            fileWriter.write('H');
// 4) write(char[]):写入指定数组
            fileWriter.write(chars);
// 5) write(char[],off,len):写入指定数组的指定部分
            fileWriter.write("韩顺平教育".toCharArray(), 0, 3);
// 6) write（string）：写入整个字符串
            fileWriter.write(" 你好北京~");
            fileWriter.write("风雨之后，定见彩虹");
// 7) write(string,off,len):写入字符串的指定部分
            fileWriter.write("上海天津", 0, 2);
//在数据量大的情况下，可以使用循环操作. } catch (IOException e) {
            e.printStackTrace();
        } finally {
//对应 FileWriter , 一定要关闭流，或者 flush 才能真正的把数据写入到文件
//老韩看源码就知道原因.
//
/*
            private void writeBytes () throws IOException {
                this.bb.flip();
                int var1 = this.bb.limit();
                int var2 = this.bb.position();
                assert var2 <= var1;
                int var3 = var2 <= var1 ? var1 - var2 : 0;
                if (var3 > 0) {
                    if (this.ch != null) {
                        assert this.ch.write(this.bb) == var3 : var3;
                    } else {
                        this.out.write(this.bb.array(), this.bb.arrayOffset() + var2, var3);

                    }
                }
                this.bb.clear();
            }
*/
            try {
//fileWriter.flush();
//关闭文件流，等价 flush() + 关闭
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("程序结束...");

    }
```

# 节点流和处理流

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209241416146.png" alt="image-20220924141605030" style="zoom: 50%;" />

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209232134057.png" alt="image-20220923213417217" style="zoom: 25%;" />

包装流，BufferedReader/Writer 对结点流进行包装，

```java
public class BufferedReader extends Reader {

    private Reader in;

    private char cb[];
    private int nChars, nextChar;

    private static final int INVALIDATED = -2;
    private static final int UNMARKED = -1;
    private int markedChar = UNMARKED;
    private int readAheadLimit = 0; /* Valid only when markedChar > 0 */
```

类中有属性Reader，可以封装节点流，可以是文件数组其他数据源，该节点流可以是任意的，只要是Reader的子类就可以

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209232138003.png" alt="image-20220923213809902" style="zoom:25%;" />

**修饰器模式**



### 区别

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209241043527.png" alt="image-20220924104333778" style="zoom: 33%;" />



### BufferedReader底层模拟（修饰器模式）

```java
public class Test {
    public static void main(String[] args) {
        BufferedReader_ bufferedReader_ = new BufferedReader_(new FileReader_());
        bufferedReader_.readFiles(10);
        bufferedReader_.readFile();
        BufferedReader_ bufferedReader_2 = new BufferedReader_(new StringReader_());
        bufferedReader_2.readStrings(2);
    }
}

abstract class Reader_ {//抽象类
    public void readFile(){}
    public void readString(){}
}

/**
 * 包装流处理流
 */
class BufferedReader_ extends Reader_ {
    private Reader_ reader_;

    public BufferedReader_(Reader_ reader_) {
        this.reader_ = reader_;
    }
    //让方法更加灵活，多次读取文件
    public void readFiles(int num) {
        for (int i = 0; i < num; i++) {
            reader_.readFile();
        }
    }
    //拓展readString，批量处理字符串数据
    public void readStrings(int num) {
        for (int i = 0; i < num; i++) {
            reader_.readString();
        }
    }
}

/**
 * 节点流
 */
class FileReader_ extends Reader_ {
    @Override
    public void readFile() {
        System.out.println("对文件读取");
    }
}

class StringReader_ extends  Reader_ {
    @Override
    public void readString() {
        System.out.println("读取字符串");
    }
}
```

也可以在抽象列Reader_中统一只用一个read抽象方法，通过子类重写实现，然后在test中调用向上转型后的类，调用read，动态绑定到运行类型中重写的方法里，就可以

```java
package mu;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        BufferedReader_ bufferedReader_ = new BufferedReader_(new FileReader_());
        bufferedReader_.readFiles(10);
        bufferedReader_.read();
        BufferedReader_ bufferedReader_2 = new BufferedReader_(new StringReader_());
        bufferedReader_2.readStrings(2);
    }
}

abstract class Reader_ {//抽象类
//    public void readFile(){}
//    public void readString(){}
    //统一用read方法
    public  abstract  void read();
}

/**
 * 包装流处理流
 */
class BufferedReader_ extends Reader_ {
    private Reader_ reader_;

    public BufferedReader_(Reader_ reader_) {
        this.reader_ = reader_;
    }
    //让方法更加灵活，多次读取文件
    public void readFiles(int num) {
        for (int i = 0; i < num; i++) {
            reader_.read();
        }
    }
    //拓展readString，批量处理字符串数据
    public void readStrings(int num) {
        for (int i = 0; i < num; i++) {
            reader_.read();
        }
    }

    @Override
    public void read() {
        reader_.read();
    }
}

/**
 * 节点流
 */
class FileReader_ extends Reader_ {
    @Override
    public void read() {
        System.out.println("对文件读取");
    }
}

class StringReader_ extends  Reader_ {
    @Override
    public void read() {
        System.out.println("读取字符串");
    }
}
```

关闭处理流的时候，底层会自动关闭他的节点流。

# BufferedReader使用

```java
public class Test {
    public static void main(String[] args) throws IOException {
       String filePath = "C:\\Users\\muyub\\Desktop\\new.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line;//按行读取
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }
}
```

底层关闭

```java
public void close() throws IOException {
        synchronized (lock) {
            if (in == null)
                return;
            try {
                in.close();
            } finally {
                in = null;
                cb = null;
            }
        }
    }
```

# BufferedWriter

```java
public class Test {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\muyub\\Desktop\\new.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
        bufferedWriter.write("h111ll");
        bufferedWriter.write("h111ll");
        bufferedWriter.newLine();//插入一个换行符
        bufferedWriter.write("h111ll");
        bufferedWriter.close();
    }
}
```

# Buffered文件拷贝

```java
public class Test {
    public static void main(String[] args)  {
        //br bw是按照字符读取操作的，不要操作二进制文件【声音视频图片】，可能会造成文件损坏
        String srcPath = "C:\\Users\\muyub\\Desktop\\new.txt";
        String copyPath = "C:\\Users\\muyub\\Desktop\\new1.txt";
        BufferedReader br = null;
        BufferedWriter bw = null;
        String line;
        try {
            br = new BufferedReader(new FileReader(srcPath));
            bw = new BufferedWriter(new FileWriter(copyPath));
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
```

# Buffered字节流

在BufferedInputStream这个处理流里面去使用实现了InputStream这个抽象类的实现子类的这些节点流





<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209241222233.png" alt="image-20220924122203784" style="zoom: 25%;" />



# 字节处理流拷贝文件

```java
public class Test {
    public static void main(String[] args)  {
        //br bw是按照字符读取操作的，不要操作二进制文件【声音视频图片】，可能会造成文件损坏
        String srcPath = "C:\\Users\\muyub\\Desktop\\QQ图片20220922122956.jpg";
        String copyPath = "C:\\Users\\muyub\\Desktop\\QQ图片2022092212295622.jpg";
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcPath));
            bos = new BufferedOutputStream(new FileOutputStream(copyPath));
            //循环读取文件，并写入到copyPath中
            byte[] bytes = new byte[1024];
            int readLen = 0;
            while ((readLen = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, readLen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
```



# 对象流

**在保存一个数据的值的时候，还要把他的数据类型保存起来**，即序列化与反序列化

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209241314109.png" alt="image-20220924131457843" style="zoom:33%;" />

> Serializable 是一个标记接口，没有方法，推荐使用

> Externalizable 该接口有方法需要实现，不推荐

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209241319572.png" alt="image-20220924131954254" style="zoom: 33%;" />

### 序列化案例

```java
public class Test {
    public static void main(String[] args) throws IOException {
        //序列化后，保存的文件格式不是纯文本，而是按照它的格式来保存
        String srcPath = "C:\\Users\\muyub\\Desktop\\a.txt";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(srcPath));
        //序列化数据到 e:\data.dat
        oos.writeInt(100);// int -> Integer (实现了 Serializable)
        oos.writeBoolean(true);// boolean -> Boolean (实现了 Serializable)
        oos.writeChar('a');// char -> Character (实现了 Serializable)
        oos.writeDouble(9.5);// double -> Double (实现了 Serializable)
        oos.writeUTF("韩顺平教育");//String
        //保存一个dog对象
        oos.writeObject(new Dog("jack",11));
        oos.close();
        System.out.println("完成");
    }
}

class Dog implements Serializable{
    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

### **反序列化**

```java
public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化后，保存的文件格式不是纯文本，而是按照它的格式来保存
        String srcPath = "C:\\Users\\muyub\\Desktop\\a.txt";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(srcPath));
        // 2.读取， 注意顺序
        System.out.println(ois.readInt());
        System.out.println(ois.readBoolean());
        System.out.println(ois.readChar());
        System.out.println(ois.readDouble());
        System.out.println(ois.readUTF());
        Object dog = ois.readObject();
        System.out.println(dog);
        // 3.关闭
        ois.close();
        System.out.println("以反序列化的方式读取(恢复)ok~");
    }
}
class Dog implements Serializable{
    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

### 注意事项

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209241353070.png" alt="image-20220924135340812" style="zoom:33%;" />

# 标准输入输出流

```java
System.in是下面的
```

# 转换流

把字节流转换为字符流，

文件会出现乱码问题，编码方式不同 ，而字节流是可以指定编码方式的，再转为字符流，就可以顺利读取。

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209242239713.png" alt="image-20220924223946591" style="zoom: 33%;" />

![image-20220924224345496](https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209242243789.png)

### InputStreamReader

```java
/**
 * 将字节流FIleInputStream转为字符流InputStreamReader  指定编码gbk
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        String filePath = "C:\\Users\\muyub\\Desktop\\new1.txt";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "gbk");
        BufferedReader bufferedReader = new BufferedReader(isr);
        String s = bufferedReader.readLine();
        System.out.println(s);
        bufferedReader.close();
    }
}
```

### OutputStreamWriter

```java
/**
 * 将FilePutputStream 字节流转为字符流，OutputStreamWriter
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        String filePath = "C:\\Users\\muyub\\Desktop\\new1.txt";
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath),"gbk");
        osw.write("dsfdsfsfds");
        osw.close();
    }
}
```

# 打印流

PrintStream实际上是一个字节流，因为他是OutputStream的子类，而OutputSteam时所有字节流的顶级父类，他是一个抽象类

PrintWriter 字符流，

![image-20220924230057919](https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209242300191.png)

### 字节打印流 PrintStream

```java
/**
 * 演示PrintStream 字节打印流/输出流
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        PrintStream out = System.out;
        //在默认情况下，PrintStream 输出数据的位置时 标准输出，即显示器
        /*
            public void print(String s) {
                if (s == null) {
                    s = "null";
                }
                 write(s);
            }
         */
        out.print("ling");
        //print底层使用的时write，所以我们可以直接调用write进行打印输出
        out.write("沐沐".getBytes(StandardCharsets.UTF_8));
        //修改打印流输出的位置/设备
        out.close();
        /*
            public static void setOut(PrintStream out) {
                checkIO();
                setOut0(out);//native方法修改了out
            }

         */
        System.setOut(new PrintStream("C:\\Users\\muyub\\Desktop\\new1.txt"));
        System.out.println("mumu");
    }
}
```

### 字符打印流 PrintWriter

```java
/**
 * 演示PrintWriter
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
//        PrintWriter printWriter = new PrintWriter(System.out);
        PrintWriter printWriter = new PrintWriter(new FileWriter("C:\\Users\\muyub\\Desktop\\new1.txt"));
        printWriter.print("sdf");
        printWriter.close();//flush和关闭流
    }
}
```

# Properties

```java
    public static void main(String[] args) throws InterruptedException, IOException {
        //创建properties对象
        Properties properties = new Properties();
        properties.load(new FileReader("D:\\code\\java\\learn\\hsp\\practice\\src\\mysql.properties"));
        properties.list(System.out);//把kv显示在控制台
        String ip = properties.getProperty("ip");
        String name = properties.getProperty("name");
        System.out.println(ip+" " + name);
    }
```

# 作业

<img src="https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202209251125721.png" alt="image-20220925112459455" style="zoom: 33%;" />

```java
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        String directoryPath = "C:\\Users\\muyub\\Desktop\\myfile";//目录路径
        File file = new File(directoryPath);
        //判断当前目录是否存在
        if (!file.exists()) {
            //创建
            if (file.mkdirs()) {
                System.out.println("创建成功");
            } else {
                System.out.println("创建失败");
            }
        }

        String filePath = directoryPath + "\\hello.txt";//文件路径
        file = new File(filePath);
        if (!file.exists()) {
            //创建文件
            if (file.createNewFile()) {
                System.out.println("创建成功");
                //文件存在了
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));//先通过字符流，然后经过处理流来操作
                bufferedWriter.write("heelo");
                bufferedWriter.close();
            } else {
                System.out.println("创建失败");
            }
        } else {
            //存在
            System.out.println("已经存在");
        }

    }
}
```

```java
//使用转换流
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        /**
         * 使用BufferedReader读取一个文件，为每一行加上行号，并输出屏幕
         */
        String filePah = "C:\\Users\\muyub\\Desktop\\new1.txt";
        BufferedReader bufferedReader = null;
        String line = "";
        int lineNum = 0;
//        bufferedReader =  new BufferedReader(new FileReader(filePah));
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePah), "gbk"));
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(++lineNum + "" + line);
        }
        bufferedReader.close();
    }
}
```

```java
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        /**
         * properties 读取其，然后对于对象初始化，输出
         * 将创建的dog对象序列化到文件
         */
        String filePath = "src//mysql.properties";
        Properties properties = new Properties();
        properties.load(new FileReader(filePath));
        int ip = Integer.parseInt(properties.get("ip") + "");
        String name = properties.get("name") +"";

        my m = new my(ip, name);
        //将创建的my对象，序列化放入到dat文件
        String serFilePath = "C:\\Users\\muyub\\Desktop\\nex1.dat";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serFilePath));
        oos.writeObject(m);
        oos.close();
        System.out.println("序列化完成");
        //反序列化
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(serFilePath));
        my y = (my)objectInputStream.readObject();
        System.out.println(y);
    }
}

class my implements Serializable{
    private Integer ip;
    private String name;

    public my(Integer ip, String name) {
        this.ip = ip;
        this.name = name;
    }

    @Override
    public String toString() {
        return "my{" +
                "ip=" + ip +
                ", name='" + name + '\'' +
                '}';
    }
}
```

