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


import {AntecedentInfermierService} from 'src/app/shared/service/infermier/dossie/AntecedentInfermier.service';
import {AntecedentDto} from 'src/app/shared/model/dossie/Antecedent.model';
import {AntecedentCriteria} from 'src/app/shared/criteria/dossie/AntecedentCriteria.model';

import {GroupeSanguinDto} from 'src/app/shared/model/dossie/GroupeSanguin.model';
import {GroupeSanguinInfermierService} from 'src/app/shared/service/infermier/dossie/GroupeSanguinInfermier.service';
@Component({
  selector: 'app-antecedent-view-infermier',
  templateUrl: './antecedent-view-infermier.component.html'
})
export class AntecedentViewInfermierComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: AntecedentInfermierService, private groupeSanguinService: GroupeSanguinInfermierService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get groupeSanguin(): GroupeSanguinDto {
        return this.groupeSanguinService.item;
    }
    set groupeSanguin(value: GroupeSanguinDto) {
        this.groupeSanguinService.item = value;
    }
    get groupeSanguins(): Array<GroupeSanguinDto> {
        return this.groupeSanguinService.items;
    }
    set groupeSanguins(value: Array<GroupeSanguinDto>) {
        this.groupeSanguinService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<AntecedentDto> {
        return this.service.items;
    }

    set items(value: Array<AntecedentDto>) {
        this.service.items = value;
    }

    get item(): AntecedentDto {
        return this.service.item;
    }

    set item(value: AntecedentDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): AntecedentCriteria {
        return this.service.criteria;
    }

    set criteria(value: AntecedentCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
