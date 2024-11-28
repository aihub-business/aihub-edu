package business.aihub.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Course1DemoFruitsResult {

    public static void main(String[] args) {

        int numStrings = 100000;

        // Generování seznamu s náhodnými řetězci
        List<String> fruits = generateRandomStrings(numStrings);
        // Vytvoření seznamu ovoce
        //List<String> fruits = List.of("jablko", "banán", "pomeranč", "hruška", "kiwi", "ananas", "mandarinka");

        // Použití neoptimalizované metody
        List<String> longFruitsUnoptimized = filterLongFruitsUnoptimized(fruits);
        System.out.println("Dlouhé ovoce (neoptimalizováno): " + longFruitsUnoptimized);

        // Použití optimalizované metody
        List<String> longFruitsOptimized = filterLongFruitsOptimized(fruits);
        System.out.println("Dlouhé ovoce (optimalizováno): " + longFruitsOptimized);

        // Měření času pro neoptimalizovanou metodu
        long startTimeUnoptimized = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            filterLongFruitsUnoptimized(fruits);
        }
        long endTimeUnoptimized = System.nanoTime();
        long durationUnoptimized = (endTimeUnoptimized - startTimeUnoptimized) / 1000000;  // převod na milisekundy

        // Měření času pro optimalizovanou metodu
        long startTimeOptimized = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            filterLongFruitsOptimized(fruits);
        }
        long endTimeOptimized = System.nanoTime();
        long durationOptimized = (endTimeOptimized - startTimeOptimized) / 1000000;  // převod na milisekundy

        System.out.println("Čas pro neoptimalizovanou metodu: " + durationUnoptimized + " ms");
        System.out.println("Čas pro optimalizovanou metodu: " + durationOptimized + " ms");
    }

   // která z funkcí je optimálněší z pohledu výkonu zpracování

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
    // Funkce pro generování seznamu náhodných řetězců
    public static List<String> generateRandomStrings(int numStrings) {
        List<String> stringList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numStrings; i++) {
            stringList.add(generateRandomString(random.nextInt(10) + 1)); // Náhodné délky řetězců od 1 do 10 znaků
        }

        return stringList;
    }

    // Funkce pro generování náhodného řetězce o specifikované délce
    public static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return sb.toString();
    }
}
