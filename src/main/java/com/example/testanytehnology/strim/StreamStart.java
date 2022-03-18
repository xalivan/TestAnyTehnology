package com.example.testanytehnology.strim;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

@Slf4j
public class StreamStart {
    Stream<String> streamEmpty = Stream.empty();

    public Stream<String> streamOf(List<String> list) {
        return list == null || list.isEmpty() ? Stream.empty() : list.stream();
    }

    private static long counterFilter;
    private static long counterMap;

    static Optional<String> getElement() {
        List<String> list = Arrays.asList("abc1", "abc1", "abc1", "abc2");
        counterFilter = 0;
        counterMap = 0;
        return list.stream().filter(element -> {
            log.info("filter() was called");
            log.info(String.valueOf(++counterFilter));
            return element.contains("2");
        }).map(element -> {
            log.info("map() was called");
            log.info(String.valueOf(++counterMap));
            return element.toUpperCase();
        }).findFirst();
    }

    public static void main(String[] args) throws IOException {
//        getElement();

        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();

        String[] arr = new String[]{"1", "2", "3"};
        Stream<String> streamOfArrayFull = Arrays.stream(arr); // 1 2 3
//        streamOfArrayFull.forEach(System.out::print);   System.out.println();
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); // 2 3
//        streamOfArrayPart.forEach(System.out::print);   System.out.println();
        Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build(); // a b c
//        streamBuilder.forEach(System.out::print);   System.out.println();
        Stream<String> streamGenerated = Stream.generate(() -> "element ").limit(3); //element element element
//        streamGenerated.forEach(System.out::print);
        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);
//        streamIterated.forEach(System.out::println);
        IntStream intStream = IntStream.range(1, 4); // 1 2 3
//        intStream.forEach(System.out::println);
        LongStream longStream = LongStream.rangeClosed(1, 4); // 1 2 3 4
//        longStream.forEach(System.out::println);
        Random random = new Random();
        DoubleStream doubleStream = random.doubles(3); //0.6794580628723819 0.869856682660486 0.1674158281728616
//        doubleStream.forEach(System.out::println);
        IntStream streamOfChars = "abc".chars(); //97 98 99
//        streamOfChars.forEach(System.out::println);
        Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c"); //abc
//        streamOfString.forEach(System.out::print);
        Path path = Paths.get("C:\\FATHER\\TestAnyTehnology\\src\\main\\java\\com\\example\\testanytehnology\\strim\\file.txt");
        Stream<String> streamOfStrings = Files.lines(path);
//        streamOfStrings.forEach(System.out::println);
        Stream<String> streamWithCharset = Files.lines(path, StandardCharsets.UTF_8);
//        streamWithCharset.forEach(System.out::println);
        Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b")); //b
//        stream.forEach(System.out::println);
        Optional<String> anyElement = stream.findAny(); //b
//        System.out.println(anyElement.get());

        List<String> elements = Stream.of("a", "b", "c")
                .filter(element -> element.contains("b"))
                .collect(toList());
        Optional<String> anyElement2 = elements.stream().findAny();
        Optional<String> firstElement = elements.stream().findFirst();

        Stream<String> onceModifiedStream = Stream.of("1111", "2222", "3333").skip(1); // 2222 3333
//        onceModifiedStream.forEach(System.out::println);
        Stream<String> twiceModifiedStream = onceModifiedStream.skip(1)
                .map(element -> element.substring(0, 2)); // 33
//        twiceModifiedStream.forEach(System.out::println);

        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        long size = list.stream().skip(1)
                .map(element -> element.substring(0, 3)).sorted().count(); //2
//        System.out.println(size);

        OptionalInt reduced = IntStream.range(1, 4).reduce(Integer::sum); //6
//        if (reduced.isPresent()) {
//            System.out.println(reduced.getAsInt());
//        }

        int reducedTwoParams = IntStream.range(1, 4).reduce(20, Integer::sum); //26
