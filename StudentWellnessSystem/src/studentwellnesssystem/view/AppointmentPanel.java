public class AppointmentPanel extends JPanel {
    private JTextField studentField;
    private JComboBox<String> counselorDropdown;
    private JButton bookButton;

    public AppointmentPanel() {
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Student Name:"));
        studentField = new JTextField();
        add(studentField);

        add(new JLabel("Counselor:"));
        counselorDropdown = new JComboBox<>();
        add(counselorDropdown);

        bookButton = new JButton("Book Appointment");
        add(bookButton);

        // Action listeners, validations, etc.
    }
}
