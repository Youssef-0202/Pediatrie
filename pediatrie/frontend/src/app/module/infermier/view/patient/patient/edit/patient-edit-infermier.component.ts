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




import {PatientInfermierService} from 'src/app/shared/service/infermier/patient/PatientInfermier.service';
import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientCriteria} from 'src/app/shared/criteria/patient/PatientCriteria.model';


import {PatientContactDto} from 'src/app/shared/model/patient/PatientContact.model';
import {PatientContactInfermierService} from 'src/app/shared/service/infermier/patient/PatientContactInfermier.service';
import {RelationDto} from 'src/app/shared/model/patient/Relation.model';
import {RelationInfermierService} from 'src/app/shared/service/infermier/patient/RelationInfermier.service';
import {SexeDto} from 'src/app/shared/model/commun/Sexe.model';
import {SexeInfermierService} from 'src/app/shared/service/infermier/commun/SexeInfermier.service';

@Component({
  selector: 'app-patient-edit-infermier',
  templateUrl: './patient-edit-infermier.component.html'
})
export class PatientEditInfermierComponent implements OnInit {

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


    private _patientContactElement = new PatientContactDto();

    private _validPatientNumDossier = true;

    private _validSexeRef = true;
    private _validPatientContactCin = true;
    private _validPatientContactEmail = true;



    constructor(private service: PatientInfermierService , private patientContactService: PatientContactInfermierService, private relationService: RelationInfermierService, private sexeService: SexeInfermierService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.patientContactElement.relation = new RelationDto();
        this.relationService.findAll().subscribe((data) => this.relations = data);

        this.sexeService.findAll().subscribe((data) => this.sexes = data);
    }

    public prepareEdit() {
        this.item.dateNaissance = this.service.format(this.item.dateNaissance);
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
            this.item = new PatientDto();
        } , error =>{
            console.log(error);
        });
    }



    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public validatePatientContact(){
        this.errorMessages = new Array();
        this.validatePatientContactCin();
        this.validatePatientContactEmail();
    }

    public setValidation(value: boolean){
        this.validPatientNumDossier = value;
        this.validPatientContactCin = value;
        this.validPatientContactEmail = value;
    }

   public addPatientContact() {
        if( this.item.patientContact == null )
            this.item.patientContact = new Array<PatientContactDto>();
       this.validatePatientContact();
       if (this.errorMessages.length === 0) {
            if(this.patientContactElement.id == null){
                this.item.patientContact.push(this.patientContactElement);
            }else{
                const index = this.item.patientContact.findIndex(e => e.id == this.patientContactElement.id);
                this.item.patientContact[index] = this.patientContactElement;
            }
          this.patientContactElement = new PatientContactDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs', detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
        }
   }

    public deletePatientContact(p: PatientContactDto) {
        this.item.patientContact.forEach((element, index) => {
            if (element === p) { this.item.patientContact.splice(index, 1); }
        });
    }

    public editPatientContact(p: PatientContactDto) {
        this.patientContactElement = {... p};
        this.activeTab = 0;
    }


    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validatePatientNumDossier();
    }

    public validatePatientNumDossier(){
        if (this.stringUtilService.isEmpty(this.item.numDossier)) {
            this.errorMessages.push('Num dossier non valide');
            this.validPatientNumDossier = false;
        } else {
            this.validPatientNumDossier = true;
        }
    }



    private validatePatientContactCin(){
        if (this.patientContactElement.cin == null) {
        this.errorMessages.push('Cin de la patientContact est  invalide');
            this.validPatientContactCin = false;
        } else {
            this.validPatientContactCin = true;
        }
    }
    private validatePatientContactEmail(){
        if (this.patientContactElement.email == null) {
        this.errorMessages.push('Email de la patientContact est  invalide');
            this.validPatientContactEmail = false;
        } else {
            this.validPatientContactEmail = true;
        }
    }

   public async openCreateRelation(relation: string) {
        const isPermistted = await this.roleService.isPermitted('Relation', 'edit');
        if (isPermistted) {
             this.relation = new RelationDto();
             this.createRelationDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }

    get relation(): RelationDto {
        return this.relationService.item;
    }
    set relation(value: RelationDto) {
        this.relationService.item = value;
    }
    get relations(): Array<RelationDto> {
        return this.relationService.items;
    }
    set relations(value: Array<RelationDto>) {
        this.relationService.items = value;
    }
    get createRelationDialog(): boolean {
        return this.relationService.createDialog;
    }
    set createRelationDialog(value: boolean) {
        this.relationService.createDialog= value;
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

    get patientContactElement(): PatientContactDto {
        if( this._patientContactElement == null )
            this._patientContactElement = new PatientContactDto();
         return this._patientContactElement;
    }

    set patientContactElement(value: PatientContactDto) {
        this._patientContactElement = value;
    }

    get validPatientNumDossier(): boolean {
        return this._validPatientNumDossier;
    }
    set validPatientNumDossier(value: boolean) {
        this._validPatientNumDossier = value;
    }

    get validSexeRef(): boolean {
        return this._validSexeRef;
    }
    set validSexeRef(value: boolean) {
        this._validSexeRef = value;
    }
    get validPatientContactCin(): boolean {
        return this._validPatientContactCin;
    }
    set validPatientContactCin(value: boolean) {
        this._validPatientContactCin = value;
    }
    get validPatientContactEmail(): boolean {
        return this._validPatientContactEmail;
    }
    set validPatientContactEmail(value: boolean) {
        this._validPatientContactEmail = value;
    }

	get items(): Array<PatientDto> {
        return this.service.items;
    }

    set items(value: Array<PatientDto>) {
        this.service.items = value;
    }

    get item(): PatientDto {
        return this.service.item;
    }

    set item(value: PatientDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): PatientCriteria {
        return this.service.criteria;
    }

    set criteria(value: PatientCriteria) {
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
