import java.sql.Connection;
import java.sql.SQLException;

public class AppointmentModel {
    public void addAppointment(Appointment appointment) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO Appointments (studentName, counselorId, appointmentDate, appointmentTime, status) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, appointment.getStudentName());
        ps.setInt(2, appointment.getCounselorId());
        ps.setDate(3, Date.valueOf(appointment.getDate()));
        ps.setTime(4, Time.valueOf(appointment.getTime()));
        ps.setString(5, appointment.getStatus());
        ps.executeUpdate();
    }
}
