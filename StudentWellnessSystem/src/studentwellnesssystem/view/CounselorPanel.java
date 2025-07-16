import javax.swing.*;
import java.awt.*;

public class CounselorPanel extends JPanel {

    public CounselorPanel() {
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Available Counselors");
        header.setFont(new Font("SansSerif", Font.BOLD, 18));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        String[] columns = {"Name", "Specialty", "Availability"};
        Object[][] data = {
            {"Dr. Smith", "Mental Health", "Mon/Wed/Fri"},
            {"Ms. Naidoo", "Academic Stress", "Tues/Thurs"},
            {"Mr. Mokoena", "Career Guidance", "Daily"}
        };

        JTable counselorTable = new JTable(data, columns);
        JScrollPane tableScroll = new JScrollPane(counselorTable);

        add(header, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
    }
}