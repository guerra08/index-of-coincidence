import java.util.HashMap;
import java.util.Optional;

public class Core {

    HashMap<Character, Long> charOccurrences;

    public Core() {
        this.charOccurrences = charOccurrences = new HashMap<>();
    }

    public HashMap<Character, Long> getCharOccurrences(){
        return charOccurrences;
    }

    public double computeIndexOfCoincidence(byte[] textBytes){
        long textSize = textBytes.length;
        for (byte b : textBytes){
            char letter = (char) b;
            if(charOccurrences.containsKey(letter))
                charOccurrences.put(letter, charOccurrences.get(letter) + 1);
            else
                charOccurrences.put(letter, 1L);
        }
        charOccurrences.remove('\n');
        HashMap<Character, Long> normalizedFreq = new HashMap<>();
        charOccurrences.forEach((key, val) -> normalizedFreq.put(key, val * (val - 1)));
        Optional<Long> reducedSumOpt = normalizedFreq.values().stream().reduce(Long::sum);
        if(reducedSumOpt.isPresent()){
            long reducedSum = reducedSumOpt.get();
            long denominator = textSize * (textSize - 1);
            return ((double) reducedSum / denominator);
        }
        return -1;
    }

}