//        System.out.println(reducedTwoParams);

        int reducedParams = Stream.of(1, 2, 3)        //26
                .reduce(20, Integer::sum, (a, b) -> {
                    log.info("combiner was called");
                    return a + b;
                });
//        System.out.println(reducedParams);

        int reducedParallel = Arrays.asList(1, 2, 3).parallelStream() //log, log //36
                .reduce(10, Integer::sum, (a, b) -> {
//                    log.info("combiner was called");
                    return a + b;
                });
//        System.out.println(reducedParallel);

        List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
                new Product(14, "orange"), new Product(13, "lemon"),
                new Product(23, "bread"), new Product(13, "sugar"));
        List<String> collectorCollection = productList.stream().map(Product::getProduct).collect(toList()); //[potatoes, orange, lemon, bread, sugar]
//        System.out.println(collectorCollection.toString());
        String listToString = productList.stream().map(Product::getProduct)
                .collect(Collectors.joining(", ", "(", ")")); //(potatoes, orange, lemon, bread, sugar)
//        System.out.println(listToString);
        double averagePrice = productList.stream().collect(Collectors.averagingInt(Product::getCost)); //17.2
//        System.out.println(averagePrice);
        int summingPrice = productList.stream().collect(Collectors.summingInt(Product::getCost)); //86
        int summingPrice2 = productList.stream().mapToInt(Product::getCost).sum(); //86
//        System.out.println(summingPrice);
//        System.out.println(summingPrice2);
        IntSummaryStatistics statistics = productList.stream().collect(Collectors.summarizingInt(Product::getCost));
        //IntSummaryStatistics{count=5, sum=86, min=13, average=17,200000, max=23}
//        System.out.println(statistics);

        Map<Integer, List<Product>> collectorMapOfLists = productList.stream().collect(Collectors.groupingBy(Product::getCost));
//        {23=[Product(cost=23, product=potatoes), Product(cost=23, product=bread)],
//        13=[Product(cost=13, product=lemon), Product(cost=13, product=sugar)],
//        14=[Product(cost=14, product=orange)]}
//        System.out.println(collectorMapOfLists);
        Map<Boolean, List<Product>> mapPartioned = productList.stream()
                .collect(Collectors.partitioningBy(element -> element.getCost() > 15));
//        {false=[Product(cost=14, product=orange), Product(cost=13, product=lemon), Product(cost=13, product=sugar)],
//        true=[Product(cost=23, product=potatoes), Product(cost=23, product=bread)]}
//        System.out.println(mapPartioned);
        Set<Product> unmodifiableSet = productList.stream()
                .collect(Collectors.collectingAndThen(toSet(),
                        Collections::unmodifiableSet));
//        У цьому конкретному випадку колектор перетворив потік у Set , а потім створив із нього незмінний набір .
//        [Product(cost=23, product=bread),
//        Product(cost=13, product=sugar),
//        Product(cost=13, product=lemon),
//        Product(cost=23, product=potatoes),
//        Product(cost=14, product=orange)]
//        System.out.println(unmodifiableSet);

        Collector<Product, ?, LinkedList<Product>> toLinkedList =
                Collector.of(LinkedList::new, LinkedList::add,
                        (first, second) -> {
                            first.addAll(second);
                            return first;
                        });
        LinkedList<Product> linkedListOfPersons = productList.stream().collect(toLinkedList);
        LinkedList<Product> linkedListOfPersons2 = productList.stream()
                .collect(Collector.of(LinkedList::new, LinkedList::add,
                        (first, second) -> {
                            first.addAll(second);
                            return first;
                        }));
//        [Product(cost=23, product=potatoes),
//        Product(cost=14, product=orange),
//        Product(cost=13, product=lemon),
//        Product(cost=23, product=bread),
//        Product(cost=13, product=sugar)]
//        System.out.println(linkedListOfPersons);
//        System.out.println(linkedListOfPersons2);

        Stream<Product> streamOfCollection2 = productList.parallelStream();
        boolean isParallel = streamOfCollection2.isParallel(); //true
        boolean bigPrice = streamOfCollection2.map(product -> product.getCost() * 12).anyMatch(price -> price > 200); //true
