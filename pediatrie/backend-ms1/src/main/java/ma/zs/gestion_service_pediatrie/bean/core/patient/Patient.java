package ma.zs.gestion_service_pediatrie.bean.core.patient;

import java.util.Objects;
import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.gestion_service_pediatrie.bean.core.commun.Sexe;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "patient")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="patient_seq",sequenceName="patient_seq",allocationSize=1, initialValue = 1)
public class Patient  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String numDossier;
    @Column(length = 500)
    private String nom;
    @Column(length = 500)
    private String prenom;
    private LocalDateTime dateNaissance ;
    @Column(length = 500)
    private String photoProfil;

    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    private Sexe sexe ;

    private List<PatientContact> patientContact ;

    public Patient(){
        super();
    }

    public Patient(Long id,String numDossier){
        this.id = id;
        this.numDossier = numDossier ;
    }
    public Patient(String numDossier){
        this.numDossier = numDossier ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="patient_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNumDossier(){
        return this.numDossier;
    }
    public void setNumDossier(String numDossier){
        this.numDossier = numDossier;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public LocalDateTime getDateNaissance(){
        return this.dateNaissance;
    }
    public void setDateNaissance(LocalDateTime dateNaissance){
        this.dateNaissance = dateNaissance;
    }
    public String getPhotoProfil(){
        return this.photoProfil;
    }
    public void setPhotoProfil(String photoProfil){
        this.photoProfil = photoProfil;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sexe")
    public Sexe getSexe(){
        return this.sexe;
    }
    public void setSexe(Sexe sexe){
        this.sexe = sexe;
    }
    @OneToMany(mappedBy = "patient")

    public List<PatientContact> getPatientContact(){
        return this.patientContact;
    }
    public void setPatientContact(List<PatientContact> patientContact){
        this.patientContact = patientContact;
    }

    @Transient
    public String getLabel() {
        label = numDossier;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id != null && id.equals(patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

