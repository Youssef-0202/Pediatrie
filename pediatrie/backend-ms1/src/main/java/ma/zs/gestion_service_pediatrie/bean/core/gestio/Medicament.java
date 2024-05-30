package ma.zs.gestion_service_pediatrie.bean.core.gestio;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "medicament")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="medicament_seq",sequenceName="medicament_seq",allocationSize=1, initialValue = 1)
public class Medicament  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String sointProduit;
    @Column(length = 500)
    private String duree;
    @Column(length = 500)
    private String consigne;

    private Traitement traitement ;


    public Medicament(){
        super();
    }

    public Medicament(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Medicament(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="medicament_seq")
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
    public String getSointProduit(){
        return this.sointProduit;
    }
    public void setSointProduit(String sointProduit){
        this.sointProduit = sointProduit;
    }
    public String getDuree(){
        return this.duree;
    }
    public void setDuree(String duree){
        this.duree = duree;
    }
    public String getConsigne(){
        return this.consigne;
    }
    public void setConsigne(String consigne){
        this.consigne = consigne;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "traitement")
    public Traitement getTraitement(){
        return this.traitement;
    }
    public void setTraitement(Traitement traitement){
        this.traitement = traitement;
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
        Medicament medicament = (Medicament) o;
        return id != null && id.equals(medicament.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

