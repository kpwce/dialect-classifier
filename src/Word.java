import java.util.Set;
/**
 * Mutable class that contains metadata for a specified word.
 */
public class Word {

    // a word is classified by its frequency and following attributes

    // number of times the word has appeared in the text
    private int frequency;

    // all the words seen to be following this word
    private Set following;

    // Representation inv: this != null, frequency > 0, following != null

    /**
     * Constructor for word object
     *
     * @param frequency
     *          the initial frequency of a word
     * @param following
     *          the word following the original word
     */
    public Word(int frequency, Set<String> following) {
        this.frequency = frequency;
        this.following = following;
    }

    /**
     * Gets word frequency
     *
     * @return number of times word has been seen
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Increases frequency by 1
     *
     * @effects increases frequency of this by 1
     */
    public void increaseFrequency() {
        frequency++;
    }

    /**
     * Checks if a word follows this word
     *
     * @param word
     *          the word we want to check that may follow the this word
     *
     * @return whether has seen specified word follow this word
     */
    public boolean hasFollowing(String word) {
        return following.contains(word);
    }

    /**
     * Add a word that follows this word
     * @param word
     *          the word to add as a following word
     * @effects add word to list of following words
     */
    public void addFollowing(String word) {
        following.add(word);
    }
}
