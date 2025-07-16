import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private int id;
    private String studentName;
    private int counselorId;
    private LocalDate date;
    private LocalTime time;
    private String status;

    public Appointment(String studentName, int counselorId, LocalDate date, LocalTime time, String status) {
        this.studentName = studentName;
        this.counselorId = counselorId;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    // Getters & Setters
}
