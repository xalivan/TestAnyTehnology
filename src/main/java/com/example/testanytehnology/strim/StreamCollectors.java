package com.example.testanytehnology.strim;

import com.codepoetics.protonpack.Indexed;
import com.codepoetics.protonpack.StreamUtils;
import com.example.testanytehnology.strim.model.Customer;
import com.example.testanytehnology.strim.model.Item;
import com.example.testanytehnology.strim.model.User;
import com.google.common.collect.ImmutableList;
import com.example.testanytehnology.strim.sideeffect.Book;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.utility.ListIterate;


import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

@Slf4j
public class StreamCollectors {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
        List<String> result = givenList.stream().collect(toList());
        List<String> result2 = givenList.stream().collect(toUnmodifiableList());

        Set<String> result3 = givenList.stream().collect(toSet());

        List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");
        Set<String> result4 = listWithDuplicates.stream().collect(toSet());
        result4.size(); //4

        List<String> result5 = givenList.stream().collect(toCollection(LinkedList::new));

        Map<String, Integer> result6 = givenList.stream().collect(toMap(Function.identity(), String::length)); //{bb=2, dd=2, a=1, ccc=3}List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");
        Map<String, Integer> result7 = givenList.stream().collect(toMap(Function.identity(), String::length, (item, identicalItem) -> item));
        String result8 = givenList.stream().collect(joining()); //abbcccdd
        String result9 = givenList.stream().collect(joining(" ", "PRE-", "-POST")); //PRE-a bb ccc dd-POST
        Long result10 = givenList.stream().count(); //4
        DoubleSummaryStatistics result11 = givenList.stream().collect(summarizingDouble(String::length)); //DoubleSummaryStatistics{count=4, sum=8,000000, min=1,000000, average=2,000000, max=3,000000}
        Double result12 = givenList.stream().collect(averagingDouble(String::length)); //2.0
        Double result13 = givenList.stream().collect(summingDouble(String::length)); //8.0
        Optional<String> result14 = givenList.stream().collect(maxBy(Comparator.naturalOrder())); //Optional[dd]
        Map<Integer, Set<String>> result15 = givenList.stream().collect(groupingBy(String::length, toSet())); //{1=[a], 2=[bb, dd], 3=[ccc]}
        Map<Boolean, List<String>> result16 = givenList.stream().collect(partitioningBy(s -> s.length() > 2)); //{false=[a, bb, dd], true=[ccc]}

        List<Integer> numbers = Arrays.asList(42, 4, 2, 24);
        Optional<Integer> min = numbers.stream().min(Integer::compareTo); //Optional[2]
        Optional<Integer> max = numbers.stream().max(Integer::compareTo); //Optional[42]

        List<Integer> numbers2 = List.of(1, 2, 3, 5, 6, 7, 7, 8, 8);

        Map<Integer, Long> result17 = numbers2.stream()
                .filter(val -> val > 3)
                .collect(Collectors.groupingBy(i -> i, Collectors.counting())); //{5=1, 6=1, 7=2, 8=2}

        Map<Integer, Long> collect = numbers2.stream()
                .collect(groupingBy(i -> i,
                        filtering(val -> val > 3, counting()))); //{1=0, 2=0, 3=0, 5=1, 6=1, 7=2, 8=2}

        Blog blog1 = new Blog("1", Arrays.asList("Nice", "Very Nice"));
        Blog blog2 = new Blog("2", Arrays.asList("Disappointing", "Ok", "Could be better"));
        List<Blog> blogs = List.of(blog1, blog2);
        Map<String, List<List<String>>> authorComments1 = blogs.stream()
                .collect(Collectors.groupingBy(Blog::getAuthorName,
                        Collectors.mapping(Blog::getComments, Collectors.toList()))); //{1=[[Nice, Very Nice]], 2=[[Disappointing, Ok, Could be better]]}
        Map<String, List<String>> authorComments2 = blogs.stream()
                .collect(Collectors.groupingBy(Blog::getAuthorName,
                        Collectors.flatMapping(blog -> blog.getComments().stream(),
                                Collectors.toList()))); //{1=[Nice, Very Nice], 2=[Disappointing, Ok, Could be better]}

