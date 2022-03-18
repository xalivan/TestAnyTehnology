package com.example.testanytehnology.strim;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

@Slf4j
public class StreamBasics {
    public static void main(String[] args) throws IOException {
        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream(); //a b c

        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> streamOfArrayFull = Arrays.stream(arr);
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); //b c

        Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();

        Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);

        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);

        IntStream intStream = IntStream.range(1, 3); //1 2
        LongStream longStream = LongStream.rangeClosed(1, 3); //1 2 3

        Random random = new Random();
        DoubleStream doubleStream = random.doubles(3);

        IntStream streamOfChars = "abc".chars(); //97 98 99

        Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c"); // a b c

        String PATH = "C:\\FATHER\\TestAnyTehnology\\src\\main\\java\\com\\example\\testanytehnology\\strim\\file.txt";
        Path path = Paths.get(PATH);
        Stream<String> streamOfStrings = Files.lines(path);
        Stream<String> streamWithCharset = Files.lines(path, StandardCharsets.UTF_8);

        Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b")); //b
        Optional<String> anyElement = stream.findAny();

        List<String> elements = Stream.of("a", "b", "c")
                .filter(element -> element.contains("b"))
                .collect(Collectors.toList());
        Optional<String> anyElement2 = elements.stream().findAny();
        Optional<String> firstElement = elements.stream().findFirst();

        Stream<String> onceModifiedStream = Stream.of("abcd", "bbcd", "cbcd", "cbcd", "cbcd", "cbcd").skip(1);
        Stream<String> twiceModifiedStream = onceModifiedStream.skip(1).map(element -> element.substring(0, 3));

        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        long size = list.stream().skip(1).map(element -> element.substring(0, 3)).count();

        List<String> list2 = Arrays.asList("abc1", "abc2", "abc3");
        Optional<String> stream2 = list2.stream() //Optional[ABC2]
                .filter(element -> {
                    return element.contains("2");
                }).map(String::toUpperCase).findFirst();

        OptionalInt reduced = IntStream.range(1, 4).reduce(Integer::sum); //OptionalInt[6] // (1 + 2 + 3)

        int reducedTwoParams = IntStream.range(1, 4).reduce(10, Integer::sum); //16 //(10 + 1 + 2 + 3)

        int reducedParallel = Arrays.asList(1, 2, 3).parallelStream() //36 // (10 + 1 = 11; 10 + 2 = 12; 10 + 3 = 13;) (12 + 13 = 25; 25 + 11 = 36)
                .reduce(10, Integer::sum, (a, b) -> {
//                    log.info("combiner was called");
                    return a + b;
                });

        List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
                new Product(14, "orange"), new Product(13, "lemon"),
                new Product(23, "bread"), new Product(13, "sugar"));
        List<String> collectorCollection = productList.stream().map(Product::getProduct).collect(Collectors.toList()); //[potatoes, orange, lemon, bread, sugar]
        String listToString = productList.stream().map(Product::getProduct).collect(Collectors.joining(", ", "[", "]")); //[potatoes, orange, lemon, bread, sugar]
        double averagePrice = productList.stream().collect(Collectors.averagingInt(Product::getCost)); //17.2
        int summingPrice = productList.stream().mapToInt(Product::getCost).sum(); //86
        IntSummaryStatistics statistics = productList.stream().collect(Collectors.summarizingInt(Product::getCost)); //IntSummaryStatistics{count=5, sum=86, min=13, average=17,200000, max=23}
        Map<Integer, List<Product>> collectorMapOfLists = productList.stream().collect(Collectors.groupingBy(Product::getCost)); //{23=[Product(cost=23, product=potatoes), Product(cost=23, product=bread)], 13=[Product(cost=13, product=lemon), Product(cost=13, product=sugar)], 14=[Product(cost=14, product=orange)]}
        Map<Boolean, List<Product>> mapPartioned = productList.stream().collect(Collectors.partitioningBy(element -> element.getCost() > 15)); //{false=[Product(cost=14, product=orange), Product(cost=13, product=lemon), Product(cost=13, product=sugar)], true=[Product(cost=23, product=potatoes), Product(cost=23, product=bread)]}
        Set<Product> unmodifiableSet = productList.stream().collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet)); //[Product(cost=23, product=bread), Product(cost=13, product=sugar), Product(cost=13, product=lemon), Product(cost=23, product=potatoes), Product(cost=14, product=orange)]

        Collector<Product, ?, LinkedList<Product>> toLinkedList =
                Collector.of(LinkedList::new, LinkedList::add,
                        (first, second) -> {
                            first.addAll(second);
                            return first;
                        });
        LinkedList<Product> linkedListOfPersons = productList.stream().collect(toLinkedList); //[Product(cost=23, product=potatoes), Product(cost=14, product=orange), Product(cost=13, product=lemon), Product(cost=23, product=bread), Product(cost=13, product=sugar)]

        Stream<Product> streamOfCollection2 = productList.parallelStream();
        boolean isParallel = streamOfCollection2.isParallel(); //true
        boolean bigPrice = streamOfCollection2
                .map(product -> product.getCost() * 12)
                .anyMatch(price -> price > 200); //true

        IntStream intStreamParallel = IntStream.range(1, 150).parallel();
        intStreamParallel.forEach(System.out::println);
        boolean isParallel2 = intStreamParallel.isParallel(); //true

        IntStream intStreamSequential = intStreamParallel.sequential();
        boolean isParallel3 = intStreamSequential.isParallel(); //false

        System.out.println(isParallel3);



//        twiceModifiedStream.forEach(System.out::println);

    }
}
