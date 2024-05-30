package ma.zs.gestion_service_pediatrie.bean.core.dossie;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "epreuve")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="epreuve_seq",sequenceName="epreuve_seq",allocationSize=1, initialValue = 1)
public class Epreuve  extends BaseEntity     {

    private Long id;
    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String libelle;



    public Epreuve(){
        super();
    }

    public Epreuve(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Epreuve(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="epreuve_seq")
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
        Epreuve epreuve = (Epreuve) o;
        return id != null && id.equals(epreuve.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

