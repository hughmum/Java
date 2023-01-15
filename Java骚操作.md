### **流的一个用法**

平均高度 = 不同高度的和 / 不同高度的数量

```java
public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] s1 = s.split(" ");
        Stream<String> stream1 = Arrays.stream(s1);
        //求和
        Integer sum = stream1
                .distinct()//去重
                .map(s2 -> Integer.parseInt(s2))//将String转换成Integer类型
                .reduce(0, (result, element) -> result + element);//对去重后的数进行求和
        Stream<String> stream2 = Arrays.stream(s1);
        //求去重后的数据的个数
        long count = stream2
                .distinct()
                .count();
        System.out.format("%.3f",sum*0.1*10/count);
    }
```

### 正则表达式一个应用

去掉括号，冒号那些

```java
package mu;


import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s11 = s1.replaceAll("(?:\\{|null|\\}| +)","");
        String[] s111 = s11.split(",");
        ArrayList<Integer> list = new ArrayList<Integer>();

        for(String s : s111) {
            list.add(Integer.parseInt(s));
        }
        Collections.sort(list);
        String s2 = sc.nextLine();
        String s22 = s2.replaceAll("(?:\\[|null|\\]| +)","");
        String[] s222 = s22.split(",");
        ArrayList<String> list2 = new ArrayList<String>();
        for (String s : s222) {
            list2.add(s);
        }
        Collections.sort(list2);
        System.out.print("{");
        for (int i = 0; i < list2.size(); i++) {
            System.out.print(list.get(i) + ":" + list2.get(i));
            if (i != list.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.print("}");
        System.out.println();
    }
}

```

### 截取字符串

hello.do  截取.号前面的字符串

```java
int lastIndex = sss.lastIndexOf(".do");
String s1 = sss.substring(0,lastIndex);
```

### 根据名称进行排序

```java
slot.sort(Comparator.comparing(Brand::getName));
```

### 从一个数组里变成map

```java
//存放牌的数据
    private static List<Brand> slot = new ArrayList<>();

    public void addSlot(Brand brand) {
        slot.add(brand);

        //牌的排序
        slot.sort(Comparator.comparing(Brand::getName));
        //获取牌的名称
        Map<String,List<Brand>> map = slot.stream().collect(Collectors.groupingBy(Brand::getName));
        Set<String> key = map.keySet();
        for (String s :key) {
            List<Brand> brands = map.get(s);
            if (brands.size() == 3) {

                break;
            }
        }


        paint();
    }
```

