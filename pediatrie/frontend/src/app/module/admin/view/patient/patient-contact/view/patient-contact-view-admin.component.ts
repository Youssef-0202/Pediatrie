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


import {PatientContactAdminService} from 'src/app/shared/service/admin/patient/PatientContactAdmin.service';
import {PatientContactDto} from 'src/app/shared/model/patient/PatientContact.model';
import {PatientContactCriteria} from 'src/app/shared/criteria/patient/PatientContactCriteria.model';

import {RelationDto} from 'src/app/shared/model/patient/Relation.model';
import {RelationAdminService} from 'src/app/shared/service/admin/patient/RelationAdmin.service';
import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientAdminService} from 'src/app/shared/service/admin/patient/PatientAdmin.service';
@Component({
  selector: 'app-patient-contact-view-admin',
  templateUrl: './patient-contact-view-admin.component.html'
})
export class PatientContactViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: PatientContactAdminService, private relationService: RelationAdminService, private patientService: PatientAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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

    public hideViewDialog() {
        this.viewDialog = false;
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

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): PatientContactCriteria {
        return this.service.criteria;
    }

    set criteria(value: PatientContactCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
