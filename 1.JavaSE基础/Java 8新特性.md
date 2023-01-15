# Java 8新特性

速度更快，代码更少，强大的Stream APi ，便于并行， 最大化减少空指针异常：Optional Nashhorm引擎 允许在JVM上运行JS应用

## Lambda表达式

**为什么使用Lambda表达式？**

Lambda是一个匿名函数，我们可以把Lambda表达式理解是一段可以传递的代码。使用它可以写出更简洁，更灵活的代码。作为一种更紧凑的代码风格，使Java语言的表达能力得到了提升。

```java
public class LambdaTest {
    @Test
    public void test1() {
        Runnable r1= new Runnable() {
            @Override
            public void run() {
                System.out.println("dsfdsfs");
            }
        };
        r1.run();

        System.out.println("***********");

        Runnable r2= () -> System.out.println("dsfdsfs");
        r2.run();
    }

    @Test
    public void test2() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare = com1.compare(1, 2);
        System.out.println(compare);
        System.out.println("*************");
        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1,o2);
        int compare2 = com2.compare(3, 2);
        System.out.println(compare2);
        System.out.println("*************");
        //方法引用
        Comparator<Integer> com3 = Integer :: compare;
        int compare3 = com3.compare(3, 2);
        System.out.println(compare3);
    }
}
```

六种lambda表达式

```java
public class LambdaTest {
    /**
     * 1 举例 (o1, o2) -> Integer.compare(o1,o2)
     * 2 格式：
     *      -> : lambda操作符 或 箭头操作符
     *      ->左边: lambda形参列表（其实就是接口中的抽象方法的形参列表）
     *      ->右边：lambda体（其实就是重写的抽象方法的方法体）
     * 3 lambda表达式的使用
     *      总结：
     *      ->左边 ： lambda形参列表的参数类型可以省略（类型推断）：如果lambda形参列表只有一个参数，则()也可以省略
     *      ->右边：  lambda体应该使用一对{}包裹, 而如果lambda体只有一条执行语句，（也可能时return语句）这样可以省略大括号以及return
     * 4 lambda表达式的本质 ： 作为接口的实例
     *                      这个接口也有要求，就是只能由一个抽象方法，函数式接口
     */

    /**
     * 语法格式一：无参，无返回值
     */
    @Test
    public void test1() {

        Runnable r1= new Runnable() {
            @Override
            public void run() {
                System.out.println("dsfdsfs");
            }
        };
        r1.run();

        System.out.println("***********");

        Runnable r2= () -> {
            System.out.println("dsfdsfs");
        };
        r2.run();
    }

    /**
     * 语法格式二： Lambda需要一个参数，但是没有返回值
     */
    @Test
    public void test2() {
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("sdfdsfdsfds");
        System.out.println("******");
        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("dangzhenle1");
    }

    /**
     * 语法格式三 ： 数据类型可以省略，因为可由编译器推断得出 称为“类型推断”
     */
    @Test
    public void test3() {
        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("dangzhenle1");
        System.out.println("****");
        Consumer<String> con2 = (s) -> {
            System.out.println(s);
        };
        con2.accept("33333");

        //平常也会用到类型推断
        ArrayList<String> list = new ArrayList<>();
        int[] arr = {1,2,3};
    }

    /**
     * 语法格式四： lambda只需要一个参数时，参数的小括号可以省略
     */
    @Test
    public void test4() {
        Consumer<String> con1 = (s) -> {
            System.out.println(s);
        };
        con1.accept("44444");
        System.out.println("***");
        Consumer<String> con2 = s -> {
            System.out.println(s);
        };
        con2.accept("4444");
    }

    /**
     * 语法格式五： lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
     */
    @Test
    public void test5() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(1, 2));
        System.out.println("****");
        Comparator<Integer> com2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(com2.compare(2, 1));
    }

    /**
     * 语法格式六： 当lambda体只有一条语句时，return 与大括号若有，都可以省略
     */
    @Test
    public void test6() {
        Comparator<Integer> com1 = (o1, o2) -> {
            return o1.compareTo(o2);
        };
        System.out.println(com1.compare(2, 1));
        System.out.println("****");
        Comparator<Integer> com2 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(com2.compare(2, 1));
    }
}
```



