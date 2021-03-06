package ua.konstantynov.test3.enumerations;

public enum DoctorStatus {
    ON_VACATION(0),
    WORKING(1),
    ON_SICK_LEAVE(2),
    DISMISSED(3);

    DoctorStatus(int status) {
        this.status = status;
    }

    private final int status;

    public int getStatus() {
        return status;
    }
}