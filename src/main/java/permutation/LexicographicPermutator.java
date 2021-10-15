package permutation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.val;

public class LexicographicPermutator<T extends Comparable<T>> {

    private final List<T> currentPermutation;

    public LexicographicPermutator(List<T> rawInput) {
        List input = new ArrayList(rawInput);
        Collections.sort(input);
        currentPermutation = input;
    }

    public Optional<List<T>> next() {
        if (currentPermutation.isEmpty()) {
            return Optional.empty();
        }

        int lastIndex = currentPermutation.size() - 1;
        int i = lastIndex;

        while (i > 0 && lessOrEqual(i, i - 1)) {
            i--;
        }

        if (i <= 0) {
            return Optional.empty();
        }

        int j = lastIndex;
        while (lessOrEqual(j, i - 1)) {
            j--;
        }

        swap(i - 1, j);
        reverse(i, lastIndex);
        return Optional.of(List.copyOf(currentPermutation));
    }

    public List<List<T>> getAll() {
        if (currentPermutation.isEmpty()) {
            return Collections.emptyList();
        }

        val result = new ArrayList<List<T>>();

        result.add(List.copyOf(currentPermutation));

        Optional<List<T>> nextPermutation;

        do {
            nextPermutation = next();
            nextPermutation.ifPresent(result::add);
        } while (nextPermutation.isPresent());

        return result;
    }

    private boolean less(int k, int m) {
        T tk = currentPermutation.get(k);
        T tm = currentPermutation.get(m);
        return tk.compareTo(tm) < 0;
    }

    private boolean lessOrEqual(int k, int m) {
        T tk = currentPermutation.get(k);
        T tm = currentPermutation.get(m);
        return tk.compareTo(tm) <= 0;
    }

    private void swap(int k, int m) {
        Collections.swap(currentPermutation, k, m);
    }

    private void reverse(int start, int end) {
        List tmp = currentPermutation.subList(start, end + 1);
        Collections.reverse(tmp);
    }

}
