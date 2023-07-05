import java.util.function.Function;
import java.util.stream.Collectors;

public class Core {

    public static double computeIndexOfCoincidence(byte[] textBytes) {
        long textSize = textBytes.length;
        var charOccurrences = new String(textBytes).chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c != '\n')
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        return charOccurrences.values().stream()
            .map(value -> value * (value - 1))
            .reduce(Long::sum)
            .map(computeValue(textSize))
            .orElseThrow(RuntimeException::new);
    }

    private static Function<Long, Double> computeValue(long textSize) {
        return value -> {
            long denominator = textSize * (textSize - 1);
            return ((double) value / denominator);
        };
    }
}
