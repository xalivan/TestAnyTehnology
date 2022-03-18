package com.example.testanytehnology.strim;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.anyOf;

public class StreamBasics2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("One");
        list.add("OneAndOnly");
        list.add("Derek");
        list.add("Change");
        list.add("factory");
        list.add("justBefore");
        list.add("Italy");
        list.add("Italy");
        list.add("Thursday");
        list.add("");
        list.add("");

        Stream<String> stream = list.stream().filter(element -> element.contains("d")); //OneAndOnly Thursday

        String PATH = "C:\\FATHER\\TestAnyTehnology\\src\\main\\java\\com\\example\\testanytehnology\\strim\\file.txt";
        List<String> uris = new ArrayList<>();
        uris.add(PATH);
        Stream<Path> stream2 = uris.stream().map(uri -> Paths.get(uri));

        List<Integer> integers = Arrays.asList(1, 1, 1);
        Integer reduced = integers.stream().reduce(23, Integer::sum); //26

        List<String> list2 = Arrays.asList("A", "B", "C", "D");
        Optional<String> result = list2.stream().findAny();

        Map<String, Integer> ages = new HashMap<>();
        ages.put("John", 25);
        ages.put("Freddy", 24);
        ages.put("Samuel", 30);

        ages.forEach((name, age) -> System.out.println(name + " is " + age + " years old"));

        List<String> names = Arrays.asList("Angela", "Aaron", "Bob", "Claire", "David");

        List<String> namesWithA = names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());

        List<String> names2 = Arrays.asList("bob", "josh", "megan");
        names2.replaceAll(String::toUpperCase);

        List<Integer> values = Arrays.asList(3, 5, 8, 9, 12);
        int sum = values.stream().reduce(0, Integer::sum);

        Thread thread = new Thread(() -> System.out.println("Hello From Another Thread"));
        thread.start();

//        System.out.println(reduced);
//        stream2.forEach(System.out::println);
    }


}
