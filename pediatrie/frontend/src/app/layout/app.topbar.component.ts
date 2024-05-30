import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { SelectItem } from 'primeng/api';
import { LayoutService } from "./service/app.layout.service";
import {AppLayoutComponent} from "./app.layout.component";
import {AppComponent} from "../app.component";
import {AuthService} from "../zynerator/security/shared/service/Auth.service";
import {TranslateService} from "@ngx-translate/core";
import {UserService} from "../zynerator/security/shared/service/User.service";
import {UserDto} from "../zynerator/security/shared/model/User.model";
import {Router} from "@angular/router";

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html'
})
export class AppTopBarComponent implements OnInit{
    roleAdmin = false;
    roleMedecin=false;
    roleInfermier=false;
    editDialog = false ;
    languageOptions: SelectItem[];
    showProfil:boolean=false;
    selectedLanguage: string;




    @ViewChild('menubutton') menuButton!: ElementRef;

    @ViewChild('topbarmenubutton') topbarMenuButton!: ElementRef;

    @ViewChild('topbarmenu') menu!: ElementRef;
    public async edit(dto: UserDto) {
        this.userService.findByUsername(dto.username).subscribe(res => {
            this.item = res;
            console.log(this.item);
            this.editDialog = true;
        });
    }
    showProfilRouter() {
        if(this.roleAdmin){
            this.showProfil=!this.showProfil;
            if(this.showProfil){
                this.service.onMenuToggle();
                this.router.navigateByUrl('/app/admin/profil');
            }else {
                this.showProfil=false;
                this.service.onMenuToggle();
                this.router.navigateByUrl('/app/admin');
            }
        }else if(this.roleInfermier){
            this.showProfil=!this.showProfil;
            if(this.showProfil){
                this.service.onMenuToggle();
                this.router.navigateByUrl('/app/infermier/profil');
            }else {
                this.showProfil=false;
                this.service.onMenuToggle();
                this.router.navigateByUrl('/app/infermier');
            }
        }else if(this.roleMedecin){
            this.showProfil=!this.showProfil;
            if(this.showProfil){
                this.service.onMenuToggle();
                this.router.navigateByUrl('/app/medecin/profil');
            }else {
                this.showProfil=false;
                this.service.onMenuToggle();
                this.router.navigateByUrl('/app/medecin');
            }
        }

    }
    public editUser(){
        this.userService.edit().subscribe(data => this.authenticatedUser = data);
        this.authService.loadInfos();
        this.editDialog = false;
    }

    public hideEditDialog() {
        this.editDialog = false;
    }



    constructor(public service :LayoutService,public router:Router, public  layoutService:LayoutService ,public app: AppComponent, public appMain: AppLayoutComponent, private authService: AuthService, private translateService: TranslateService, private userService: UserService,) {
        this.languageOptions = [
            { label: 'English', value: 'en' },
            { label: 'Français', value: 'fr' },
            { label: 'العربية', value: 'ar' }
        ];
    }

    useLanguage(language: string): void {
        this.translateService.use(language);
    }
    ngOnInit(): void {
        this.authService.loadInfos();
        if ( this.authService.authenticatedUser.roleUsers[0].role.authority === 'ROLE_ADMIN') {
            this.roleAdmin = true;
        }else if (this.authService.authenticatedUser.roleUsers[0].role.authority === 'ROLE_INFERMIER'){
            this.roleInfermier = true;
        }else if(this.authService.authenticatedUser.roleUsers[0].role.authority === 'ROLE_MEDECIN'){
            this.roleMedecin = true;
        }

    }

    logout(){
        this.roleAdmin = false;
        this.roleMedecin=false;
        this.roleInfermier=false;
        this.editDialog = false;
        if(this.showProfil){
            this.showProfil= false;
            this.service.onMenuToggle();
        }
        this.authService.logout();
    }
    get item(): UserDto {
        return this.userService.item;
    }

    set item(value: UserDto) {
        this.userService.item = value;
    }
    get authenticatedUser(): UserDto{
        return this.authService.authenticatedUser;
    }
    set authenticatedUser(userDto: UserDto){
        this.authService.authenticatedUser = userDto;
    }


    background() {
        if(this.roleMedecin){
            return {"background-color":"#c5e3f6"}
        }
        else if(this.roleInfermier){
            return {"background-color":"#f5eded"}
        }else {
            return {"background-color":"#B0B1B3"}
        }
    }


    spanClick() {
        if(this.showProfil){
            this.service.onMenuToggle();
            this.showProfil=false;
        }
        if(this.roleAdmin)
            this.router.navigateByUrl('/app/admin')
        if(this.roleMedecin)
            this.router.navigateByUrl('/app/medecin')
        if(this.roleInfermier)
            this.router.navigateByUrl('/app/infermier')
    }
}
