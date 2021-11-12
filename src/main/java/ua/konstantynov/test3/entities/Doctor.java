package ua.konstantynov.test3.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ua.konstantynov.test3.enumerations.DoctorStatus;
import ua.konstantynov.test3.enumerations.MedicalSpeciality;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_person")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "medical_speciality")
    private MedicalSpeciality medicalSpeciality;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DoctorStatus doctorStatus;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Visit> visits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id.equals(doctor.id) &&
                Objects.equals(firstName, doctor.firstName) &&
                Objects.equals(lastName, doctor.lastName) &&
                Objects.equals(phoneNumber, doctor.phoneNumber) &&
                medicalSpeciality == doctor.medicalSpeciality &&
                doctorStatus == doctor.doctorStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, medicalSpeciality, doctorStatus);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", medicalSpeciality=" + medicalSpeciality +
                ", doctorStatus=" + doctorStatus +
                '}';
    }
}