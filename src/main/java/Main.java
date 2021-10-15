import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import lombok.val;
import permutation.LexicographicPermuter;

public class Main {

    public static void main(String[] args) {
        val scanner = new Scanner(System.in);
        val inputRaw = scanner.nextLine().trim();
        if (inputRaw.isEmpty()) {
            System.exit(0);
        }
        val input = inputRaw.split("\\s+");

        val permutator = new LexicographicPermuter<String>(Arrays.asList(input));

        Optional<List<String>> nextPermutation;
        val formatter = OutputFormatter.builder()
            .delimiter(", ")
            .build();

        do {
            nextPermutation = permutator.next();
            nextPermutation
                .map(formatter::format)
                .ifPresent(System.out::println);
        } while (nextPermutation.isPresent());
    }
}