        List<Integer> list = IntStream.range(0, 9)
                .boxed() //Guava 21
                .collect(ImmutableList.toImmutableList()); //[0, 1, 2, 3, 4, 5, 6, 7, 8]

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Book("The Two Towers", 1954, "0345339711"));
        bookList.add(new Book("The Return of the King", 1955, "0618129111"));
        Map<String, String> collect1 = bookList.stream().collect(toMap(Book::getIsbn, Book::getName)); //{0395489318=The Fellowship of the Ring, 0618129111=The Return of the King, 0345339711=The Two Towers}
        Map<Integer, Book> collect2 = bookList.stream().collect(Collectors.toMap(Book::getReleaseYear, Function.identity(),
                (existing, replacement) -> existing)); //{1954=Book{name='The Fellowship of the Ring', releaseYear=1954, isbn='0395489318'}, 1955=Book{name='The Return of the King', releaseYear=1955, isbn='0618129111'}}
        ConcurrentHashMap<Integer, Book> collect3 = bookList.stream().collect(toMap(Book::getReleaseYear, Function.identity(),
                (o1, o2) -> o1, ConcurrentHashMap::new)); //{1954=Book{name='The Fellowship of the Ring', releaseYear=1954, isbn='0395489318'}, 1955=Book{name='The Return of the King', releaseYear=1955, isbn='0618129111'}}
        TreeMap<String, Book> collect4 = bookList.stream().collect(toMap(Book::getName, Function.identity(), (o1, o2) -> o1, TreeMap::new)); //{The Fellowship of the Ring=Book{name='The Fellowship of the Ring', releaseYear=1954, isbn='0395489318'}, The Return of the King=Book{name='The Return of the King', releaseYear=1955, isbn='0618129111'}, The Two Towers=Book{name='The Two Towers', releaseYear=1954, isbn='0345339711'}}

        List<String> collect5 = Stream.of("cat", "dog", "elephant", "fox", "rabbit", "duck")
                .takeWhile(n -> n.length() % 2 != 0)
                .collect(toList());//cat dog

        List<Optional<String>> listOfOptionals = Arrays.asList(
                Optional.empty(), Optional.of("foo"), Optional.empty(), Optional.of("bar"));
        List<String> filteredList = listOfOptionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList()); //foo bar

        List<String> filteredList2 = listOfOptionals.stream()
                .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
                .collect(Collectors.toList()); //foo bar

        List<String> filteredList3 = listOfOptionals.stream()
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toList()); //foo bar

        List<String> filteredList4 = listOfOptionals.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList()); //foo bar


        long startTime = System.nanoTime();
        //TODO ForkJoinPool
        long firstNum = 1;
        long lastNum = 1_000_000;
        List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
                .collect(Collectors.toList());
        ForkJoinPool customThreadPool = new ForkJoinPool(4);
