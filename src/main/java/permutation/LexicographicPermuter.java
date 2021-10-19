package permutation;

import collations.CustomCollationsBootstrap;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.val;

public class LexicographicPermuter<T extends Comparable<T>> {
    static {
        CustomCollationsBootstrap.loadExtraCollations();
    }

    private final Collator collator = Collator.getInstance();
    private final List<T> currentPermutation;
    private boolean isFirst = true;

    {
        collator.setStrength(Collator.IDENTICAL);
        collator.setDecomposition(Collator.FULL_DECOMPOSITION);
    }

    public LexicographicPermuter(List<T> rawInput) {
        currentPermutation = new ArrayList<>(rawInput);
        currentPermutation.sort(collator);
    }

    public Optional<List<T>> next() {
        if (currentPermutation.isEmpty()) {
            return Optional.empty();
        }

        if (isFirst) {
            isFirst = false;
            return Optional.of(List.copyOf(currentPermutation));
        }

        int lastIndex = currentPermutation.size() - 1;
        int i = lastIndex;

        // Find element before the start of the decreasing suffix - pivot
        while (i > 0 && lessOrEqual(i, i - 1)) {
            i--;
        }

        // If already the greatest possible permutation.
        if (i <= 0) {
            return Optional.empty();
        }

        // Find rightmost element in the suffix, greater than the pivot
        int j = lastIndex;
        while (lessOrEqual(j, i - 1)) {
            j--;
        }

        swap(j, i - 1);
        reverse(i, lastIndex);
        return Optional.of(List.copyOf(currentPermutation));
    }

    public List<List<T>> getAll() {
        if (currentPermutation.isEmpty()) {
            return Collections.emptyList();
        }

        val result = new ArrayList<List<T>>();

        Optional<List<T>> nextPermutation;

        do {
            nextPermutation = next();
            nextPermutation.ifPresent(result::add);
        } while (nextPermutation.isPresent());

        return result;
    }

    private boolean lessOrEqual(int k, int m) {
        T tk = currentPermutation.get(k);
        T tm = currentPermutation.get(m);
        return collator.compare(tk, tm) <= 0;
    }

    private void swap(int k, int m) {
        Collections.swap(currentPermutation, k, m);
    }

    private void reverse(int start, int end) {
        List tmp = currentPermutation.subList(start, end + 1);
        Collections.reverse(tmp);
    }

}
