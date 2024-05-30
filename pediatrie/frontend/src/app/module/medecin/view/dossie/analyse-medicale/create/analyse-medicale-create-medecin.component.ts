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



import {AnalyseMedicaleMedecinService} from 'src/app/shared/service/medecin/dossie/AnalyseMedicaleMedecin.service';
import {AnalyseMedicaleDto} from 'src/app/shared/model/dossie/AnalyseMedicale.model';
import {AnalyseMedicaleCriteria} from 'src/app/shared/criteria/dossie/AnalyseMedicaleCriteria.model';
import {ConsultationDto} from 'src/app/shared/model/consultatio/Consultation.model';
import {ConsultationMedecinService} from 'src/app/shared/service/medecin/consultatio/ConsultationMedecin.service';
import {EpreuveDto} from 'src/app/shared/model/dossie/Epreuve.model';
import {EpreuveMedecinService} from 'src/app/shared/service/medecin/dossie/EpreuveMedecin.service';
@Component({
  selector: 'app-analyse-medicale-create-medecin',
  templateUrl: './analyse-medicale-create-medecin.component.html'
})
export class AnalyseMedicaleCreateMedecinComponent  implements OnInit {

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



   private _validAnalyseMedicaleRef = true;
    private _validEpreuveRef = true;
    private _validConsultationRef = true;

	constructor(private service: AnalyseMedicaleMedecinService , private consultationService: ConsultationMedecinService, private epreuveService: EpreuveMedecinService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.epreuveService.findAll().subscribe((data) => this.epreuves = data);
        this.consultationService.findAll().subscribe((data) => this.consultations = data);
    }


    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new AnalyseMedicaleDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }

        }, error => {
            console.log(error);
        });
    }


    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }





    public  setValidation(value: boolean){
        this.validAnalyseMedicaleRef = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateAnalyseMedicaleRef();
    }

    public validateAnalyseMedicaleRef(){
        if (this.stringUtilService.isEmpty(this.item.ref)) {
        this.errorMessages.push('Ref non valide');
        this.validAnalyseMedicaleRef = false;
        } else {
            this.validAnalyseMedicaleRef = true;
        }
    }


    public async openCreateEpreuve(epreuve: string) {
    const isPermistted = await this.roleService.isPermitted('Epreuve', 'add');
    if(isPermistted) {
         this.epreuve = new EpreuveDto();
         this.createEpreuveDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }

    get epreuve(): EpreuveDto {
        return this.epreuveService.item;
    }
    set epreuve(value: EpreuveDto) {
        this.epreuveService.item = value;
    }
    get epreuves(): Array<EpreuveDto> {
        return this.epreuveService.items;
    }
    set epreuves(value: Array<EpreuveDto>) {
        this.epreuveService.items = value;
    }
    get createEpreuveDialog(): boolean {
        return this.epreuveService.createDialog;
    }
    set createEpreuveDialog(value: boolean) {
        this.epreuveService.createDialog= value;
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
    get createConsultationDialog(): boolean {
        return this.consultationService.createDialog;
    }
    set createConsultationDialog(value: boolean) {
        this.consultationService.createDialog= value;
    }



    get validAnalyseMedicaleRef(): boolean {
        return this._validAnalyseMedicaleRef;
    }

    set validAnalyseMedicaleRef(value: boolean) {
         this._validAnalyseMedicaleRef = value;
    }

    get validEpreuveRef(): boolean {
        return this._validEpreuveRef;
    }
    set validEpreuveRef(value: boolean) {
        this._validEpreuveRef = value;
    }
    get validConsultationRef(): boolean {
        return this._validConsultationRef;
    }
    set validConsultationRef(value: boolean) {
        this._validConsultationRef = value;
    }


    get items(): Array<AnalyseMedicaleDto> {
        return this.service.items;
    }

    set items(value: Array<AnalyseMedicaleDto>) {
        this.service.items = value;
    }

    get item(): AnalyseMedicaleDto {
        return this.service.item;
    }

    set item(value: AnalyseMedicaleDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): AnalyseMedicaleCriteria {
        return this.service.criteria;
    }

    set criteria(value: AnalyseMedicaleCriteria) {
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
