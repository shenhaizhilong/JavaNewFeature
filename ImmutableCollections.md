# Immutable Collections
____

## 不可变List
Java 8 如果我们想创建不可变集合我们需要用Collections.unmodifiable***

```java

 List<String> emptyList = new ArrayList<>();
 List<String> immutableList = Collections.unmodifiableList(emptyList);

```
```java
jshell> var immuableList = Collections.unmodifiable
unmodifiableCollection(     
unmodifiableList(           
unmodifiableMap(
unmodifiableNavigableMap(   
unmodifiableNavigableSet(   
unmodifiableSet(
unmodifiableSortedMap(      
unmodifiableSortedSet(
```

Java 9 引入 List.of()
```java
jshell> var list = List.of(1,2,3,4,5)
list ==> [1, 2, 3, 4, 5]

jshell> list.add(6)
|  异常错误 java.lang.UnsupportedOperationException
|at ImmutableCollections.uoe (ImmutableCollections.java:71)
|at ImmutableCollections$AbstractImmutableCollection.add (ImmutableCollections.java:75)
|at (#4:1)

```

方法签名
```
jshell> list.of(
签名:
List<E> List<E>.<E>of()
List<E> List<E>.<E>of(E e1)
List<E> List<E>.<E>of(E e1, E e2)
List<E> List<E>.<E>of(E e1, E e2, E e3)
List<E> List<E>.<E>of(E e1, E e2, E e3, E e4)
List<E> List<E>.<E>of(E e1, E e2, E e3, E e4, E e5)
List<E> List<E>.<E>of(E e1, E e2, E e3, E e4, E e5, E e6)
List<E> List<E>.<E>of(E e1, E e2, E e3, E e4, E e5, E e6, E e7)
List<E> List<E>.<E>of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8)
List<E> List<E>.<E>of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9)
List<E> List<E>.<E>of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10)
List<E> List<E>.<E>of(E... elements)

<再次按 Tab 可查看文档>

jshell> list.of(
List<E> List<E>.<E>of()
Returns an unmodifiable list containing zero elements.See Unmodifiable Lists for details.

Type Parameters:
E - the List 's element type

Returns:
an empty List
```
内部实现:内部为数组
```java

 static <E> List<E> of(E e1, E e2, E e3) {
        return new ImmutableCollections.ListN<>(e1, e2, e3);
    }

  @SafeVarargs
        ListN(E... input) {
            // copy and check manually to avoid TOCTOU
            @SuppressWarnings("unchecked")
            E[] tmp = (E[])new Object[input.length]; // implicit nullcheck of input
            for (int i = 0; i < input.length; i++) {
                tmp[i] = Objects.requireNonNull(input[i]);
            }
            elements = tmp;
        }

```

## 特征
- 1. 不可变
- 2. 无法添加/删除/更新
- 3. 尝试更改会抛出java.lang.UnsupportedOperationException

```java

jshell> list
list ==> [1, 2, 3, 4, 5]

jshell> list.set(0,10)
|  异常错误 java.lang.UnsupportedOperationException
|        at ImmutableCollections.uoe (ImmutableCollections.java:71)
|        at ImmutableCollections$AbstractImmutableList.set (ImmutableCollections.java:109)
|        at (#6:1)


jshell> list.remove(0)
|  异常错误 java.lang.UnsupportedOperationException
|        at ImmutableCollections.uoe (ImmutableCollections.java:71)
|        at ImmutableCollections$AbstractImmutableList.remove (ImmutableCollections.java:107)
|        at (#7:1)

jshell> list.add(10)
|  异常错误 java.lang.UnsupportedOperationException
|        at ImmutableCollections.uoe (ImmutableCollections.java:71)
|        at ImmutableCollections$AbstractImmutableCollection.add (ImmutableCollections.java:75)
|        at (#8:1)


```

```java   
AbstractImmutableList 

// all mutating methods throw UnsupportedOperationException
@Override public void    add(int index, E element) { throw uoe(); }
@Override public boolean addAll(int index, Collection<? extends E> c) { throw uoe(); }
@Override public E       remove(int index) { throw uoe(); }
@Override public void    replaceAll(UnaryOperator<E> operator) { throw uoe(); }
@Override public E       set(int index, E element) { throw uoe(); }
@Override public void    sort(Comparator<? super E> c) { throw uoe(); }


```