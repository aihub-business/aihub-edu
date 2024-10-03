package capital.aihub.courses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Course1DemoFruitsResult {

    public static void main(String[] args) {
        // Vytvoření seznamu ovoce
        List<String> fruits = List.of("jablko", "banán", "pomeranč", "hruška", "kiwi", "ananas", "mandarinka");

        // Použití neoptimalizované metody
        List<String> longFruitsUnoptimized = filterLongFruitsUnoptimized(fruits);
        System.out.println("Dlouhé ovoce (neoptimalizováno): " + longFruitsUnoptimized);

        // Použití optimalizované metody
        List<String> longFruitsOptimized = filterLongFruitsOptimized(fruits);
        System.out.println("Dlouhé ovoce (optimalizováno): " + longFruitsOptimized);

        // Měření času pro neoptimalizovanou metodu
        long startTimeUnoptimized = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            filterLongFruitsUnoptimized(fruits);
        }
        long endTimeUnoptimized = System.nanoTime();
        long durationUnoptimized = (endTimeUnoptimized - startTimeUnoptimized) / 1000000;  // převod na milisekundy

        // Měření času pro optimalizovanou metodu
        long startTimeOptimized = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            filterLongFruitsOptimized(fruits);
        }
        long endTimeOptimized = System.nanoTime();
        long durationOptimized = (endTimeOptimized - startTimeOptimized) / 1000000;  // převod na milisekundy

        System.out.println("Čas pro neoptimalizovanou metodu: " + durationUnoptimized + " ms");
        System.out.println("Čas pro optimalizovanou metodu: " + durationOptimized + " ms");
    }

    // Neoptimalizovaná metoda pro filtrování dlouhého ovoce
    public static List<String> filterLongFruitsUnoptimized(List<String> fruits) {
        List<String> result = new ArrayList<>();
        for (String fruit : fruits) {
            if (fruit.length() > 5) {
                result.add(fruit);
            }
        }
        return result;
    }

    // Optimalizovaná metoda používající Stream API
    public static List<String> filterLongFruitsOptimized(List<String> fruits) {
        return fruits.stream()
                .filter(fruit -> fruit.length() > 5)
                .collect(Collectors.toList());
    }
}
