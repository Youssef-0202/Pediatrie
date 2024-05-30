import {Component, OnInit} from '@angular/core';


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
import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';


import {PatientMedecinService} from 'src/app/shared/service/medecin/patient/PatientMedecin.service';
import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientCriteria} from 'src/app/shared/criteria/patient/PatientCriteria.model';

import {PatientContactDto} from 'src/app/shared/model/patient/PatientContact.model';
import {PatientContactMedecinService} from 'src/app/shared/service/medecin/patient/PatientContactMedecin.service';
import {RelationDto} from 'src/app/shared/model/patient/Relation.model';
import {RelationMedecinService} from 'src/app/shared/service/medecin/patient/RelationMedecin.service';
import {SexeDto} from 'src/app/shared/model/commun/Sexe.model';
import {SexeMedecinService} from 'src/app/shared/service/medecin/commun/SexeMedecin.service';
@Component({
  selector: 'app-patient-view-medecin',
  templateUrl: './patient-view-medecin.component.html'
})
export class PatientViewMedecinComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    patientContact = new PatientContactDto();
    patientContacts: Array<PatientContactDto> = [];

    constructor(private service: PatientMedecinService, private patientContactService: PatientContactMedecinService, private relationService: RelationMedecinService, private sexeService: SexeMedecinService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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

    public hideViewDialog() {
        this.viewDialog = false;
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

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): PatientCriteria {
        return this.service.criteria;
    }

    set criteria(value: PatientCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
