package ua.konstantynov.test3.enumerations;

public enum MedicalSpeciality {
    ANESTHESIOLOGY(0),
    DERMATOLOGY(1),
    NEUROLOGY(2),
    NUCLEAR_MEDICINE(3),
    OPHTHALMOLOGY(4),
    PATHOLOGY(5),
    PEDIATRICS(6),
    PSYCHIATRY(7),
    SURGERY(8),
    UROLOGY(9);

    MedicalSpeciality(int status) {
        this.status = status;
    }

    private final int status;

    public int getStatus() {
        return status;

    }
}