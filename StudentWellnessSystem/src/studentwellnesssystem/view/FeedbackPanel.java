import javax.swing.*;
import java.awt.*;

public class FeedbackPanel extends JPanel {

    public FeedbackPanel() {
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Student Feedback");
        header.setFont(new Font("SansSerif", Font.BOLD, 18));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea feedbackArea = new JTextArea(10, 30);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        feedbackArea.setBorder(BorderFactory.createTitledBorder("Share your experience"));
        
        JButton submitBtn = new JButton("Submit Feedback");
        submitBtn.addActionListener(e -> {
            String feedback = feedbackArea.getText().trim();
            if (!feedback.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Thank you for your feedback!", "Success", JOptionPane.INFORMATION_MESSAGE);
                feedbackArea.setText("");
                // Future: store feedback in DB or log file
            } else {
                JOptionPane.showMessageDialog(this, "Please enter feedback before submitting.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(submitBtn);

        add(header, BorderLayout.NORTH);
        add(feedbackArea, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}