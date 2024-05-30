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


import {DiagnosticInfermierService} from 'src/app/shared/service/infermier/rappor/DiagnosticInfermier.service';
import {DiagnosticDto} from 'src/app/shared/model/rappor/Diagnostic.model';
import {DiagnosticCriteria} from 'src/app/shared/criteria/rappor/DiagnosticCriteria.model';

import {ConsultationDto} from 'src/app/shared/model/consultatio/Consultation.model';
import {ConsultationInfermierService} from 'src/app/shared/service/infermier/consultatio/ConsultationInfermier.service';
@Component({
  selector: 'app-diagnostic-view-infermier',
  templateUrl: './diagnostic-view-infermier.component.html'
})
export class DiagnosticViewInfermierComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: DiagnosticInfermierService, private consultationService: ConsultationInfermierService){
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

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<DiagnosticDto> {
        return this.service.items;
    }

    set items(value: Array<DiagnosticDto>) {
        this.service.items = value;
    }

    get item(): DiagnosticDto {
        return this.service.item;
    }

    set item(value: DiagnosticDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): DiagnosticCriteria {
        return this.service.criteria;
    }

    set criteria(value: DiagnosticCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
