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


import {FichePatientAdminService} from 'src/app/shared/service/admin/dossie/FichePatientAdmin.service';
import {FichePatientDto} from 'src/app/shared/model/dossie/FichePatient.model';
import {FichePatientCriteria} from 'src/app/shared/criteria/dossie/FichePatientCriteria.model';

import {ConsultationDto} from 'src/app/shared/model/consultatio/Consultation.model';
import {ConsultationAdminService} from 'src/app/shared/service/admin/consultatio/ConsultationAdmin.service';
import {AntecedentDto} from 'src/app/shared/model/dossie/Antecedent.model';
import {AntecedentAdminService} from 'src/app/shared/service/admin/dossie/AntecedentAdmin.service';
@Component({
  selector: 'app-fiche-patient-view-admin',
  templateUrl: './fiche-patient-view-admin.component.html'
})
export class FichePatientViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: FichePatientAdminService, private consultationService: ConsultationAdminService, private antecedentService: AntecedentAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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

    public hideViewDialog() {
        this.viewDialog = false;
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

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): FichePatientCriteria {
        return this.service.criteria;
    }

    set criteria(value: FichePatientCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
