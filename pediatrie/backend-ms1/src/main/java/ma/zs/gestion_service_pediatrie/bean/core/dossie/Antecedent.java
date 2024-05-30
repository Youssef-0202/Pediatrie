package ma.zs.gestion_service_pediatrie.bean.core.dossie;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "antecedent")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="antecedent_seq",sequenceName="antecedent_seq",allocationSize=1, initialValue = 1)
public class Antecedent  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String allergie;
    @Column(length = 500)
    private String etat_psy;
    @Column(length = 500)
    private String respiratoire;
    @Column(length = 500)
    private String alimentation;
    @Column(length = 500)
    private String mouvement;
    @Column(length = 500)
    private String sommeil;

    private GroupeSanguin groupeSanguin ;


    public Antecedent(){
        super();
    }

    public Antecedent(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Antecedent(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="antecedent_seq")
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
    public String getAllergie(){
        return this.allergie;
    }
    public void setAllergie(String allergie){
        this.allergie = allergie;
    }
    public String getEtat_psy(){
        return this.etat_psy;
    }
    public void setEtat_psy(String etat_psy){
        this.etat_psy = etat_psy;
    }
    public String getRespiratoire(){
        return this.respiratoire;
    }
    public void setRespiratoire(String respiratoire){
        this.respiratoire = respiratoire;
    }
    public String getAlimentation(){
        return this.alimentation;
    }
    public void setAlimentation(String alimentation){
        this.alimentation = alimentation;
    }
    public String getMouvement(){
        return this.mouvement;
    }
    public void setMouvement(String mouvement){
        this.mouvement = mouvement;
    }
    public String getSommeil(){
        return this.sommeil;
    }
    public void setSommeil(String sommeil){
        this.sommeil = sommeil;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupe_sanguin")
    public GroupeSanguin getGroupeSanguin(){
        return this.groupeSanguin;
    }
    public void setGroupeSanguin(GroupeSanguin groupeSanguin){
        this.groupeSanguin = groupeSanguin;
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
        Antecedent antecedent = (Antecedent) o;
        return id != null && id.equals(antecedent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

