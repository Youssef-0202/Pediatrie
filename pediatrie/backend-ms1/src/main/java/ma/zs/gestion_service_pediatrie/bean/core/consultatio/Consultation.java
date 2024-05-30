package ma.zs.gestion_service_pediatrie.bean.core.consultatio;

import java.util.Objects;
import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.gestion_service_pediatrie.bean.core.dossie.FichePatient;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Radiologie;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Infermier;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Medecin;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Epreuve;
import ma.zs.gestion_service_pediatrie.bean.core.rappor.SyntheseMedicale;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Patient;
import ma.zs.gestion_service_pediatrie.bean.core.rappor.Diagnostic;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.AnalyseMedicale;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.TypeImage;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Antecedent;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "consultation")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="consultation_seq",sequenceName="consultation_seq",allocationSize=1, initialValue = 1)
public class Consultation  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    private LocalDateTime dateConsultation ;
    private LocalDateTime heureConsultation ;
    @Column(length = 500)
    private String typeConsultation;

    private Medecin medecin ;
    private Infermier infermier ;
    private Patient patient ;

    private List<AnalyseMedicale> analyseMedicale ;
    private List<FichePatient> fichePatient ;
    private List<Radiologie> radiologie ;
    private List<Diagnostic> diagnostic ;
    private List<SyntheseMedicale> syntheseMedicale ;

    public Consultation(){
        super();
    }

    public Consultation(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Consultation(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="consultation_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public LocalDateTime getDateConsultation(){
        return this.dateConsultation;
    }
    public void setDateConsultation(LocalDateTime dateConsultation){
        this.dateConsultation = dateConsultation;
    }
    public LocalDateTime getHeureConsultation(){
        return this.heureConsultation;
    }
    public void setHeureConsultation(LocalDateTime heureConsultation){
        this.heureConsultation = heureConsultation;
    }
    public String getTypeConsultation(){
        return this.typeConsultation;
    }
    public void setTypeConsultation(String typeConsultation){
        this.typeConsultation = typeConsultation;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medecin")
    public Medecin getMedecin(){
        return this.medecin;
    }
    public void setMedecin(Medecin medecin){
        this.medecin = medecin;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infermier")
    public Infermier getInfermier(){
        return this.infermier;
    }
    public void setInfermier(Infermier infermier){
        this.infermier = infermier;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient")
    public Patient getPatient(){
        return this.patient;
    }
    public void setPatient(Patient patient){
        this.patient = patient;
    }
    @OneToMany(mappedBy = "consultation")

    public List<AnalyseMedicale> getAnalyseMedicale(){
        return this.analyseMedicale;
    }
    public void setAnalyseMedicale(List<AnalyseMedicale> analyseMedicale){
        this.analyseMedicale = analyseMedicale;
    }
    @OneToMany(mappedBy = "consultation")

    public List<FichePatient> getFichePatient(){
        return this.fichePatient;
    }
    public void setFichePatient(List<FichePatient> fichePatient){
        this.fichePatient = fichePatient;
    }
    @OneToMany(mappedBy = "consultation")

    public List<Radiologie> getRadiologie(){
        return this.radiologie;
    }
    public void setRadiologie(List<Radiologie> radiologie){
        this.radiologie = radiologie;
    }
    @OneToMany(mappedBy = "consultation")

    public List<Diagnostic> getDiagnostic(){
        return this.diagnostic;
    }
    public void setDiagnostic(List<Diagnostic> diagnostic){
        this.diagnostic = diagnostic;
    }
    @OneToMany(mappedBy = "consultation")

    public List<SyntheseMedicale> getSyntheseMedicale(){
        return this.syntheseMedicale;
    }
    public void setSyntheseMedicale(List<SyntheseMedicale> syntheseMedicale){
        this.syntheseMedicale = syntheseMedicale;
    }

    @Transient
    public String getLabel() {
        label = ref;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consultation consultation = (Consultation) o;
        return id != null && id.equals(consultation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

