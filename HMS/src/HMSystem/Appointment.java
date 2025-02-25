package HMSystem;

import java.sql.Date;

public class Appointment {
    private int appointment_id;
    private int patient_id;
    private int doctor_id;
    private Date appointmentDate;
    private String reason;

    // Constructor without appointment_id
    public Appointment(int patient_id, int doctor_id, Date appointmentDate, String reason) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.appointmentDate = appointmentDate;
        this.reason = reason;
    }

    // Constructor with appointment_id
    public Appointment(int appointment_id, int patient_id, int doctor_id, Date appointmentDate, String reason) {
        this.appointment_id = appointment_id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.appointmentDate = appointmentDate;
        this.reason = reason;
    }

    // Getters and Setters
    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
