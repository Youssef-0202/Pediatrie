package ma.zs.gestion_service_pediatrie.bean.core.dossie;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "groupe_sanguin")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="groupe_sanguin_seq",sequenceName="groupe_sanguin_seq",allocationSize=1, initialValue = 1)
public class GroupeSanguin  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String libelle;



    public GroupeSanguin(){
        super();
    }

    public GroupeSanguin(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public GroupeSanguin(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="groupe_sanguin_seq")
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
        GroupeSanguin groupeSanguin = (GroupeSanguin) o;
        return id != null && id.equals(groupeSanguin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

