import {Component, OnInit} from "@angular/core";
import {MedecinAdminService} from "../../../../../shared/service/admin/commun/MedecinAdmin.service";
import {UserService} from "../../../../../zynerator/security/shared/service/User.service";
import {TranslateService} from "@ngx-translate/core";
import {AuthService} from "../../../../../zynerator/security/shared/service/Auth.service";
import {UserDto} from "../../../../../zynerator/security/shared/model/User.model";
import {MedecinDto} from "../../../../../shared/model/commun/Medecin.model";
import {element} from "protractor";
import {ProfilAdminService} from "../../../../../shared/service/admin/profil/ProfilAdmin.service";
import {InfermierDto} from "../../../../../shared/model/commun/Infermier.model";


@Component({
    selector: 'profil.component.html',
    templateUrl: 'profil.component.html',
    styleUrls:['profil.component.css']
})
export class ProfilComponent implements OnInit{
    constructor(private service:ProfilAdminService ,private authService: AuthService) {

    }
    editDialog = false ;
    editActionIsValid: boolean=true;
    newPassword: string="";
    confermedPassword: string="";
    protected validatePasswordNotExist:boolean=false;
    protected passwordNotMatch: boolean;



    ngOnInit() {

    }

    url: any = '';
    onSelectFile(event) {
        if (event.target.files && event.target.files[0]) {
            var reader = new FileReader();

            reader.readAsDataURL(event.target.files[0]); // read file as data url

            reader.onload = (event) => {
                // called once readAsDataURL is completed
                this.url = event.target.result;
                console.log(this.url);
            };
        }
    }

    public editUser(){
        this.service.edit().subscribe(religion=>{
            if(this.confermedPassword == "" && this.newPassword==""){
                this.editDialog = false;
            }
        } , error =>{
            console.log(error);
        });

        if(this.newPassword != "" && this.confermedPassword != ""){
            if (this.confermedPassword==this.newPassword){
                this.service.editPassword(this.authenticatedMedecin.username,this.confermedPassword).subscribe(
                    res=>{
                        if(res){
                            this.editDialog = false;
                            this.passwordNotMatch=false;
                            this.validatePasswordNotExist=false;
                            this.newPassword="";
                            this.confermedPassword="";
                            alert("Ok")
                        }else {
                            this.newPassword="";
                            this.confermedPassword="";
                            alert("No")
                        }
                    },error => {
                        console.log(error.message)
                    }
                );
            }
            else{
                this.editDialog = true;
                this.passwordNotMatch=true;
                this.newPassword="";
                this.confermedPassword="";
                console.log("password not match")
            }
        }else if(this.confermedPassword != null) {
            this.validatePasswordNotExist=true;
            console.log("password not confermed")
        }

    }

    get authenticatedMedecin(): MedecinDto {
        return this.authService.authenticatedMedecin;
    }

    set authenticatedMedecin(value: MedecinDto) {
        this.authService.authenticatedMedecin = value;
    }





    showEditPassword() {
        this.editDialog=true;
    }
}
