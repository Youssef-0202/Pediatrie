package ma.zs.gestion_service_pediatrie.bean.core.patient;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "patient_contact")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="patient_contact_seq",sequenceName="patient_contact_seq",allocationSize=1, initialValue = 1)
public class PatientContact  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String cin;
    @Column(length = 500)
    private String email;
    @Column(length = 500)
    private String nom;
    @Column(length = 500)
    private String prenom;
    @Column(length = 500)
    private String telephone;
    @Column(length = 500)
    private String adresse;

    private Relation relation ;
    private Patient patient ;


    public PatientContact(){
        super();
    }

    public PatientContact(Long id,String email){
        this.id = id;
        this.email = email ;
    }
    public PatientContact(String email){
        this.email = email ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="patient_contact_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getCin(){
        return this.cin;
    }
    public void setCin(String cin){
        this.cin = cin;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
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
    public String getTelephone(){
        return this.telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relation")
    public Relation getRelation(){
        return this.relation;
    }
    public void setRelation(Relation relation){
        this.relation = relation;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient")
    public Patient getPatient(){
        return this.patient;
    }
    public void setPatient(Patient patient){
        this.patient = patient;
    }

    @Transient
    public String getLabel() {
        label = email;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientContact patientContact = (PatientContact) o;
        return id != null && id.equals(patientContact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

