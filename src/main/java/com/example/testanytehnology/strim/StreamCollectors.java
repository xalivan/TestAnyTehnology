package com.example.testanytehnology.strim;

import com.google.common.collect.ImmutableList;
import com.example.testanytehnology.strim.sideeffect.Book;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

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

    }
}
