package ua.konstantynov.test3.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ua.konstantynov.test3.enumerations.PatientStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@ToString
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_patient")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PatientStatus patientStatus;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Visit> visits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id.equals(patient.id) &&
                Objects.equals(firstName, patient.firstName) &&
                Objects.equals(lastName, patient.lastName) &&
                Objects.equals(phoneNumber, patient.phoneNumber) &&
                Objects.equals(birthDate, patient.birthDate) &&
                patientStatus == patient.patientStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, birthDate, patientStatus);
    }
}