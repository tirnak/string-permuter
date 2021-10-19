import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.Collator;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import lombok.val;
import org.junit.jupiter.api.Test;
import permutation.LexicographicPermuter;

public class LexicographicPermuterTest {

    @Test
    public void emptyInput() {
        val items = List.of();
        val permutator = new LexicographicPermuter(items);
        val result = permutator.getAll();
        assertEquals(0, result.size());
    }

    @Test
    public void twoElements() {
        val items = List.of("a", "b");
        val permutator = new LexicographicPermuter(items);
        val result = permutator.getAll();
        assertEquals(2, result.size());
        assertEquals(List.of("a", "b"), result.get(0));
        assertEquals(List.of("b", "a"), result.get(1));
    }

    @Test
    public void threeElements() {
        val items = List.of("z", "x", "y");
        val permutator = new LexicographicPermuter(items);
        val result = permutator.getAll();
        assertEquals(6, result.size());
        assertEquals(List.of("x", "y", "z"), result.get(0));
        assertEquals(List.of("z", "y", "x"), result.get(5));
    }

    @Test
    public void repeatingElements() {
        val items = List.of("1", "1", "2");
        val permutator = new LexicographicPermuter(items);
        val result = permutator.getAll();
        assertEquals(3, result.size());
        assertEquals(List.of("1", "1", "2"), result.get(0));
        assertEquals(List.of("2", "1", "1"), result.get(2));
    }

    @Test
    public void testCollationEsEs() {
        val curLocale = Locale.getDefault();

        System.out.println(Arrays.toString(Collator.getAvailableLocales()));
        Locale.setDefault(new Locale("es", "ES"));

        val items = List.of("luz", "llama", "curioso", "chalina");
        val permutator = new LexicographicPermuter(items);
        val result = permutator.getAll();
        assertEquals(24, result.size());
        assertEquals(List.of("curioso", "chalina", "luz", "llama"), result.get(0));

        Locale.setDefault(curLocale);
    }
}
