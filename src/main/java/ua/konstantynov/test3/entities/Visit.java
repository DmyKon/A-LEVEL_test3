package ua.konstantynov.test3.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "visit")
public class Visit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_visit")
    private String id;

    @Column(name = "visit_date")
    private LocalDateTime visitDateTime;

    @Column(name = "diagnosis", columnDefinition = "TEXT")
    private String diagnosis;

    @Column(name = "recipe", columnDefinition = "TEXT")
    private String recipe;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "medicals", joinColumns = @JoinColumn(name = "id_visit"))
    @Column(name = "name")
    private List<String> medicalsList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit that = (Visit) o;
        return id.equals(that.id) &&
                Objects.equals(visitDateTime, that.visitDateTime) &&
                Objects.equals(diagnosis, that.diagnosis) &&
                Objects.equals(recipe, that.recipe) &&
                Objects.equals(patient.getId(), that.patient.getId()) &&
                Objects.equals(doctor.getId(), that.doctor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, visitDateTime, diagnosis, recipe, patient.getId(), doctor.getId());
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id='" + id + '\'' +
                ", visitDate=" + visitDateTime +
                ", diagnosis='" + diagnosis + '\'' +
                ", recipe='" + recipe + '\'' +
                ", medicalsList=" + medicalsList +
                ", patient=" + patient.getId() +
                ", doctor=" + doctor.getId() +
                '}';
    }
}