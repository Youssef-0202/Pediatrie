package  ma.zs.gestion_service_pediatrie.dao.criteria.core.patient;



import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;

public class PatientContactCriteria extends  BaseCriteria  {

    private String cin;
    private String cinLike;
    private String email;
    private String emailLike;
    private String nom;
    private String nomLike;
    private String prenom;
    private String prenomLike;
    private String telephone;
    private String telephoneLike;
    private String adresse;
    private String adresseLike;

    private RelationCriteria relation ;
    private List<RelationCriteria> relations ;
    private PatientCriteria patient ;
    private List<PatientCriteria> patients ;


    public PatientContactCriteria(){}

    public String getCin(){
        return this.cin;
    }
    public void setCin(String cin){
        this.cin = cin;
    }
    public String getCinLike(){
        return this.cinLike;
    }
    public void setCinLike(String cinLike){
        this.cinLike = cinLike;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmailLike(){
        return this.emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }

    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNomLike(){
        return this.nomLike;
    }
    public void setNomLike(String nomLike){
        this.nomLike = nomLike;
    }

    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public String getPrenomLike(){
        return this.prenomLike;
    }
    public void setPrenomLike(String prenomLike){
        this.prenomLike = prenomLike;
    }

    public String getTelephone(){
        return this.telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public String getTelephoneLike(){
        return this.telephoneLike;
    }
    public void setTelephoneLike(String telephoneLike){
        this.telephoneLike = telephoneLike;
    }

    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public String getAdresseLike(){
        return this.adresseLike;
    }
    public void setAdresseLike(String adresseLike){
        this.adresseLike = adresseLike;
    }


    public RelationCriteria getRelation(){
        return this.relation;
    }

    public void setRelation(RelationCriteria relation){
        this.relation = relation;
    }
    public List<RelationCriteria> getRelations(){
        return this.relations;
    }

    public void setRelations(List<RelationCriteria> relations){
        this.relations = relations;
    }
    public PatientCriteria getPatient(){
        return this.patient;
    }

    public void setPatient(PatientCriteria patient){
        this.patient = patient;
    }
    public List<PatientCriteria> getPatients(){
        return this.patients;
    }

    public void setPatients(List<PatientCriteria> patients){
        this.patients = patients;
    }
}
