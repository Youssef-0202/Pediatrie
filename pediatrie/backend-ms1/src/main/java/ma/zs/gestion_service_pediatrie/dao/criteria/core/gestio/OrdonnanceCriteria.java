package  ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio;


import ma.zs.gestion_service_pediatrie.dao.criteria.core.consultatio.ConsultationCriteria;

import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class OrdonnanceCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String nomHospital;
    private String nomHospitalLike;
    private LocalDateTime dateOrdonnance;
    private LocalDateTime dateOrdonnanceFrom;
    private LocalDateTime dateOrdonnanceTo;
    private String adresseHospitla;
    private String adresseHospitlaLike;
    private String signature;
    private String signatureLike;

    private TraitementCriteria traitement ;
    private List<TraitementCriteria> traitements ;
    private ConsultationCriteria consultatuin ;
    private List<ConsultationCriteria> consultatuins ;


    public OrdonnanceCriteria(){}

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

    public String getNomHospital(){
        return this.nomHospital;
    }
    public void setNomHospital(String nomHospital){
        this.nomHospital = nomHospital;
    }
    public String getNomHospitalLike(){
        return this.nomHospitalLike;
    }
    public void setNomHospitalLike(String nomHospitalLike){
        this.nomHospitalLike = nomHospitalLike;
    }

    public LocalDateTime getDateOrdonnance(){
        return this.dateOrdonnance;
    }
    public void setDateOrdonnance(LocalDateTime dateOrdonnance){
        this.dateOrdonnance = dateOrdonnance;
    }
    public LocalDateTime getDateOrdonnanceFrom(){
        return this.dateOrdonnanceFrom;
    }
    public void setDateOrdonnanceFrom(LocalDateTime dateOrdonnanceFrom){
        this.dateOrdonnanceFrom = dateOrdonnanceFrom;
    }
    public LocalDateTime getDateOrdonnanceTo(){
        return this.dateOrdonnanceTo;
    }
    public void setDateOrdonnanceTo(LocalDateTime dateOrdonnanceTo){
        this.dateOrdonnanceTo = dateOrdonnanceTo;
    }
    public String getAdresseHospitla(){
        return this.adresseHospitla;
    }
    public void setAdresseHospitla(String adresseHospitla){
        this.adresseHospitla = adresseHospitla;
    }
    public String getAdresseHospitlaLike(){
        return this.adresseHospitlaLike;
    }
    public void setAdresseHospitlaLike(String adresseHospitlaLike){
        this.adresseHospitlaLike = adresseHospitlaLike;
    }

    public String getSignature(){
        return this.signature;
    }
    public void setSignature(String signature){
        this.signature = signature;
    }
    public String getSignatureLike(){
        return this.signatureLike;
    }
    public void setSignatureLike(String signatureLike){
        this.signatureLike = signatureLike;
    }


    public TraitementCriteria getTraitement(){
        return this.traitement;
    }

    public void setTraitement(TraitementCriteria traitement){
        this.traitement = traitement;
    }
    public List<TraitementCriteria> getTraitements(){
        return this.traitements;
    }

    public void setTraitements(List<TraitementCriteria> traitements){
        this.traitements = traitements;
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
