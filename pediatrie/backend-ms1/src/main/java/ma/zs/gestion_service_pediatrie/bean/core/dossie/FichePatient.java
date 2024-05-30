package ma.zs.gestion_service_pediatrie.bean.core.dossie;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fiche_patient")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="fiche_patient_seq",sequenceName="fiche_patient_seq",allocationSize=1, initialValue = 1)
public class FichePatient  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    private LocalDateTime dateFichePatient ;
    @Column(length = 500)
    private String antecedentFamillial;
    @Column(length = 500)
    private String histoireMaladie;
    @Column(length = 500)
    private String examenFichePatient;
    @Column(length = 500)
        private String conclusionFichePatient;
    private Antecedent antecedent;
    private Consultation consultation;


    public FichePatient(){
        super();
    }

    public FichePatient(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public FichePatient(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="fiche_patient_seq")
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
    public LocalDateTime getDateFichePatient(){
        return this.dateFichePatient;
    }
    public void setDateFichePatient(LocalDateTime dateFichePatient){
        this.dateFichePatient = dateFichePatient;
    }
    public String getAntecedentFamillial(){
        return this.antecedentFamillial;
    }
    public void setAntecedentFamillial(String antecedentFamillial){
        this.antecedentFamillial = antecedentFamillial;
    }
    public String getHistoireMaladie(){
        return this.histoireMaladie;
    }
    public void setHistoireMaladie(String histoireMaladie){
        this.histoireMaladie = histoireMaladie;
    }
    public String getExamenFichePatient(){
        return this.examenFichePatient;
    }
    public void setExamenFichePatient(String examenFichePatient){
        this.examenFichePatient = examenFichePatient;
    }
    public String getConclusionFichePatient(){
        return this.conclusionFichePatient;
    }
    public void setConclusionFichePatient(String conclusionFichePatient){
        this.conclusionFichePatient = conclusionFichePatient;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "antecedent")
    public Antecedent getAntecedent(){
        return this.antecedent;
    }
    public void setAntecedent(Antecedent antecedent){
        this.antecedent = antecedent;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation")
    public Consultation getConsultation(){
        return this.consultation;
    }
    public void setConsultation(Consultation consultation){
        this.consultation = consultation;
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
        FichePatient fichePatient = (FichePatient) o;
        return id != null && id.equals(fichePatient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