## 函数式（Functional）接口

```java
@FunctionalInterface
public interface Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see     java.lang.Thread#run()
     */
    public abstract void run();
}
```

**自定义函数式接口**

```java
@FunctionalInterface
public interface MyInterface {
    void method1();
}
```

当需要用函数式接口来写匿名实现类的时候，都可以用lambda表达式来写

在java.util.function包下定义了java8丰富的函数式接口

##### Java内置四大核心函数式接口

<img src="https://myimage-1314195759.cos.ap-chengdu.myqcloud.com/img/202210131155606.png" alt="image-20221013115506457" style="zoom: 50%;" />

```java
/**
 * Java 内置四大核心函数式接口
 * 消费型接口 Consumer<T> void accept(T t)
 * 供给型接口 Supplier<T> T get()
 * 函数型接口 Function<T, R> R apply(T t)
 * 断定性接口 Predicate<T> boolean test(T t)
 */
public class LambdaTest2 {
    @Test
    public void test1() {
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("lei" + aDouble);
            }
        });
        System.out.println("lambda化简后***");
        happyTime(300, money -> System.out.println("dsfdsf" + money));
    }

    public void happyTime(double money, Consumer<Double> con) {
        con.accept(money);
    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("北京","天津");
        List<String> filterStrs = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(filterStrs);
        System.out.println("lambda后****");
        List<String> filterStrs1 = filterString(list, s -> s.contains("京"));
        System.out.println(filterStrs1);
    }
    //根据给定的规则，过滤集合中的字符串， 此规则由predicate的方法决定
    public List<String> filterString(List<String> list, Predicate<String> pre) {
        ArrayList<String> filterList = new ArrayList<>();
        for (String s : list) {
            if (pre.test(s)) {
                filterList.add(s);
            }
        }
        return filterList;
    }
}
```



## 方法引用与构造器引用

##### 方法引用

**当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用**

方法引用，本质上就是Lambda表达式，而lambda表达式，是作为函数式接口的实例，所以方法引用，也是函数式接口的实例

使用格式： 类或对象  ::  方法名

具体分为三种情况：

1. 对象 :: 非静态方法（实例方法）
2. 类:: 静态方法
3. 类::非静态方法

方法引用使用要求： 要求接口中的抽象方法的形参列表和返回值类型与方法引用的形参列表和返回值类型相同。

```java
@Test
public void test1() {
    
}
```

情况一    对象：：实例方法

```java
// 情况一： 对象：：实例方法
    //Consumer中的void accept（T t）
    //PrintStream 中的void println(T t)
    @Test
    public void test6() {
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("北京");
        System.out.println("*****");
        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
        con2.accept("beijing");
    }
    //Supplier中的T get()
    //Employee中的String getName()
    @Test
    public void test7() {
        Employee emp = new Employee("Dd",  "Dd", 11, d);
        Supplier<String> sup1  = () -> emp.getName();
        System.out.println(sup1.get());
        System.out.println("****");
        Supplier<String> sup2 = emp::getName;
        System.out.println(sup2.get());
    }
```

情况二   类：：静态方法

```java
//情况二 类：：静态方法
    //Comparator中的int compare(T t1, T t2)
    //Integer 中的int compare(T t1, T t2);
    @Test
    public void test9() {
        Comparator<Integer> com1 = (t1, t2) -> Integer.compare(t1, t2);
        System.out.println(com1.compare(1, 2));
        System.out.println("*****");
        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(2, 1));
    }
    //Function中的R apply(T t) 两个类型
    //Math 中的Long round(Double d)
    @Test
    public void test99() {
        Function<Double,Long> fun = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };
        System.out.println("***");
        Function<Double, Long> func1 = d -> Math.round(d);
        System.out.println(func1.apply(9.9));
        System.out.println("*****");
        Function<Double, Long> func2 = Math::round;
        System.out.println(func2.apply(3.3));
    }
```

