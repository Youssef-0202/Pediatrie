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


import {MedecinInfermierService} from 'src/app/shared/service/infermier/commun/MedecinInfermier.service';
import {MedecinDto} from 'src/app/shared/model/commun/Medecin.model';
import {MedecinCriteria} from 'src/app/shared/criteria/commun/MedecinCriteria.model';

import {SexeDto} from 'src/app/shared/model/commun/Sexe.model';
import {SexeInfermierService} from 'src/app/shared/service/infermier/commun/SexeInfermier.service';
@Component({
  selector: 'app-medecin-view-infermier',
  templateUrl: './medecin-view-infermier.component.html'
})
export class MedecinViewInfermierComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: MedecinInfermierService, private sexeService: SexeInfermierService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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

    get items(): Array<MedecinDto> {
        return this.service.items;
    }

    set items(value: Array<MedecinDto>) {
        this.service.items = value;
    }

    get item(): MedecinDto {
        return this.service.item;
    }

    set item(value: MedecinDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): MedecinCriteria {
        return this.service.criteria;
    }

    set criteria(value: MedecinCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
