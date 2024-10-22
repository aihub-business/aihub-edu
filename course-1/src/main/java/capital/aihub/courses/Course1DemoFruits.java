package capital.aihub.courses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Course1DemoFruits {

    public static void main(String[] args) {
        List<String> fruits = List.of("jablko", "banán", "pomeranč", "hruška", "kiwi");

// Use case 1: Návrh metody na filtrování ovoce
// Prompt>  Napiš metodu pro filtrování ovoce delších než 5 znaků
        List<String> longFruits = filterLongFruits(fruits);
        System.out.println("Dlouhé ovoce: " + longFruits);
    }


// Use case 2: Optimalizace kódu
// Prompt> Jak mohu tuto metodu optimalizovat?
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
    public static List<String> filterLongFruits(List<String> fruits) {
        return fruits.stream()
                .filter(fruit -> fruit.length() > 5)
                .collect(Collectors.toList());
    }

// Use case 3: Vysvětlení kódu
// Prompt> Vysvětli, jak funguje Stream API v Javě

// Use case 4: Generování unit testů
// Prompt> Vytvoř unit test pro metodu filterLongFruits

}