情况三： 类 ：： 实例方法

```java
//情况三： 类 ：： 实例方法
    //Comparator中的int compare(T t1, T t2)
    //String中的int t1.compareTo(t2) 此时第一个参数是作为方法的调用者出现的
    @Test
    public void test88() {
        Comparator<String> com1 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(com1.compare("ddd", "dd"));
        System.out.println("****");
        Comparator<String> com2 = String::compareTo;
        System.out.println(com2.compare("2", "3"));
    }
    //BiPredicate中的boolean test(T t1, T t2)
    //String中的boolean t1.equals(t2)
    @Test
    public void test68() {
        BiPredicate<String, String> pre1 = (s1, s2) -> s1.equals(s2);
        System.out.println(pre1.test("1", "2"));
        System.out.println("****");
        BiPredicate<String, String> pre2 = String::equals;
        System.out.println(pre2.test("2", "2"));
    }
    //Function 中的 R apply (T t)
    //Employee中的String getName();
    @Test
    public void test77() {
        Employee employee = new Employee("dd","d",343,1);
        Function<Employee, String> fun1  = e -> e.getName();
        System.out.println(fun1.apply(employee));
        System.out.println("****");
        Function<Employee, String> fun2 = Employee::getName;
        System.out.println(fun2.apply(employee));
    }
```

##### 构造器引用

和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致

抽象方法的返回值类型即为构造器所属的类的类型

```java
	//构造器引用
    //Supplier中的T get()
    //Employee的空参构造器：Employee()
    @Test
    public void test9() {
        Supplier<Employee> sup = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        System.out.println("****");
        Supplier<Employee> sup1 = () -> new Employee();
        System.out.println("****");
        Supplier<Employee> sup2 = Employee :: new;
        System.out.println(sup2.get());
    }

    //Function中的R apply(T t)
    @Test
    public void test99() {
        Function<Integer, Employee> func1 = id -> new Employee(id);
        Employee emp = func1.apply(1);
        System.out.println(emp);
        System.out.println("*****");
        Function<Integer, Employee> func2 = Employee::new;
        System.out.println(func2.apply(2));
    }

    //BiFuncion中的R apply(T t, U u)
    @Test
    public void test999() {
        BiFunction<Integer, String, Employee> func1 = (id, name) -> new Employee(1,"2");
        System.out.println(func1.apply(1, "d"));
        System.out.println("****");
        BiFunction<Integer, String, Employee> func2 = Employee::new;
        System.out.println(func2.apply(1, "2"));
    }
```

##### 数组引用

可以把数组看做是一个特殊的类，则写法与构造器引用一致

```java
//数组引用
    //Function中的R apply(T t)
    @Test
    public void test44() {
        Function<Integer,String[]> func1 = length -> new String[length];
        String[] arr1 = func1.apply(4);
        System.out.println(Arrays.toString(arr1));
        System.out.println("***");
        Function<Integer,String[]> func2 = String[] :: new;
        String[] arr2 = func1.apply(2);
        System.out.println(Arrays.toString(arr2));
    }
```



## 强大的Stream API

这是第二个重要的改变

使用Stream Api对集合数据进行操作，类似于SQL执行的数据库查询

为什么要用Stream API 项目中许多数据都来源于MYSQL Oracle 等，但现在数据源可以更多了，有MongDB， Redis等， 而这些NoSQL的数据就需要Java层面去处理.

**Stream和Collection集合的区别**：Collection是一种静态的内存数据结构，而Steam是有关计算的。前者是主要面向内存，储存在内存中，后者主要是面向CPU 通过CPU计算的

**什么是Stream**

是数据渠道，用于操作数据源 集合 数组等，所生成的元素序列

1. stream自己不存储数据，
2. stream不会改变源对象 相反，会返回一个持有结果的新stream
3. stream 操作是延迟进行的，这意味着他们会等到需要结果的时候才执行

**Stream的操作三个步骤**

创建stream ：一个数据源

