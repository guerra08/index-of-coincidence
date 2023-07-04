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
            .reduce(Long::sum).map(value -> {
                long denominator = textSize * (textSize - 1);
                return ((double) value / denominator);
            }).orElseThrow(RuntimeException::new);
    }
}