//        long actualTotal = customThreadPool.submit(
//                () -> aList.parallelStream().reduce(0L, Long::sum)).get(); //500000500000
//        long test = (lastNum + firstNum) * lastNum / 2; //500000500000
//        customThreadPool.shutdown();   //348967600 -1 core //362977300 -2 core //415986000 -4 core
        //TODO ForkJoinPool
        try {
            long actualTotal2 = customThreadPool.submit(
                    () -> aList.parallelStream().reduce(0L, Long::sum)).get(); //500000500000
        } finally {
            customThreadPool.shutdown(); //379303600 -1 core //358525400 -2 core //389490200 -3 core //350897900 -4 core
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;

//        Stream<Integer> stream1 = Stream.of(1, 3, 5);
//        Stream<Integer> stream2 = Stream.of(2, 4, 6);
//        Stream<Integer> resultingStream = Stream.concat(stream1, stream2); //1 3 5 2 4 6
//        resultingStream.forEach(System.out::println);

//        Stream<Integer> stream1 = Stream.of(1, 3, 5);
//        Stream<Integer> stream2 = Stream.of(2, 4, 6);
//        Stream<Integer> stream3 = Stream.of(18, 15, 36);
//        Stream<Integer> resultingStream = Stream.concat(Stream.concat(stream1, stream2), stream3); //1 3 5 2 4 6 18 15 36
//        resultingStream.forEach(System.out::println);

//        Stream<Integer> stream1 = Stream.of(1, 3, 5);
//        Stream<Integer> stream2 = Stream.of(2, 4, 6);
//        Stream<Integer> stream3 = Stream.of(18, 15, 36);
//        Stream<Integer> stream4 = Stream.of(99);
//        Stream<Integer> resultingStream = Stream.of(stream1, stream2, stream3, stream4).flatMap(Function.identity());
//        resultingStream.forEach(System.out::println);
        //TODO Map and FlatMap
        List<String> myList = Stream.of("a", "b", "c", "d", "e", "c", "l", "n")
                .map(String::toUpperCase)
                .collect(Collectors.toList()); //[A, B, C, D, E, C, L, N]

        List<List<String>> list2 = Arrays.asList(
                Arrays.asList("a"),
                Arrays.asList("b")); //[[a], [b]]

        List<String> collect6 = list2
                .stream()
                .flatMap(Collection::stream)
                .collect(toList()); //[a, b]

        //TODO String Operations with Java Streams
        List<String> arrayOfString = Arrays.asList("a", "b", "c", "d", "e", "c", "l", "n");
        String collect7 = arrayOfString.stream().map(String::toUpperCase).collect(joining(",", "[", "]")); //[A,B,C,D,E,C,L,N]

        String str = "The Fellowship of the Ring";
        List<String> collect8 = Stream.of(str.split("\\s"))
                .map(String::new)
                .collect(Collectors.toUnmodifiableList()); //[The, Fellowship, of, the, Ring]

        String strChar = "Fellowship";
        List<Character> collect9 = strChar.chars()
                .mapToObj(item -> (char) item)
                .collect(toList()); //[F, e, l, l, o, w, s, h, i, p]

        Map<String, String> collect10 = Stream.of("a:b", "b:c")
                .map(st -> st.split(":"))
                .collect(toMap(st -> st[0], st -> st[1])); //{a=b, b=c}

        String[] programmingLanguages = {"java", "python", "nodejs", "ruby"};
        String collect11 = Arrays.asList(programmingLanguages).stream().collect(joining(" ")); //java python nodejs ruby

        String[] programming_languages = new String[]{"language:java", "os:linux", "editor:emacs"};
        Map<String, String> expectation = new HashMap<>(); //{editor=emacs, os=linux, language=java}
        expectation.put("language", "java");
        expectation.put("os", "linux");
        expectation.put("editor", "emacs");

        Map<String, String> collect12 = Arrays.asList(programming_languages).stream()
                .map(so -> so.split(":"))
                .collect(toMap(so -> so[0], so -> so[1])); //{editor=emacs, os=linux, language=java}

        String[] names = {"Afrim", "Bashkim", "Besim", "Lulzim", "Durim", "Shpetim"};
        List<String> evenIndexedNames = IntStream
                .range(0, names.length)
                .filter(i -> i % 2 == 0) //взяти парні елементи масиву -> 0, 2, 4
                .mapToObj(i -> names[i])
                .collect(Collectors.toList()); //Afrim Besim Durim
        // <groupId>com.codepoetics</groupId>
        List<Indexed<String>> list3 = StreamUtils
                .zipWithIndex(Arrays.stream(names))
                .filter(i -> i.getIndex() % 2 == 0)
                .collect(Collectors.toList());

        // <artifactId>streamex</artifactId>
//        List<String> list4 = EntryStream.of(names) //Afrim Besim Durim
//                .filterKeyValue((index, name) -> index % 2 == 0)
//                .values()
//                .toList();

        List<String> valueList = new ArrayList<>();
        valueList.add("Joe");
        valueList.add("John");
        valueList.add("Sean");
        String s = valueList.stream().reduce((first, second) -> second).get(); //Sean

        long count = valueList.stream().count();
        String s1 = valueList.stream().skip(count - 1).findFirst().get(); //Sean


        String testString = "String";
        Stream<Character> characterStream = testString.chars().mapToObj(c -> (char) c); // [S, t, r, i, n, g]
        Stream<Character> characterStream2 = testString.codePoints().mapToObj(c -> (char) c); // [S, t, r, i, n, g]
        Stream<String> stringStream = testString.codePoints().mapToObj(c -> String.valueOf((char) c)); // [S, t, r, i, n, g]


        Stream<Integer> infiniteStream = Stream.iterate(0, i -> i + 2);
        List<Integer> collect18 = infiniteStream
                .limit(10)
                .collect(Collectors.toList()); //0, 2, 4, 6, 8, 10, 12, 14, 16, 18

        Supplier<UUID> randomUUIDSupplier = UUID::randomUUID;
        Stream<UUID> infiniteStreamOfRandomUUID = Stream.generate(randomUUIDSupplier);
        List<UUID> randomInts = infiniteStreamOfRandomUUID
                .skip(10)
                .limit(10)
                .collect(Collectors.toList()); //f8ac26f1-ff0d-4611-8589-4b2fb7a6426e ....

        Stream<Integer> integers = Stream.iterate(0, i -> i + 1);
        List<Integer> collect13 = integers.limit(10).collect(toList()); //0, 1, 2, 3, 4, 5, 6, 7, 8, 9

        Stream<Integer> anStream = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> newStream = Stream.concat(Stream.of(99), anStream); //99, 1, 2, 3, 4, 5

        Stream<String> anStreamA = Stream.of("a", "b", "c", "d", "e");
        Stream<String> newStreamB = Stream.concat(anStreamA, Stream.of("A"));
        List<String> resultList = newStreamB.collect(Collectors.toList()); //a, b, c, d, e, A

        int[] integers2 = new int[]{20, 98, 12, 7, 35};
        int min2 = Arrays.stream(integers2).min().getAsInt(); // 7
        int max2 = IntStream.of(20, 98, 12, 7, 35).max().getAsInt(); // 98
        int sum = IntStream.of(20, 98, 12, 7, 35).sum(); // 172
        double avg = IntStream.of(20, 98, 12, 7, 35).average().getAsDouble(); // 34.4
        int sum3 = IntStream.range(1, 10).sum(); // 45
        int sum4 = IntStream.rangeClosed(1, 10).sum(); // 55
        List<Integer> evenInts = IntStream.rangeClosed(1, 10).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList()); //2 4 6 8 10
        int sum5 = Stream.of(33, 45).mapToInt(i -> i).sum(); // 78
        List<Integer> collect14 = IntStream.rangeClosed(1, 5).parallel().boxed().collect(toList()); //1 2 3 4 5

        User userAnn = new User(1, "Ann", 20, 20, "woman");
        User userBob = new User(2, "Bon", 15, 21, "man");
        User userSam = new User(3, "Sam", 18, 25, "man");
