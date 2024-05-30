import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';



import {InfermierAdminService} from 'src/app/shared/service/admin/commun/InfermierAdmin.service';
import {InfermierDto} from 'src/app/shared/model/commun/Infermier.model';
import {InfermierCriteria} from 'src/app/shared/criteria/commun/InfermierCriteria.model';
import {SexeDto} from 'src/app/shared/model/commun/Sexe.model';
import {SexeAdminService} from 'src/app/shared/service/admin/commun/SexeAdmin.service';
import {DomSanitizer} from "@angular/platform-browser";
@Component({
  selector: 'app-infermier-create-admin',
  templateUrl: './infermier-create-admin.component.html'
})
export class InfermierCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    private _file: any;
    private _files: any;



   private _validInfermierRef = true;
   private _validInfermierEmail = true;
    private _validSexeRef = true;

	constructor(private sanitizer:DomSanitizer,private service: InfermierAdminService , private sexeService: SexeAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.sexeService.findAll().subscribe((data) => this.sexes = data);
    }


    public save(): void {
        this.submitted = true;
        this.item.createdAt=new Date();
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {

        this.item.accountNonExpired=true;
        this.item.accountNonLocked=true;
        this.item.credentialsNonExpired=true;
        this.item.passwordChanged=false;
        this.item.enabled=true;
        this.service.save(this.item).subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new InfermierDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }

        }, error => {
            console.log(error);
        });
    }




    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }





    public  setValidation(value: boolean){
        this.validInfermierRef = value;
        this.validInfermierEmail = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateInfermierRef();
        this.validateInfermierEmail();
    }

    public validateInfermierRef(){
        if (this.stringUtilService.isEmpty(this.item.ref)) {
        this.errorMessages.push('Ref non valide');
        this.validInfermierRef = false;
        } else {
            this.validInfermierRef = true;
        }
    }
    public validateInfermierEmail(){
        if (this.stringUtilService.isEmpty(this.item.email)) {
        this.errorMessages.push('Email non valide');
        this.validInfermierEmail = false;
        } else {
            this.validInfermierEmail = true;
        }
    }


    public async openCreateSexe(sexe: string) {
    const isPermistted = await this.roleService.isPermitted('Sexe', 'add');
    if(isPermistted) {
         this.sexe = new SexeDto();
         this.createSexeDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }

    get sexe(): SexeDto {
        return this.sexeService.item;
    }
    set sexe(value: SexeDto) {
        this.sexeService.item = value;
    }
    get sexes(): Array<SexeDto> {
        return this.sexeService.items;
    }
    set sexes(value: Array<SexeDto>) {
        this.sexeService.items = value;
    }
    get createSexeDialog(): boolean {
        return this.sexeService.createDialog;
    }
    set createSexeDialog(value: boolean) {
        this.sexeService.createDialog= value;
    }



    get validInfermierRef(): boolean {
        return this._validInfermierRef;
    }

    set validInfermierRef(value: boolean) {
         this._validInfermierRef = value;
    }
    get validInfermierEmail(): boolean {
        return this._validInfermierEmail;
    }

    set validInfermierEmail(value: boolean) {
         this._validInfermierEmail = value;
    }

    get validSexeRef(): boolean {
        return this._validSexeRef;
    }
    set validSexeRef(value: boolean) {
        this._validSexeRef = value;
    }


    get items(): Array<InfermierDto> {
        return this.service.items;
    }

    set items(value: Array<InfermierDto>) {
        this.service.items = value;
    }

    get item(): InfermierDto {
        return this.service.item;
    }

    set item(value: InfermierDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): InfermierCriteria {
        return this.service.criteria;
    }

    set criteria(value: InfermierCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }



}
