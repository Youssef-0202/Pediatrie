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


import {TraitementAdminService} from 'src/app/shared/service/admin/gestio/TraitementAdmin.service';
import {TraitementDto} from 'src/app/shared/model/gestio/Traitement.model';
import {TraitementCriteria} from 'src/app/shared/criteria/gestio/TraitementCriteria.model';

import {OrdonnanceDto} from 'src/app/shared/model/gestio/Ordonnance.model';
import {OrdonnanceAdminService} from 'src/app/shared/service/admin/gestio/OrdonnanceAdmin.service';
import {ConsultationDto} from 'src/app/shared/model/consultatio/Consultation.model';
import {ConsultationAdminService} from 'src/app/shared/service/admin/consultatio/ConsultationAdmin.service';
import {MedicamentDto} from 'src/app/shared/model/gestio/Medicament.model';
import {MedicamentAdminService} from 'src/app/shared/service/admin/gestio/MedicamentAdmin.service';
@Component({
  selector: 'app-traitement-view-admin',
  templateUrl: './traitement-view-admin.component.html'
})
export class TraitementViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    medicaments = new MedicamentDto();
    medicamentss: Array<MedicamentDto> = [];

    constructor(private service: TraitementAdminService, private ordonnanceService: OrdonnanceAdminService, private consultationService: ConsultationAdminService, private medicamentService: MedicamentAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get consultatuin(): ConsultationDto {
        return this.consultationService.item;
    }
    set consultatuin(value: ConsultationDto) {
        this.consultationService.item = value;
    }
    get consultatuins(): Array<ConsultationDto> {
        return this.consultationService.items;
    }
    set consultatuins(value: Array<ConsultationDto>) {
        this.consultationService.items = value;
    }
    get ordonnance(): OrdonnanceDto {
        return this.ordonnanceService.item;
    }
    set ordonnance(value: OrdonnanceDto) {
        this.ordonnanceService.item = value;
    }
    get ordonnances(): Array<OrdonnanceDto> {
        return this.ordonnanceService.items;
    }
    set ordonnances(value: Array<OrdonnanceDto>) {
        this.ordonnanceService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<TraitementDto> {
        return this.service.items;
    }

    set items(value: Array<TraitementDto>) {
        this.service.items = value;
    }

    get item(): TraitementDto {
        return this.service.item;
    }

    set item(value: TraitementDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): TraitementCriteria {
        return this.service.criteria;
    }

    set criteria(value: TraitementCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
