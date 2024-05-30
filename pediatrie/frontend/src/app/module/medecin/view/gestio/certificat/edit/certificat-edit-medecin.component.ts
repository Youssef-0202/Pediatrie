import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {CertificatMedecinService} from 'src/app/shared/service/medecin/gestio/CertificatMedecin.service';
import {CertificatDto} from 'src/app/shared/model/gestio/Certificat.model';
import {CertificatCriteria} from 'src/app/shared/criteria/gestio/CertificatCriteria.model';


import {ConsultationDto} from 'src/app/shared/model/consultatio/Consultation.model';
import {ConsultationMedecinService} from 'src/app/shared/service/medecin/consultatio/ConsultationMedecin.service';

@Component({
  selector: 'app-certificat-edit-medecin',
  templateUrl: './certificat-edit-medecin.component.html'
})
export class CertificatEditMedecinComponent implements OnInit {

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



    private _validCertificatRef = true;

    private _validConsultatuinRef = true;



    constructor(private service: CertificatMedecinService , private consultationService: ConsultationMedecinService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.consultationService.findAll().subscribe((data) => this.consultatuins = data);
    }

    public prepareEdit() {
        this.item.dateDebut = this.service.format(this.item.dateDebut);
        this.item.dateFin = this.service.format(this.item.dateFin);
    }



 public edit(): void {
        this.submitted = true;
        this.prepareEdit();
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.editWithShowOption(false);
        } else {
            this.messageService.add({
                severity: 'error',
                summary: 'Erreurs',
                detail: 'Merci de corrigé les erreurs sur le formulaire'
            });
        }
    }

    public editWithShowOption(showList: boolean) {
        this.service.edit().subscribe(religion=>{
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = religion;
            this.editDialog = false;
            this.submitted = false;
            this.item = new CertificatDto();
        } , error =>{
            console.log(error);
        });
    }



    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public setValidation(value: boolean){
        this.validCertificatRef = value;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateCertificatRef();
    }

    public validateCertificatRef(){
        if (this.stringUtilService.isEmpty(this.item.ref)) {
            this.errorMessages.push('Ref non valide');
            this.validCertificatRef = false;
        } else {
            this.validCertificatRef = true;
        }
    }





    get consultatuin(): ConsultationDto {
        return this.consultationService.item;
    }
    set consultatuin(value: ConsultationDto) {
        this.consultationService.item = value;
    }
    get consultatuins(): Array<ConsultationDto> {
        return this.consultationService.items;
    }
    set consultatuins(value: Array<ConsultationDto>) {
        this.consultationService.items = value;
    }
    get createConsultatuinDialog(): boolean {
        return this.consultationService.createDialog;
    }
    set createConsultatuinDialog(value: boolean) {
        this.consultationService.createDialog= value;
    }


    get validCertificatRef(): boolean {
        return this._validCertificatRef;
    }
    set validCertificatRef(value: boolean) {
        this._validCertificatRef = value;
    }

    get validConsultatuinRef(): boolean {
        return this._validConsultatuinRef;
    }
    set validConsultatuinRef(value: boolean) {
        this._validConsultatuinRef = value;
    }

	get items(): Array<CertificatDto> {
        return this.service.items;
    }

    set items(value: Array<CertificatDto>) {
        this.service.items = value;
    }

    get item(): CertificatDto {
        return this.service.item;
    }

    set item(value: CertificatDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): CertificatCriteria {
        return this.service.criteria;
    }

    set criteria(value: CertificatCriteria) {
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
