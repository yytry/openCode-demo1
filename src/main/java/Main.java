import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = numbers.stream().filter(n -> n % 2 == 0).map(n -> n * n).collect(Collectors.toList());

        System.out.println("Evens squared: " + result);
        // 测试commit push
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob", "David");
        names.sort((a, b) -> b.compareTo(a));
        System.out.println("Sorted: " + names);

        List<String> upper = names.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("Uppercase: " + upper);

        String value = null;
        String safe = Optional.ofNullable(value).orElse("default");
        System.out.println("Optional: " + safe);

        IntSupplier fib = new IntSupplier() {
            int a = 0, b = 1;
            public int getAsInt() {
                int temp = a;
                a = b;
                b = temp + b;
                return temp;
            }
        };
        StringBuilder fibSeq = new StringBuilder();
        for (int i = 0; i < 10; i++) fibSeq.append(fib.getAsInt()).append(" ");
        System.out.println("Fibonacci: " + fibSeq.toString().trim());

        String text = "hello world hello java world hello";
        Map<String, Long> wordCount = Arrays.stream(text.split(" ")).collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        System.out.println("Word count: " + wordCount);
    }
}