中间操作 ： 一个中间操作链，对数据操作

终止操作 ： 一旦执行终止操作，就执行中间操作链，并产生结果，之后，不会再被使用

##### 创建Stream

**创建Stream方式1 ： 通过集合**

 并行和顺序流区别，在取数据时并行是随机取，而顺序按顺序

```java
//通过集合chuangjian
    @Test
    public void test() {
        List<Employee> employees = EmployeeData.getEmployees;
        //返回一个顺序流 default Stream<E> stream()
        Stream<Employee> stream = employees.stream();
        //返回一个并行流 default Stream<E> parallelStream()
        Stream<Employee> employeeStream = employees.parallelStream();
    }
```



**创建Stream方式2： 通过集合**

```java
//通过数组
    @Test
    public void test11() {
        //调用Arrays类的public static <T> Stream<T> stream(T[] array,
        int[] arr = new int[]{1,2,3,4};
        IntStream stream = Arrays.stream(arr);
        Employee e1 = new Employee(1,"@");
        Employee e2 = new Employee(2,"@");
        Employee[] employees = new Employee[]{e1, e2};
        Stream<Employee> stream1 = Arrays.stream(employees);
    }
```



**创建Stream方式3 ： 通过Stream的of()**

```java
    //通过of
    @Test
    public void test111() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
    }
```



**创建Stream方式4 ： 创建无限流**

```java
//通过无限流
    @Test
    public void test1111() {
        //迭代 public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        //遍历前十个偶数
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        //生成
        //    public static<T> Stream<T> generate(Supplier<T> s) {
        //        Objects.requireNonNull(s);
        //        return StreamSupport.stream(
        //                new StreamSpliterators.InfiniteSupplyingSpliterator.OfRef<>(Long.MAX_VALUE, s), false);
        //    }
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
```

##### 中间操作

**筛选与切片**

```java
//1 筛选与切片
    @Test
    public void test111() {
        //Stream<T> filter(Predicate<? super T> predicate);-- 接受lambda从流中排除某些元素
        List<Employee> list = EmployeeData.getEmployees();
        Stream<Employee> stream = list.stream();
        stream.filter(e -> e.getId() > 1).forEach(System.out::println);

        //limit（n） 截断流，不超过给定数量
        list.stream().limit(3).forEach(System.out::println);

        //skip跳过元素 返回一个扔掉了前n个元素的流，如果不足n个，与limit互补
        list.stream().skip(1).forEach(System.out::println);

        //distinct 去重
        list.stream().distinct().forEach(System.out::println);
    }
```

**映射**

```java
//2 映射
    @Test
    public void test111() {
        //map(Function f)接受一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射出一个新的元素
        List<String> list = Arrays.asList("aa","bb","cc","dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

        //练习：获取员工姓名长度>3的员工的姓名
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> stringStream = employees.stream().map(Employee::getName);
        stringStream.filter(name -> name.length() > 3).forEach(System.out::println);

        //练习2：
        Stream<Stream<Character>> streamStream = list.stream().map(StreamTest::fromStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });

        //flatMap(Function f) 接受一个函数作为参数，将流中的每个值都换成另一个流，然后把所有的流连接成一个流
        //map类似于list.add flatmap 类似于list.addAll
        Stream<Character> characterStream = list.stream().flatMap(StreamTest::fromStringToStream);
        characterStream.forEach(System.out::println);


    }
    //举个例子 将字符串中的多个字符构成的集合转换为对映的Stream实例
    public static Stream<Character> fromStringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for(Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }
```

排序

```java
    @Test
    public void test33() {
        //sorted自然排序
        List<Integer> list =Arrays.asList(1,5,7,23,4);
        list.stream().sorted().forEach(System.out::println);
        //sorted(Comparator com)定制排序
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((e1, e2) -> Integer.compare(e1.getId(),e2.getId()))
                .forEach(System.out::println);
    }
```

##### 终止操作

1 匹配与查找