//        User userSam3 = new User(4, "TONY", 18, 25, "man");
        List<User> personList = new ArrayList<>();
        personList.add(userAnn);
        personList.add(userBob);
        personList.add(userSam);
//        personList.add(userSam3);
        List<User> personListFiltered = personList.stream()
                .filter(p -> p.getAge() > 15)
                .collect(toUnmodifiableList()); //User(id=1, name=Ann, age=20, weight=20, sex=woman) User(id=3, name=Sam, age=18, weight=25, sex=man)

        // <artifactId>eclipse-collections</artifactId>
        List<User> personListFiltered2 = ListIterate.distinct(personList, HashingStrategies.fromFunction(User::getName));
        List<User> personListFiltered3 = ListIterate.distinct(personList, HashingStrategies.fromIntFunction(User::getAge));
        // User(id=1, name=Ann, age=20, weight=20, sex=woman)
        // User(id=2, name=Bon, age=15, weight=21, sex=man)
        // User(id = 3, name = Sam, age = 18, weight = 25, sex = man)

        // <artifactId>streamex</artifactId>
        List<User> personListFiltered4 = StreamEx.of(personList)
                .distinct(User::getSex)
                .toList();
//        User(id=1, name=Ann, age=20, weight=20, sex=woman)
//        User(id=2, name=Bon, age=15, weight=21, sex=man)

        List<String> collect15 = Stream.iterate("", sm -> sm + "s")
                .takeWhile(sm -> sm.length() < 10).collect(toUnmodifiableList());  //s ss sss ssss sssss ssssss sssssss ssssssss sssssssss
        Stream<String> sssss = Stream.iterate("", sm -> sm + "s").dropWhile(sm -> !sm.contains("sssss")); //sssss....
        Stream.iterate(0, i -> i < 10, i -> i + 1).collect(toUnmodifiableList()); //0, 1, 2, 3, 4, 5, 6, 7, 8, 9

        List<String> collection1 = Arrays.asList("a", null, "b", "c");
        List<String> collect16 = collection1.stream()
                .filter(Objects::nonNull)
                .collect(toList());

        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> collect17 = ints.stream().filter(i -> i % 2 == 0).collect(toUnmodifiableList()); //2 4 6 8 10

        List<String> namesx = Arrays.asList("Adam", "Alexander", "John", "Tom");
        List<String> resultx = namesx.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList()); //Adam Alexander
        List<String> result1 = namesx.stream()
                .filter(name -> name.startsWith("A"))
                .filter(name -> name.length() < 5)
                .collect(Collectors.toList()); //Adam
        List<String> result18 = namesx.stream()
                .filter(name -> name.startsWith("A") && name.length() < 5)
                .collect(toList()); //Adam

        Predicate<String> predicate1 = name -> name.startsWith("A");
        Predicate<String> predicate2 = name -> name.length() < 5;
        List<String> result19 = namesx.stream()
                .filter(predicate1.and(predicate2))
                .collect(Collectors.toList()); //Adam

        Predicate<String> predicate3 = name -> name.startsWith("J");
        Predicate<String> predicate4 = name -> name.length() < 4;
        List<String> result20 = namesx.stream()
                .filter(predicate3.or(predicate4))
                .collect(Collectors.toList()); //John Tom

        Predicate<String> predicate5 = name -> name.startsWith("J");
        Predicate<String> predicate6 = name -> name.length() < 4;
        List<String> result21 = namesx.stream()
                .filter(predicate5.or(predicate6.negate()))
                .collect(Collectors.toList()); //Adam Alexander John

        List<String> result22 = namesx.stream()
                .filter(((Predicate<String>) name -> name.startsWith("A")).and(name -> name.length() < 5))
                .collect(Collectors.toList()); //Adam

        List<Predicate<String>> allPredicates = new ArrayList<Predicate<String>>();
        allPredicates.add(name -> name.startsWith("A"));
        allPredicates.add(name -> name.contains("d"));
        allPredicates.add(name -> name.length() > 4);

        List<String> result23 = namesx.stream()
                .filter(allPredicates.stream().reduce(x -> true, Predicate::and))
                .collect(Collectors.toList()); //Alexander
        List<String> result24 = namesx.stream()
                .filter(allPredicates.stream().reduce(x -> false, Predicate::or))
                .collect(Collectors.toList()); //Adam Alexander

        Customer john = new Customer("John P.", 15);
        Customer sarah = new Customer("Sarah M.", 200);
        Customer charles = new Customer("Charles B.", 150);
        Customer mary = new Customer("Mary T.", 1);
        List<Customer> customers = Arrays.asList(john, sarah, charles, mary);

        List<Customer> customersWithMoreThan100Points = customers
                .stream()
                .filter(c -> c.getPoints() > 100)
                .collect(Collectors.toList());
