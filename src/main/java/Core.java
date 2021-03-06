import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Core {

    public static double computeIndexOfCoincidence(byte[] textBytes) {
        HashMap<Character, Long> charOccurrences = new HashMap<>();
        long textSize = textBytes.length;
        for (byte b : textBytes) {
            char letter = (char) b;
            if (charOccurrences.containsKey(letter))
                charOccurrences.put(letter, charOccurrences.get(letter) + 1);
            else
                charOccurrences.put(letter, 1L);
        }
        charOccurrences.remove('\n');
        return charOccurrences.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() * (e.getValue() - 1)))
            .values().stream().reduce(Long::sum).map(value -> {
                long denominator = textSize * (textSize - 1);
                return ((double) value / denominator);
            }).orElseThrow(RuntimeException::new);
    }
}
