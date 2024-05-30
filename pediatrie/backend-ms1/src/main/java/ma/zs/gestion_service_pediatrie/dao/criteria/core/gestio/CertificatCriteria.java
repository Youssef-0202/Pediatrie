package  ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio;


import ma.zs.gestion_service_pediatrie.dao.criteria.core.consultatio.ConsultationCriteria;

import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class CertificatCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private LocalDateTime dateDebut;
    private LocalDateTime dateDebutFrom;
    private LocalDateTime dateDebutTo;
    private LocalDateTime dateFin;
    private LocalDateTime dateFinFrom;
    private LocalDateTime dateFinTo;
    private String description;
    private String descriptionLike;
    private String nbrJour;
    private String nbrJourMin;
    private String nbrJourMax;

    private ConsultationCriteria consultatuin ;
    private List<ConsultationCriteria> consultatuins ;


    public CertificatCriteria(){}

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

    public LocalDateTime getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut){
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateDebutFrom(){
        return this.dateDebutFrom;
    }
    public void setDateDebutFrom(LocalDateTime dateDebutFrom){
        this.dateDebutFrom = dateDebutFrom;
    }
    public LocalDateTime getDateDebutTo(){
        return this.dateDebutTo;
    }
    public void setDateDebutTo(LocalDateTime dateDebutTo){
        this.dateDebutTo = dateDebutTo;
    }
    public LocalDateTime getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(LocalDateTime dateFin){
        this.dateFin = dateFin;
    }
    public LocalDateTime getDateFinFrom(){
        return this.dateFinFrom;
    }
    public void setDateFinFrom(LocalDateTime dateFinFrom){
        this.dateFinFrom = dateFinFrom;
    }
    public LocalDateTime getDateFinTo(){
        return this.dateFinTo;
    }
    public void setDateFinTo(LocalDateTime dateFinTo){
        this.dateFinTo = dateFinTo;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public String getNbrJour(){
        return this.nbrJour;
    }
    public void setNbrJour(String nbrJour){
        this.nbrJour = nbrJour;
    }   
    public String getNbrJourMin(){
        return this.nbrJourMin;
    }
    public void setNbrJourMin(String nbrJourMin){
        this.nbrJourMin = nbrJourMin;
    }
    public String getNbrJourMax(){
        return this.nbrJourMax;
    }
    public void setNbrJourMax(String nbrJourMax){
        this.nbrJourMax = nbrJourMax;
    }
      

    public ConsultationCriteria getConsultatuin(){
        return this.consultatuin;
    }

    public void setConsultatuin(ConsultationCriteria consultatuin){
        this.consultatuin = consultatuin;
    }
    public List<ConsultationCriteria> getConsultatuins(){
        return this.consultatuins;
    }

    public void setConsultatuins(List<ConsultationCriteria> consultatuins){
        this.consultatuins = consultatuins;
    }
}
