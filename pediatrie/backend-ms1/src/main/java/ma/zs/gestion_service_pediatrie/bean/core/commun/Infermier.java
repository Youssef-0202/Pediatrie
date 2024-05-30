package ma.zs.gestion_service_pediatrie.bean.core.commun;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;




import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;

import ma.zs.gestion_service_pediatrie.zynerator.security.bean.User;

@Entity
@Table(name = "infermier")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="infermier_seq",sequenceName="infermier_seq",allocationSize=1, initialValue = 1)
public class Infermier  extends User    {


    public Infermier(String username) {
        super(username);
    }
    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String nom;
    @Column(length = 500)
    private String prenom;
    private LocalDateTime dateNaissance ;
    @Column(length = 500)
    private String telephone;

    private String photoProfil;

    public String getPhotoProfil() {
        return photoProfil;
    }

    public void setPhotoProfil(String photoProfil) {
        this.photoProfil = photoProfil;
    }

    @Column(columnDefinition = "boolean default false")
    private boolean credentialsNonExpired = false;
    @Column(columnDefinition = "boolean default false")
    private boolean enabled = false;
    @Column(columnDefinition = "boolean default false")
    private boolean accountNonExpired = false;
    @Column(columnDefinition = "boolean default false")
    private boolean accountNonLocked = false;
    @Column(columnDefinition = "boolean default false")
    private boolean passwordChanged = false;

    private Sexe sexe ;


    public Infermier(){
        super();
    }

    public Infermier(Long id,String email){
        this.id = id;
        this.email = email ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="infermier_seq")
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
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public LocalDateTime getDateNaissance(){
        return this.dateNaissance;
    }
    public void setDateNaissance(LocalDateTime dateNaissance){
        this.dateNaissance = dateNaissance;
    }
    public String getTelephone(){
        return this.telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sexe")
    public Sexe getSexe(){
        return this.sexe;
    }
    public void setSexe(Sexe sexe){
        this.sexe = sexe;
    }
    public boolean  getCredentialsNonExpired(){
        return this.credentialsNonExpired;
    }
    public void setCredentialsNonExpired(boolean credentialsNonExpired){
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public boolean  getEnabled(){
        return this.enabled;
    }
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
    public boolean  getAccountNonExpired(){
        return this.accountNonExpired;
    }
    public void setAccountNonExpired(boolean accountNonExpired){
        this.accountNonExpired = accountNonExpired;
    }
    public boolean  getAccountNonLocked(){
        return this.accountNonLocked;
    }
    public void setAccountNonLocked(boolean accountNonLocked){
        this.accountNonLocked = accountNonLocked;
    }
    public boolean  getPasswordChanged(){
        return this.passwordChanged;
    }
    public void setPasswordChanged(boolean passwordChanged){
        this.passwordChanged = passwordChanged;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    @Transient
    public String getLabel() {
        label = email;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infermier infermier = (Infermier) o;
        return id != null && id.equals(infermier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

