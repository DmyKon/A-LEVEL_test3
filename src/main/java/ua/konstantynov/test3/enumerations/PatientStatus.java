package ua.konstantynov.test3.enumerations;

public enum PatientStatus {
    DISCHARGED(0),
    AMBULATORY_TREATMENT(1),
    HOSPITAL_TREATMENT(2),
    DEAD(3);

    PatientStatus(int status) {
        this.status = status;
    }

    private final int status;

    public int getStatus() {
        return status;
    }
}