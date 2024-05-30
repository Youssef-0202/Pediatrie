package ma.zs.gestion_service_pediatrie.bean.core.commun;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sexe")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="sexe_seq",sequenceName="sexe_seq",allocationSize=1, initialValue = 1)
public class Sexe  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String libelle;



    public Sexe(){
        super();
    }

    public Sexe(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Sexe(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="sexe_seq")
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
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
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
        Sexe sexe = (Sexe) o;
        return id != null && id.equals(sexe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

