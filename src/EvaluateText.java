
import java.util.PriorityQueue;
import javax.swing.*;

/**
 * Driver class with UI
 */
public class EvaluateText {

    public static void main(String[] args) {
        TextParser parsed = new TextParser();
        JTextField input = new JTextField(15);
        JTextArea output = new JTextArea(5, 15);

        output.setEditable(false);
        JButton results = new JButton("Results");
        JButton clear = new JButton("Reset");
        output.setEditable(false);
        input.requestFocus();
        results.setBounds(0,0,95,30);
        clear.setBounds(0,50,95,30);

        // handle button actions
        results.addActionListener(ae -> {
            String text = input.getText();
            PriorityQueue<Node> ranking = parsed.rank(text);

            StringBuilder rankingFormatted = new StringBuilder();
            int start = 1;
            for (Node dialect : ranking) {
                rankingFormatted.append(start).append(": ").append(dialect.key).append("\n");
                start++;
            }
            output.setText(rankingFormatted.toString());
        });

        clear.addActionListener(ae -> {
            input.setText("");
            output.setText("");
        });

        // place buttons and text fields onto screen
        JPanel pane = new JPanel();
        pane.setSize(300, 200);
        pane.add(input);
        pane.add(output);
        pane.add(results);
        pane.add(clear);

        JFrame frame = new JFrame("Detector");
        frame.add(pane);
        frame.setSize(200, 200);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


}
