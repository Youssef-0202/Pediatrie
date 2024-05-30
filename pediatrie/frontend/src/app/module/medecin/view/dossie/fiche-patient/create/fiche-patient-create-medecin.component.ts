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



import {FichePatientMedecinService} from 'src/app/shared/service/medecin/dossie/FichePatientMedecin.service';
import {FichePatientDto} from 'src/app/shared/model/dossie/FichePatient.model';
import {FichePatientCriteria} from 'src/app/shared/criteria/dossie/FichePatientCriteria.model';
import {ConsultationDto} from 'src/app/shared/model/consultatio/Consultation.model';
import {ConsultationMedecinService} from 'src/app/shared/service/medecin/consultatio/ConsultationMedecin.service';
import {AntecedentDto} from 'src/app/shared/model/dossie/Antecedent.model';
import {AntecedentMedecinService} from 'src/app/shared/service/medecin/dossie/AntecedentMedecin.service';
@Component({
  selector: 'app-fiche-patient-create-medecin',
  templateUrl: './fiche-patient-create-medecin.component.html'
})
export class FichePatientCreateMedecinComponent  implements OnInit {

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



   private _validFichePatientRef = true;
    private _validAntecedentRef = true;
    private _validConsultationRef = true;

	constructor(private service: FichePatientMedecinService , private consultationService: ConsultationMedecinService, private antecedentService: AntecedentMedecinService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.antecedentService.findAll().subscribe((data) => this.antecedents = data);
        this.consultationService.findAll().subscribe((data) => this.consultations = data);
    }


    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new FichePatientDto();
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
        this.validFichePatientRef = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateFichePatientRef();
    }

    public validateFichePatientRef(){
        if (this.stringUtilService.isEmpty(this.item.ref)) {
        this.errorMessages.push('Ref non valide');
        this.validFichePatientRef = false;
        } else {
            this.validFichePatientRef = true;
        }
    }


    public async openCreateAntecedent(antecedent: string) {
    const isPermistted = await this.roleService.isPermitted('Antecedent', 'add');
    if(isPermistted) {
         this.antecedent = new AntecedentDto();
         this.createAntecedentDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }

    get consultation(): ConsultationDto {
        return this.consultationService.item;
    }
    set consultation(value: ConsultationDto) {
        this.consultationService.item = value;
    }
    get consultations(): Array<ConsultationDto> {
        return this.consultationService.items;
    }
    set consultations(value: Array<ConsultationDto>) {
        this.consultationService.items = value;
    }
    get createConsultationDialog(): boolean {
        return this.consultationService.createDialog;
    }
    set createConsultationDialog(value: boolean) {
        this.consultationService.createDialog= value;
    }
    get antecedent(): AntecedentDto {
        return this.antecedentService.item;
    }
    set antecedent(value: AntecedentDto) {
        this.antecedentService.item = value;
    }
    get antecedents(): Array<AntecedentDto> {
        return this.antecedentService.items;
    }
    set antecedents(value: Array<AntecedentDto>) {
        this.antecedentService.items = value;
    }
    get createAntecedentDialog(): boolean {
        return this.antecedentService.createDialog;
    }
    set createAntecedentDialog(value: boolean) {
        this.antecedentService.createDialog= value;
    }



    get validFichePatientRef(): boolean {
        return this._validFichePatientRef;
    }

    set validFichePatientRef(value: boolean) {
         this._validFichePatientRef = value;
    }

    get validAntecedentRef(): boolean {
        return this._validAntecedentRef;
    }
    set validAntecedentRef(value: boolean) {
        this._validAntecedentRef = value;
    }
    get validConsultationRef(): boolean {
        return this._validConsultationRef;
    }
    set validConsultationRef(value: boolean) {
        this._validConsultationRef = value;
    }


    get items(): Array<FichePatientDto> {
        return this.service.items;
    }

    set items(value: Array<FichePatientDto>) {
        this.service.items = value;
    }

    get item(): FichePatientDto {
        return this.service.item;
    }

    set item(value: FichePatientDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): FichePatientCriteria {
        return this.service.criteria;
    }

    set criteria(value: FichePatientCriteria) {
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
