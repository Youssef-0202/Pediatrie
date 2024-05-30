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




import {SyntheseMedicaleMedecinService} from 'src/app/shared/service/medecin/rappor/SyntheseMedicaleMedecin.service';
import {SyntheseMedicaleDto} from 'src/app/shared/model/rappor/SyntheseMedicale.model';
import {SyntheseMedicaleCriteria} from 'src/app/shared/criteria/rappor/SyntheseMedicaleCriteria.model';


import {ConsultationDto} from 'src/app/shared/model/consultatio/Consultation.model';
import {ConsultationMedecinService} from 'src/app/shared/service/medecin/consultatio/ConsultationMedecin.service';

@Component({
  selector: 'app-synthese-medicale-edit-medecin',
  templateUrl: './synthese-medicale-edit-medecin.component.html'
})
export class SyntheseMedicaleEditMedecinComponent implements OnInit {

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



    private _validSyntheseMedicaleRef = true;

    private _validConsultationRef = true;



    constructor(private service: SyntheseMedicaleMedecinService , private consultationService: ConsultationMedecinService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.consultationService.findAll().subscribe((data) => this.consultations = data);
    }

    public prepareEdit() {
        this.item.dateSyntheseMedicale = this.service.format(this.item.dateSyntheseMedicale);
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
            this.item = new SyntheseMedicaleDto();
        } , error =>{
            console.log(error);
        });
    }



    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public setValidation(value: boolean){
        this.validSyntheseMedicaleRef = value;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateSyntheseMedicaleRef();
    }

    public validateSyntheseMedicaleRef(){
        if (this.stringUtilService.isEmpty(this.item.ref)) {
            this.errorMessages.push('Ref non valide');
            this.validSyntheseMedicaleRef = false;
        } else {
            this.validSyntheseMedicaleRef = true;
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


    get validSyntheseMedicaleRef(): boolean {
        return this._validSyntheseMedicaleRef;
    }
    set validSyntheseMedicaleRef(value: boolean) {
        this._validSyntheseMedicaleRef = value;
    }

    get validConsultationRef(): boolean {
        return this._validConsultationRef;
    }
    set validConsultationRef(value: boolean) {
        this._validConsultationRef = value;
    }

	get items(): Array<SyntheseMedicaleDto> {
        return this.service.items;
    }

    set items(value: Array<SyntheseMedicaleDto>) {
        this.service.items = value;
    }

    get item(): SyntheseMedicaleDto {
        return this.service.item;
    }

    set item(value: SyntheseMedicaleDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): SyntheseMedicaleCriteria {
        return this.service.criteria;
    }

    set criteria(value: SyntheseMedicaleCriteria) {
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
