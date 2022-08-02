import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Mutable class that maps frequencies to words.
 */
public class FrequencyMap  {

    private String category;
    private Map<String, Word> map;

    /**
     * Create a frequency map for a specified dialect
     * @param dialect
     *              the dialect to identify the word map
     */
    public FrequencyMap(String dialect) {
        this.category = dialect;
        this.map = new HashMap<>();
    }

    /**
     * Add a word to the map
     *
     * @param word
     *          the word in the map
     * @param following
     *          the word following the word in the map
     */
    public void addWord(String word, String following) {
        if (map.containsKey(word)) {
            Word details = map.get(word);
            details.increaseFrequency();
            details.addFollowing(following);
        } else {
            HashSet<String> followingSet = new HashSet<>();
            followingSet.add(following);
            map.put(word, new Word(1, followingSet));
        }
    }

    /**
     * Number of appearances of a particular word in the map
     *
     * @param word
     *          the word whose frequency we want to know
     * @return the word's frequency in the map
     */
    public int wordFrequency(String word) {
        if (map.containsKey(word)) {
            return map.get(word).getFrequency();
        }
        return 0;
    }

    /**
     * Score the word based on the number of times it appears
     *
     * @param word
     *          the word in the map
     * @param following
     *          the word following the word in the map
     *
     * @return score of the word based on frequency
     */
    public int wordScore(String word, String following) {
        int score = 0;
        // check possible gender matches
        if (word.charAt(word.length() - 1) == 'a' && word.length() > 1) {
            Word obj = map.get(word.substring(0, word.length() - 1) + 'o');
            score += obj == null ? 0 : obj.getFrequency();
        } else {
            if (word.charAt(word.length() - 1) == 'o' && word.length() > 1) {
                Word obj = map.get(word.substring(0, word.length() - 1) + 'a');
                score += obj == null ? 0 : obj.getFrequency();
            }
        }
        if (wordFrequency(word) != 0) {
            Word obj = map.get(word);
            score += 2 * obj.getFrequency() + (obj.hasFollowing(following) ? 10 : 0);
        }
        return score;
    }

    /**
     * Access and retrieve category (dialect) of the map
     *
     * @return category
     */
    public String getCategory() {
        return this.category;
    }

}