//        System.out.println(isParallel);
//        System.out.println(bigPrice);

        IntStream intStreamParallel = IntStream.range(1, 150).parallel();
//        System.out.println(intStreamParallel.summaryStatistics());
        //IntSummaryStatistics{count=149, sum=11175, min=1, average=75,000000, max=149}
        boolean isParallel2 = intStreamParallel.isParallel(); //true
//        System.out.println(isParallel2);
//        Потік у паралельному режимі можна перетворити назад у послідовний режим за допомогою методу sequential() :
        IntStream intStreamSequential = intStreamParallel.sequential();
        boolean isParallel3 = intStreamSequential.isParallel(); //false
//        System.out.println(isParallel3);

        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
        List<String> result = givenList.stream().collect(toList()); //[a, bb, ccc, dd]
//        System.out.println(result);
        List<String> result2 = givenList.stream().collect(toUnmodifiableList()); //[a, bb, ccc, dd]
//        System.out.println(result2);

        List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");
        Set<String> result3 = listWithDuplicates.stream().collect(toSet()); //[bb, a, c, d]
        Set<String> result4 = givenList.stream().collect(toUnmodifiableSet());
//        System.out.println(result3);

        List<String> result5 = givenList.stream().collect(toCollection(LinkedList::new));
        List<String> result6 = new LinkedList<>(givenList);

        Map<String, Integer> result7 = givenList.stream().collect(toMap(Function.identity(), String::length));
        //{bb=2, dd=2, a=1, ccc=3}
//        System.out.println(result7);

        List<String> listWithDuplicates2 = Arrays.asList("a", "bb", "c", "d", "bb");
        Map<String, Integer> result8 = listWithDuplicates2.stream()
                .collect(toMap(Function.identity(), String::length, (item, identicalItem) -> item));
//        {bb=2, a=1, c=1, d=1}
//        System.out.println(result8);

//        Давайте зберемо елементи Stream до екземпляра List , а потім перетворимо результат у екземпляр Set
        Set<String> result9 = listWithDuplicates2.stream().collect(collectingAndThen(toList(), Set::copyOf));
//        System.out.println(result9);

        String result10 = givenList.stream().collect(joining()); //abbcccdd
        String result11 = givenList.stream().collect(joining(" ")); //a bb ccc dd
        String result12 = givenList.stream().collect(joining(" ", "PRE-", "-POST")); //PRE-a bb ccc dd-POST
        Long result13 = givenList.stream().collect(counting()); //4
//        System.out.println(result13);


        List<String> listWithDuplicates3 = Arrays.asList("a", "bb", "c", "d", "bb");
        DoubleSummaryStatistics result14 = listWithDuplicates3.stream().collect(summarizingDouble(String::length));
        //DoubleSummaryStatistics{count=5, sum=7,000000, min=1,000000, average=1,400000, max=2,000000}
        Double result15 = givenList.stream().collect(averagingDouble(String::length)); //2.0
        Double result16 = givenList.stream().collect(summingDouble(String::length)); //8.0
        Optional<String> result17 = givenList.stream().collect(maxBy(Comparator.naturalOrder())); //Optional[dd]

        Map<Integer, Set<String>> result18 = givenList.stream().collect(groupingBy(String::length, toSet()));
        //{1=[a], 2=[bb, dd], 3=[ccc]}
        Map<Boolean, List<String>> result19 = givenList.stream().collect(partitioningBy(s -> s.length() > 2));
        //{false=[a, bb, dd], true=[ccc]}
        System.out.println(result19.get(false)); //[a, bb, dd]

        List<Integer> numbers = Arrays.asList(42, 4, 2, 24);
        Optional<Integer> min = numbers.stream().collect(minBy(Integer::compareTo)); //Optional[2]
        Optional<Integer> max = numbers.stream().collect(maxBy(Integer::compareTo)); //Optional[42]



    }



}
