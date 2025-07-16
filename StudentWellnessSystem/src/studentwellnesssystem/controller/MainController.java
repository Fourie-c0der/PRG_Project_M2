public class MainController {
    private AppointmentModel appointmentModel;
    private AppointmentPanel appointmentPanel;

    public MainController() {
        appointmentModel = new AppointmentModel();
        appointmentPanel = new AppointmentPanel();

        // Add action listeners to buttons
        appointmentPanel.getBookButton().addActionListener(e -> {
            // validate, collect fields, call model to insert
        });
    }
}