//        Customer(name=Sarah M., points=200)
//        Customer(name=Charles B., points=150)

        List<Customer> customersWithMoreThan100Points2 = customers
                .stream()
                .filter(Customer::hasOverHundredPoints)
                .collect(Collectors.toList());
//        Customer(name=Sarah M., points=200)
//        Customer(name=Charles B., points=150)

        List<Customer> charlesWithMoreThan100Points3 = customers
                .stream()
                .filter(c -> c.getPoints() > 100 && c.getName().startsWith("Charles"))
                .collect(Collectors.toList());
//        Customer(name=Charles B., points=150)

        List<Integer> integers3 = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum2 = integers3.stream().reduce(0, Integer::sum); //15
        Integer sum6 = integers3.stream().collect(Collectors.summingInt(Integer::intValue)); //15
        Integer sum7 = integers3.stream().mapToInt(Integer::intValue).sum(); //15

        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);

        int sum8 = map.values().stream().mapToInt(Integer::valueOf).sum(); //15

        Item item1 = new Item(1, 10);
        Item item2 = new Item(2, 15);
        Item item3 = new Item(3, 25);
        Item item4 = new Item(4, 40);
        List<Item> items = Arrays.asList(item1, item2, item3, item4);

        Integer sum9 = items.stream().map(Item::getPrice).reduce(0, Integer::sum); // 90
        Integer sum10 = items.stream().map(Item::getPrice).mapToInt(Integer::intValue).sum(); // 90
        Integer sum11 = items.stream().mapToInt(Item::getPrice).sum(); // 90

        String string = "Item1 10 Item2 25 Item3 30 Item4 45";
        Integer sum12 = Arrays.stream(string.split(" "))
                .filter((val) -> val.matches("\\d+"))
                .mapToInt(Integer::valueOf)
                .sum(); //110

        List<String> collect19 = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
