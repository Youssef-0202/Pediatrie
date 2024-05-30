package ma.zs.gestion_service_pediatrie.bean.core.gestio;

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
@Table(name = "ordonnance")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="ordonnance_seq",sequenceName="ordonnance_seq",allocationSize=1, initialValue = 1)
public class Ordonnance  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String nomHospital;
    private LocalDateTime dateOrdonnance ;
    @Column(length = 500)
    private String adresseHospitla;
    @Column(length = 500)
    private String signature;

    private Traitement traitement ;
    private Consultation consultatuin ;


    public Ordonnance(){
        super();
    }

    public Ordonnance(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Ordonnance(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="ordonnance_seq")
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
    public String getNomHospital(){
        return this.nomHospital;
    }
    public void setNomHospital(String nomHospital){
        this.nomHospital = nomHospital;
    }
    public LocalDateTime getDateOrdonnance(){
        return this.dateOrdonnance;
    }
    public void setDateOrdonnance(LocalDateTime dateOrdonnance){
        this.dateOrdonnance = dateOrdonnance;
    }
    public String getAdresseHospitla(){
        return this.adresseHospitla;
    }
    public void setAdresseHospitla(String adresseHospitla){
        this.adresseHospitla = adresseHospitla;
    }
    public String getSignature(){
        return this.signature;
    }
    public void setSignature(String signature){
        this.signature = signature;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "traitement")
    public Traitement getTraitement(){
        return this.traitement;
    }
    public void setTraitement(Traitement traitement){
        this.traitement = traitement;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultatuin")
    public Consultation getConsultatuin(){
        return this.consultatuin;
    }
    public void setConsultatuin(Consultation consultatuin){
        this.consultatuin = consultatuin;
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
        Ordonnance ordonnance = (Ordonnance) o;
        return id != null && id.equals(ordonnance.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

