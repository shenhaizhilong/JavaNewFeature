# Var 
___
## 介绍
Java 10 引入了一个新的特性：局部变量的自动推断，类似于C# 的var

```java
jshell> var age = 10;
age ==> 10

jshell> var name = "tom";
name ==> "tom"
```
## 结构
```java
Left Hand side  = right Hand side

var name        =    "tom";
```
## 目标
减小开发人员的代码写入量，消除不必要的局部变量类型声明，并保持Java对静态类型安全的承诺

## 如何工作

```java

public class VarDemo {

    public  void readFile() throws IOException {
        var fileName = "String.md";
        var line = "";
        var fileReader = new FileReader(fileName);
        var bufferedReader = new BufferedReader(fileReader);
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }

    public static void main(String[] args) throws IOException {
        VarDemo demo = new VarDemo();
        demo.readFile();
    }
}
```
Intellij Idea 反编译代码
```java
public class VarDemo {
    public VarDemo() {
    }

    public void readFile() throws IOException {
        String fileName = "String.md";
        String line = "";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        bufferedReader.close();
    }

    public static void main(String[] args) throws IOException {
        VarDemo demo = new VarDemo();
        demo.readFile();
    }
}
```
可以看到编译器根据右侧的表达式正确的推断出了左侧变量的类型 并将其添加到字节码中

## var 是保留类型名
 > var 并不是关键字，而是保留类型名，也就是说我可以用它作为变量名/类型名/方法名/包名/
 > jshell> var var = 10
 > var ==> 10

## 应用示例
- 局部变量的初始化
- 循环中的局部变量
- for each  语句中的变量

```java

var numbers = List.of(1, 2, 3, 4, 5); // inferred value ArrayList<String>
// for each 
for (var number : numbers) {
	System.out.println(number);
}
// 循环中的局部变量
for (var i = 0; i < numbers.size(); i++) {
	System.out.println(numbers.get(i));
}


jshell> var x = 1 > 0 ? 1: "Less than zero"
x ==> 1

jshell> var x = -1 > 0 ? 1: "Less than zero"
x ==> "Less than zero"


jshell> var numbers = new int[]{2, 4, 6}
numbers ==> int[3] { 2, 4, 6 }

jshell> var num = numbers[0]
num ==> 2

jshell> number += 10
$21 ==> 20


```
 
## 限制

- 右边类型必须明确不能为null
- 不能用于多变量定义  var a = 1,b = 10;
- 右侧不能为数组初始化  var b = {1,2,3}，可以var numbers = new int[]{2, 4, 6}
- 包含lambdas、方法引用和数组初始化器的Poly表达式将触发一个错误
- 一旦初始化，类型无法更改，静态类型安全
- var 字段不允许
- 不允许作为方法的返回类型  var getAge(){ return age; }
```java

jshell> var a = 1,b = 10;
|  错误:
|  'var' 不允许在复合声明中使用
|  var a = 1,b = 10;
|            ^

jshell> var b = {1,2,3}
|  错误:
|  无法推断本地变量 b 的类型
|    (数组初始化程序需要显式目标类型)
|  var b = {1,2,3};
|  ^--------------^

jshell> var numbers[] = new int[]{2, 4, 6}
|  错误:
|  'var' 不允许用作数组的元素类型
|  var numbers[] = new int[]{2, 4, 6};
|      ^

jshell> class Clazz {
   ...>   private var name;
   ...> }
|  错误:
|  此处不允许使用 'var'
|    private var name;
|            ^-^

jshell> var number = 10
number ==> 10

jshell>        number = "InfoQ"
|  错误:
|  不兼容的类型: java.lang.String无法转换为int
|         number = "InfoQ"
|                  ^-----^


Main.java:81: error: cannot infer type for local
variable x
        var x;
            ^
  (cannot use 'val' on variable without initializer)

Main.java:82: error: cannot infer type for local
variable f
        var f = () -> { };
            ^
  (lambda expression needs an explicit target-type) 

Main.java:83: error: cannot infer type for local
variable g
        var g = null;
            ^
  (variable initializer is 'null')

Main.java:84: error: cannot infer type for local
variable c
        var c = l();
            ^
  (inferred type is non denotable)

Main.java:195: error: cannot infer type for local variable m
        var m = this::l;
            ^
  (method reference needs an explicit target-type)

Main.java:199: error: cannot infer type for local variable k
        var k = { 1 , 2 };
            ^
  (array initializer needs an explicit target-type)
```
## 优点
- 提升开发体验
- 减少套路代码


## 参考文献
- [Explore the New Java 10 “var” Type](https://www.infoq.com/articles/java-10-var-type?utm_campaign=rightbar_v2&utm_source=infoq&utm_medium=articles_link&utm_content=link_text)
- [Local-Variable Type Inference](http://openjdk.java.net/jeps/286)