```java
   @Test
    public void test33() {
        List<Employee> employees = EmployeeData.getEmployees();
        //allMatch(Predicate p) - 检查是否匹配所有元素
        boolean allMatch = employees.stream().allMatch(e -> e.getId() > 1);
        System.out.println(allMatch);
        //anyMatch 存在一个匹配元素
        boolean anyMatch = employees.stream().anyMatch(e -> e.getId() > 1);
        System.out.println(anyMatch);
        //noMatch 检查是否没有匹配的元素
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(noneMatch);
        //findFirst 返回第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);
        //findAny 返回当前流的任意一个元素
        Optional<Employee> any = employees.parallelStream().findAny();
        System.out.println(any);
        //count 返回流中总个数
        long count = employees.stream().filter(e -> e.getId() > 1).count();
        System.out.println(count);
        //max（Comparator c） 返回最大值
        Optional<Integer> max = employees.stream().map(e -> e.getId()).max(Integer::compare);
        System.out.println(max);
        //min
        Optional<Employee> min = employees.stream().min((e1, e2) -> Integer.compare(e1.getId(), e2.getId()));
        System.out.println(min);
        //forEach 内部迭代
        employees.stream().forEach(System.out::println);
        //使用集合的遍历操作
        employees.forEach(System.out::println);//这里一定是一个默认方法
    }
```

2 归约

reduce

```java
T reduce(T identity, BinaryOperator<T> accumulator);


public interface BinaryOperator<T> extends BiFunction<T,T,T> {
    /**
     * Returns a {@link BinaryOperator} which returns the lesser of two elements
     * according to the specified {@code Comparator}.
     *
     * @param <T> the type of the input arguments of the comparator
     * @param comparator a {@code Comparator} for comparing the two values
     * @return a {@code BinaryOperator} which returns the lesser of its operands,
     *         according to the supplied {@code Comparator}
     * @throws NullPointerException if the argument is null
     */
    public static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link BinaryOperator} which returns the greater of two elements
     * according to the specified {@code Comparator}.
     *
     * @param <T> the type of the input arguments of the comparator
     * @param comparator a {@code Comparator} for comparing the two values
     * @return a {@code BinaryOperator} which returns the greater of its operands,
     *         according to the supplied {@code Comparator}
     * @throws NullPointerException if the argument is null
     */
    public static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }
}

```

```java
    @Test
    public void test33() {
        //reduce(T identity, BinaryOperator)--可以将流中元素反复结合起来，得到一个值，返回
        //练习，计算1=10自然数的集合
        List<Integer> list = Arrays.asList(1,2,3,4);
        Integer reduce = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
        //reduce(BinaryOperator) 可以将流中元素反复结合起来，得到一个值，返回Optional<T>
        List<Employee> employees = EmployeeData.getEmployees();
//        Optional<Integer> reduce1 = employees.stream().map(Employee::getId).reduce(Integer::sum);
        Optional<Integer> reduce1 = employees.stream().map(Employee::getId).reduce((d1,d2) -> d1 + d2);
        System.out.println(reduce1);
    }
```

收集

Collector接口中方法的实现决定了如何对流执行收集的操作，

**collect(Collector c)将流转换为其他形式，接受一个collector接口的实现，用于给stream中元素做汇总的方法**

```java
    @Test
    public void test33() {
        //查找>？的员工，并返回成一个list
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> collect = employees.stream().filter(e -> e.getId() > 1).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
```



## Optional类

Optional<T>类 是一个容器类，他可以保存类型T的值，代表这个值存在，或者仅仅保存null表示这个值不存在，原来用null表示一个值不存在，现在optinal可以更好的表达这个概念，并且避免空指针异常。

```java
    @Test
    public void test33() {
        Girl girl = new Girl();
        girl = null;
        Optional<Girl> girl1 = Optional.ofNullable(girl);
    }
```

```java
    @Test
    public void test33() {
        Girl girl = new Girl();
        girl = null;
        Optional<Girl> girl1 = Optional.ofNullable(girl);
        System.out.println(girl1);
        Girl dd = girl1.orElse(new Girl());
        System.out.println(dd);
    }
```

