package  ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie;


import ma.zs.gestion_service_pediatrie.dao.criteria.core.consultatio.ConsultationCriteria;

import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class FichePatientCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private LocalDateTime dateFichePatient;
    private LocalDateTime dateFichePatientFrom;
    private LocalDateTime dateFichePatientTo;
    private String antecedentFamillial;
    private String antecedentFamillialLike;
    private String histoireMaladie;
    private String histoireMaladieLike;
    private String examenFichePatient;
    private String examenFichePatientLike;
    private String conclusionFichePatient;
    private String conclusionFichePatientLike;

    private AntecedentCriteria antecedent ;
    private List<AntecedentCriteria> antecedents ;
    private ConsultationCriteria consultation ;
    private List<ConsultationCriteria> consultations ;


    public FichePatientCriteria(){}

    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public String getRefLike(){
        return this.refLike;
    }
    public void setRefLike(String refLike){
        this.refLike = refLike;
    }

    public LocalDateTime getDateFichePatient(){
        return this.dateFichePatient;
    }
    public void setDateFichePatient(LocalDateTime dateFichePatient){
        this.dateFichePatient = dateFichePatient;
    }
    public LocalDateTime getDateFichePatientFrom(){
        return this.dateFichePatientFrom;
    }
    public void setDateFichePatientFrom(LocalDateTime dateFichePatientFrom){
        this.dateFichePatientFrom = dateFichePatientFrom;
    }
    public LocalDateTime getDateFichePatientTo(){
        return this.dateFichePatientTo;
    }
    public void setDateFichePatientTo(LocalDateTime dateFichePatientTo){
        this.dateFichePatientTo = dateFichePatientTo;
    }
    public String getAntecedentFamillial(){
        return this.antecedentFamillial;
    }
    public void setAntecedentFamillial(String antecedentFamillial){
        this.antecedentFamillial = antecedentFamillial;
    }
    public String getAntecedentFamillialLike(){
        return this.antecedentFamillialLike;
    }
    public void setAntecedentFamillialLike(String antecedentFamillialLike){
        this.antecedentFamillialLike = antecedentFamillialLike;
    }

    public String getHistoireMaladie(){
        return this.histoireMaladie;
    }
    public void setHistoireMaladie(String histoireMaladie){
        this.histoireMaladie = histoireMaladie;
    }
    public String getHistoireMaladieLike(){
        return this.histoireMaladieLike;
    }
    public void setHistoireMaladieLike(String histoireMaladieLike){
        this.histoireMaladieLike = histoireMaladieLike;
    }

    public String getExamenFichePatient(){
        return this.examenFichePatient;
    }
    public void setExamenFichePatient(String examenFichePatient){
        this.examenFichePatient = examenFichePatient;
    }
    public String getExamenFichePatientLike(){
        return this.examenFichePatientLike;
    }
    public void setExamenFichePatientLike(String examenFichePatientLike){
        this.examenFichePatientLike = examenFichePatientLike;
    }

    public String getConclusionFichePatient(){
        return this.conclusionFichePatient;
    }
    public void setConclusionFichePatient(String conclusionFichePatient){
        this.conclusionFichePatient = conclusionFichePatient;
    }
    public String getConclusionFichePatientLike(){
        return this.conclusionFichePatientLike;
    }
    public void setConclusionFichePatientLike(String conclusionFichePatientLike){
        this.conclusionFichePatientLike = conclusionFichePatientLike;
    }


    public AntecedentCriteria getAntecedent(){
        return this.antecedent;
    }

    public void setAntecedent(AntecedentCriteria antecedent){
        this.antecedent = antecedent;
    }
    public List<AntecedentCriteria> getAntecedents(){
        return this.antecedents;
    }

    public void setAntecedents(List<AntecedentCriteria> antecedents){
        this.antecedents = antecedents;
    }
    public ConsultationCriteria getConsultation(){
        return this.consultation;
    }

    public void setConsultation(ConsultationCriteria consultation){
        this.consultation = consultation;
    }
    public List<ConsultationCriteria> getConsultations(){
        return this.consultations;
    }

    public void setConsultations(List<ConsultationCriteria> consultations){
        this.consultations = consultations;
    }
}
