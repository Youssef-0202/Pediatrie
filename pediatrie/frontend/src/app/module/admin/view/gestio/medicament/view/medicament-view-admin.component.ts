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


import {MedicamentAdminService} from 'src/app/shared/service/admin/gestio/MedicamentAdmin.service';
import {MedicamentDto} from 'src/app/shared/model/gestio/Medicament.model';
import {MedicamentCriteria} from 'src/app/shared/criteria/gestio/MedicamentCriteria.model';

import {TraitementDto} from 'src/app/shared/model/gestio/Traitement.model';
import {TraitementAdminService} from 'src/app/shared/service/admin/gestio/TraitementAdmin.service';
@Component({
  selector: 'app-medicament-view-admin',
  templateUrl: './medicament-view-admin.component.html'
})
export class MedicamentViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: MedicamentAdminService, private traitementService: TraitementAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get traitement(): TraitementDto {
        return this.traitementService.item;
    }
    set traitement(value: TraitementDto) {
        this.traitementService.item = value;
    }
    get traitements(): Array<TraitementDto> {
        return this.traitementService.items;
    }
    set traitements(value: Array<TraitementDto>) {
        this.traitementService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<MedicamentDto> {
        return this.service.items;
    }

    set items(value: Array<MedicamentDto>) {
        this.service.items = value;
    }

    get item(): MedicamentDto {
        return this.service.item;
    }

    set item(value: MedicamentDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): MedicamentCriteria {
        return this.service.criteria;
    }

    set criteria(value: MedicamentCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
