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




import {PatientContactAdminService} from 'src/app/shared/service/admin/patient/PatientContactAdmin.service';
import {PatientContactDto} from 'src/app/shared/model/patient/PatientContact.model';
import {PatientContactCriteria} from 'src/app/shared/criteria/patient/PatientContactCriteria.model';


import {RelationDto} from 'src/app/shared/model/patient/Relation.model';
import {RelationAdminService} from 'src/app/shared/service/admin/patient/RelationAdmin.service';
import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientAdminService} from 'src/app/shared/service/admin/patient/PatientAdmin.service';

@Component({
  selector: 'app-patient-contact-edit-admin',
  templateUrl: './patient-contact-edit-admin.component.html'
})
export class PatientContactEditAdminComponent implements OnInit {

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



    private _validPatientContactCin = true;
    private _validPatientContactEmail = true;

    private _validRelationRef = true;
    private _validPatientNumDossier = true;



    constructor(private service: PatientContactAdminService , private relationService: RelationAdminService, private patientService: PatientAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.relationService.findAll().subscribe((data) => this.relations = data);
        this.patientService.findAll().subscribe((data) => this.patients = data);
    }

    public prepareEdit() {
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
            this.item = new PatientContactDto();
        } , error =>{
            console.log(error);
        });
    }



    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public setValidation(value: boolean){
        this.validPatientContactCin = value;
        this.validPatientContactEmail = value;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validatePatientContactCin();
        this.validatePatientContactEmail();
    }

    public validatePatientContactCin(){
        if (this.stringUtilService.isEmpty(this.item.cin)) {
            this.errorMessages.push('Cin non valide');
            this.validPatientContactCin = false;
        } else {
            this.validPatientContactCin = true;
        }
    }

    public validatePatientContactEmail(){
        if (this.stringUtilService.isEmpty(this.item.email)) {
            this.errorMessages.push('Email non valide');
            this.validPatientContactEmail = false;
        } else {
            this.validPatientContactEmail = true;
        }
    }




   public async openCreatePatient(patient: string) {
        const isPermistted = await this.roleService.isPermitted('Patient', 'edit');
        if (isPermistted) {
             this.patient = new PatientDto();
             this.createPatientDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
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

    get patient(): PatientDto {
        return this.patientService.item;
    }
    set patient(value: PatientDto) {
        this.patientService.item = value;
    }
    get patients(): Array<PatientDto> {
        return this.patientService.items;
    }
    set patients(value: Array<PatientDto>) {
        this.patientService.items = value;
    }
    get createPatientDialog(): boolean {
        return this.patientService.createDialog;
    }
    set createPatientDialog(value: boolean) {
        this.patientService.createDialog= value;
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

    get validRelationRef(): boolean {
        return this._validRelationRef;
    }
    set validRelationRef(value: boolean) {
        this._validRelationRef = value;
    }
    get validPatientNumDossier(): boolean {
        return this._validPatientNumDossier;
    }
    set validPatientNumDossier(value: boolean) {
        this._validPatientNumDossier = value;
    }

	get items(): Array<PatientContactDto> {
        return this.service.items;
    }

    set items(value: Array<PatientContactDto>) {
        this.service.items = value;
    }

    get item(): PatientContactDto {
        return this.service.item;
    }

    set item(value: PatientContactDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): PatientContactCriteria {
        return this.service.criteria;
    }

    set criteria(value: PatientContactCriteria) {
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