//                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
//                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(toList()); //THREE FOUR

        List<User> collect20 = personList.stream()
                .peek(u -> u.setName(u.getName().toLowerCase()))
                .collect(toUnmodifiableList());
//        User(id=1, name=ann, age=20, weight=20, sex=woman)
//        User(id=2, name=bon, age=15, weight=21, sex=man)
//        User(id=3, name=sam, age=18, weight=25, sex=man)

        Map<String, Integer> someMap = new HashMap<>();
        someMap.put("10", 1);
        someMap.put("20", 2);
        someMap.put("30", 3);
        someMap.put("40", 4);
        someMap.put("50", 5);

        Set<Map.Entry<String, Integer>> entries = someMap.entrySet(); //[50=5, 40=4, 30=3, 20=2, 10=1]
        Stream<Map.Entry<String, Integer>> entriesStream = entries.stream();

        Set<String> keySet = someMap.keySet(); //[50, 40, 30, 20, 10]
        Stream<String> keysStream = keySet.stream();

        Collection<Integer> values = someMap.values(); //[5, 4, 3, 2, 1]
        Stream<Integer> valuesStream = values.stream();

        Map<String, String> books = new HashMap<>();
        books.put("978-0201633610", "Design patterns : elements of reusable object-oriented software");
        books.put("978-1617291999", "Java 8 in Action: Lambdas, Streams, and functional-style programming");
        books.put("978-0134685991", "Effective Java");

        Optional<String> optionalIsbn = books.entrySet().stream()
                .filter(e -> "Effective Java".equals(e.getValue()))
                .map(Map.Entry::getKey)
                .findFirst(); //Optional[978-0134685991]

        Optional<String> optionalIsbn2 = books.entrySet().stream()
                .filter(e -> "Non Existent Title".equals(e.getValue()))
                .map(Map.Entry::getKey).findFirst(); //Optional.empty

        Map<String, String> books2 = new HashMap<>();
        books2.put("978-0201633610", "Design patterns : elements of reusable object-oriented software");
        books2.put("978-1617291999", "Java 8 in Action: Lambdas, Streams, and functional-style programming");
        books2.put("978-0134685991", "Effective Java");
        books2.put("978-0321356680", "Effective Java: Second Edition");

        List<String> isbnCodes = books2.entrySet().stream()
                .filter(e -> e.getValue().startsWith("Effective Java"))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()); //978-0134685991 978-0321356680

        List<String> titles = books2.entrySet().stream()
                .filter(e -> e.getKey().startsWith("978-0"))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
//        Design patterns : elements of reusable object-oriented software
//        Effective Java
//        Effective Java: Second Edition

        List<Integer> numbers3 = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result25 = numbers3
                .stream()
                .reduce(0, (subtotal, element) -> subtotal + element); //21

        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String result26 = letters
                .stream()
                .reduce("", (partialString, element) -> partialString + element); //abcde

        String result27 = letters.stream().reduce("", String::concat); //abcde

        String result28 = letters
                .stream()
                .reduce("", (partialString, element) -> partialString.toUpperCase() + element.toUpperCase()); //ABCDE

        List<Integer> ages = Arrays.asList(25, 30, 45, 28, 32);
        int computedAges = ages.parallelStream().reduce(0, (a, b) -> a + b, Integer::sum); //160

        List<User> users = Arrays.asList(new User(30, "John", 10, 10, "man"),
                new User(35, "Julie", 20, 20, "woman"));
        int computedAges2 = users.stream()
                .reduce(0, (user1, user2) -> user1 + user2.getAge(), Integer::sum); //30

        List<Integer> numbers5 = Arrays.asList(1, 2, 3, 4, 5, 6);
        int divider = 2;
        int result29 = numbers5.stream().reduce(0, (a, b) -> a / divider + b / divider); //4
        int result30 = numbers5.stream()
                .reduce(0, (a, b) -> {
                    try {
                        return a / divider + b / divider;
                    } catch (ArithmeticException e) {
                        log.info("Arithmetic Exception: Division by Zero");
                    }
                    return 0;
                }); //4

        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        List<String> collect21 = listOfNumbers.stream()
                .map(number -> new String(number + " " + Thread.currentThread().getName()))
                .collect(toUnmodifiableList());
