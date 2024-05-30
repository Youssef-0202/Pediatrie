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




import {AntecedentAdminService} from 'src/app/shared/service/admin/dossie/AntecedentAdmin.service';
import {AntecedentDto} from 'src/app/shared/model/dossie/Antecedent.model';
import {AntecedentCriteria} from 'src/app/shared/criteria/dossie/AntecedentCriteria.model';


import {GroupeSanguinDto} from 'src/app/shared/model/dossie/GroupeSanguin.model';
import {GroupeSanguinAdminService} from 'src/app/shared/service/admin/dossie/GroupeSanguinAdmin.service';

@Component({
  selector: 'app-antecedent-edit-admin',
  templateUrl: './antecedent-edit-admin.component.html'
})
export class AntecedentEditAdminComponent implements OnInit {

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



    private _validAntecedentRef = true;

    private _validGroupeSanguinRef = true;



    constructor(private service: AntecedentAdminService , private groupeSanguinService: GroupeSanguinAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.groupeSanguinService.findAll().subscribe((data) => this.groupeSanguins = data);
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
            this.item = new AntecedentDto();
        } , error =>{
            console.log(error);
        });
    }



    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public setValidation(value: boolean){
        this.validAntecedentRef = value;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateAntecedentRef();
    }

    public validateAntecedentRef(){
        if (this.stringUtilService.isEmpty(this.item.ref)) {
            this.errorMessages.push('Ref non valide');
            this.validAntecedentRef = false;
        } else {
            this.validAntecedentRef = true;
        }
    }




   public async openCreateGroupeSanguin(groupeSanguin: string) {
        const isPermistted = await this.roleService.isPermitted('GroupeSanguin', 'edit');
        if (isPermistted) {
             this.groupeSanguin = new GroupeSanguinDto();
             this.createGroupeSanguinDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
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
    get createGroupeSanguinDialog(): boolean {
        return this.groupeSanguinService.createDialog;
    }
    set createGroupeSanguinDialog(value: boolean) {
        this.groupeSanguinService.createDialog= value;
    }


    get validAntecedentRef(): boolean {
        return this._validAntecedentRef;
    }
    set validAntecedentRef(value: boolean) {
        this._validAntecedentRef = value;
    }

    get validGroupeSanguinRef(): boolean {
        return this._validGroupeSanguinRef;
    }
    set validGroupeSanguinRef(value: boolean) {
        this._validGroupeSanguinRef = value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): AntecedentCriteria {
        return this.service.criteria;
    }

    set criteria(value: AntecedentCriteria) {
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
