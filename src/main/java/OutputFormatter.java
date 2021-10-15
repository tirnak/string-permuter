import java.util.List;
import java.util.StringJoiner;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import lombok.val;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Builder
public class OutputFormatter {

    String delimiter;
    @Builder.Default
    String prefix = "";
    @Builder.Default
    String suffix = "";

    public String format(List<String> raw) {
        val stringJoiner = new StringJoiner(delimiter, prefix, suffix);
        raw.forEach(stringJoiner::add);
        return stringJoiner.toString();
    }
}