//        1 main
//        2 main
//        3 main
//        4 main
        List<String> collect22 = listOfNumbers.parallelStream()
                .map(number -> new String(number + " " + Thread.currentThread().getName()))
                .collect(toUnmodifiableList());
//        1 ForkJoinPool.commonPool-worker-3
//        2 ForkJoinPool.commonPool-worker-5
//        3 main
//        4 ForkJoinPool.commonPool-worker-3

        List<Integer> listOfNumbers3 = Arrays.asList(1, 2, 3, 4);
        int sum13 = listOfNumbers3.parallelStream().reduce(5, Integer::sum); //30

        Stream<Integer> infiniteStream4 = Stream.iterate(0, i -> i + 2);
        List<Integer> collect23 = infiniteStream4
                .limit(100)
                .collect(Collectors.toList());
        ForkJoinPool customThreadPool4 = new ForkJoinPool(2);
        ForkJoinTask<List<String>> submit = customThreadPool4
                .submit(() -> collect23.parallelStream()
                        .map(number -> new String(Thread.currentThread().getName()))
                        .collect(Collectors.toList()));
        customThreadPool4.shutdown();

        List<Integer> arrayListOfNumbers = new ArrayList<>();
        List<Integer> linkedListOfNumbers = new LinkedList<>();
        IntStream.rangeClosed(1, 1_000_000).forEach(i -> {
            arrayListOfNumbers.add(i);
            linkedListOfNumbers.add(i);
        });
        arrayListOfNumbers.stream().reduce(0, Integer::sum);           //135093312,675 ± 4195024,803
        arrayListOfNumbers.stream().parallel().reduce(0, Integer::sum);//70631711,489 ± 1517217,320
        arrayListOfNumbers.stream().collect(Collectors.toSet());               //2074483,821 ± 7520,402
        arrayListOfNumbers.stream().parallel().collect(Collectors.toSet());    //5509573,621 ± 60249,942

        int[] intArray = new int[1_000_000];
        Integer[] integerArray = new Integer[1_000_000];
        IntStream.rangeClosed(1, 1_000_000).forEach(i -> {
            intArray[i - 1] = i;
            integerArray[i - 1] = i;
        });
        Arrays.stream(intArray).reduce(0, Integer::sum);               //116247,787 ± 283,150
        Arrays.stream(intArray).parallel().reduce(0, Integer::sum);    //293142,385 ± 2526,892
        Arrays.stream(integerArray).reduce(0, Integer::sum);           //2153732,607 ± 16956,463
        Arrays.stream(integerArray).parallel().reduce(0, Integer::sum);//5134866,640 ± 148283,942

        Set<Integer> collect24 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(i -> i % 2 == 0)
                .skip(2)
                .collect(toSet()); //6 8 10

        Set<Integer> collect25 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(i -> i % 2 == 0)
                .limit(2)
                .collect(toSet());//2 4

        Set<Integer> collect26 = Stream.iterate(0, i -> i + 1)
                .filter(i -> i % 2 == 0)
                .limit(10)
                .collect(toSet()); //0 16 2 18 4 6 8 10 12 14

        List<Integer> collect27 = Stream.iterate(0, i -> i + 1)
                .filter(i -> i % 2 == 0)
                .skip(2)
                .limit(5)
                .collect(toList()); //4 6 8 10 12

        List<String> result35 = Stream.of(Locale.getISOCountries()).collect(Collectors.toList()); //AD AE AF AG AI ...
        List<String> result36 = Stream.of(Locale.getISOCountries()).collect(toList());
//        System.out.println(collect24);
        result35.stream().forEach(System.out::println);

    }
}
