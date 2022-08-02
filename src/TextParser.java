import java.util.*;
import java.io.File;

/**
 *Parses text into map and evaluates frequencies
 */
public class TextParser {

    private FrequencyMap[] maps;

    /**
     * Constructor for text parser
     */
    public TextParser() {
        this.maps = readFiles();
    }

    /**
     * Creates a ranking based on user input
     *
     * @param text
     *          user input, a sentence to classify
     * @return priority queue with the most likely language first
     */
    public PriorityQueue<Node> rank(String text) {
        Scanner scan = new Scanner(text);
        List<String> words = new LinkedList<String>();
        while (scan.hasNext()) {
            String word = scan.next().replaceAll("[^A-Za-z]+", "").toLowerCase();
            if (!"".equals(word)) {
                words.add(word);
            }
        }

        PriorityQueue<Node> order = new PriorityQueue<>();

        for (FrequencyMap map : maps) {
            Node dialect = new Node(map.getCategory(), 0);

            for (int i = 0; i < words.size() - 1; i++) {
                dialect.val += map.wordScore(words.get(i), words.get(i + 1));
            }

            // edge case for last word
            dialect.val += map.wordScore(words.get(words.size() - 1), "");

            order.add(dialect);
        }

        return order;

    }

    /**
     * Do preliminary file processing
     *
     * @return array of maps of frequencies containing info for all dialects
    */
    private FrequencyMap[] readFiles() {
        // all files must have at least 3 words
        File path = new File("src/corpus");

        File[] files = path.listFiles();
        FrequencyMap[] maps = new FrequencyMap[files.length];

        // parse through file lines
        for (int i = 0; i < files.length; i++){
            FrequencyMap map = new FrequencyMap(files[i].getName().substring(0, files[i].getName().length() - 4));
            try {
                Scanner scan = new Scanner(files[i]);
                String currentWord = scan.next().replaceAll("[^A-Za-z]+", "").toLowerCase();
                String nextWord = scan.next().replaceAll("[^A-Za-z]+", "").toLowerCase();

                do {
                    map.addWord(currentWord, nextWord);
                    currentWord = nextWord;
                    nextWord = scan.next().replaceAll("[^A-Za-z]+", "").toLowerCase();
                } while (scan.hasNext());
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }

            // save the map to array
            maps[i] = map;
        }
        return maps;
    }
}
