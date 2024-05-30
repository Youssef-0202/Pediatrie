package ma.zs.gestion_service_pediatrie.bean.core.dossie;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type_image")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="type_image_seq",sequenceName="type_image_seq",allocationSize=1, initialValue = 1)
public class TypeImage  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String libelle;



    public TypeImage(){
        super();
    }

    public TypeImage(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public TypeImage(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="type_image_seq")
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
        TypeImage typeImage = (TypeImage) o;
        return id != null && id.equals(typeImage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

