package  ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie;


import ma.zs.gestion_service_pediatrie.dao.criteria.core.consultatio.ConsultationCriteria;

import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class AnalyseMedicaleCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private LocalDateTime dateAnalyseMedicale;
    private LocalDateTime dateAnalyseMedicaleFrom;
    private LocalDateTime dateAnalyseMedicaleTo;
    private String valeur;
    private String valeurMin;
    private String valeurMax;
    private String valeurRang;
    private String valeurRangLike;

    private EpreuveCriteria epreuve ;
    private List<EpreuveCriteria> epreuves ;
    private ConsultationCriteria consultation ;
    private List<ConsultationCriteria> consultations ;


    public AnalyseMedicaleCriteria(){}

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

    public LocalDateTime getDateAnalyseMedicale(){
        return this.dateAnalyseMedicale;
    }
    public void setDateAnalyseMedicale(LocalDateTime dateAnalyseMedicale){
        this.dateAnalyseMedicale = dateAnalyseMedicale;
    }
    public LocalDateTime getDateAnalyseMedicaleFrom(){
        return this.dateAnalyseMedicaleFrom;
    }
    public void setDateAnalyseMedicaleFrom(LocalDateTime dateAnalyseMedicaleFrom){
        this.dateAnalyseMedicaleFrom = dateAnalyseMedicaleFrom;
    }
    public LocalDateTime getDateAnalyseMedicaleTo(){
        return this.dateAnalyseMedicaleTo;
    }
    public void setDateAnalyseMedicaleTo(LocalDateTime dateAnalyseMedicaleTo){
        this.dateAnalyseMedicaleTo = dateAnalyseMedicaleTo;
    }
    public String getValeur(){
        return this.valeur;
    }
    public void setValeur(String valeur){
        this.valeur = valeur;
    }   
    public String getValeurMin(){
        return this.valeurMin;
    }
    public void setValeurMin(String valeurMin){
        this.valeurMin = valeurMin;
    }
    public String getValeurMax(){
        return this.valeurMax;
    }
    public void setValeurMax(String valeurMax){
        this.valeurMax = valeurMax;
    }
      
    public String getValeurRang(){
        return this.valeurRang;
    }
    public void setValeurRang(String valeurRang){
        this.valeurRang = valeurRang;
    }
    public String getValeurRangLike(){
        return this.valeurRangLike;
    }
    public void setValeurRangLike(String valeurRangLike){
        this.valeurRangLike = valeurRangLike;
    }


    public EpreuveCriteria getEpreuve(){
        return this.epreuve;
    }

    public void setEpreuve(EpreuveCriteria epreuve){
        this.epreuve = epreuve;
    }
    public List<EpreuveCriteria> getEpreuves(){
        return this.epreuves;
    }

    public void setEpreuves(List<EpreuveCriteria> epreuves){
        this.epreuves = epreuves;
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
