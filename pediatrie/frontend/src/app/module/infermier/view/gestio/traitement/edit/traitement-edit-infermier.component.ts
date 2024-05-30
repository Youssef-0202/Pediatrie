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




import {TraitementInfermierService} from 'src/app/shared/service/infermier/gestio/TraitementInfermier.service';
import {TraitementDto} from 'src/app/shared/model/gestio/Traitement.model';
import {TraitementCriteria} from 'src/app/shared/criteria/gestio/TraitementCriteria.model';


import {OrdonnanceDto} from 'src/app/shared/model/gestio/Ordonnance.model';
import {OrdonnanceInfermierService} from 'src/app/shared/service/infermier/gestio/OrdonnanceInfermier.service';
import {ConsultationDto} from 'src/app/shared/model/consultatio/Consultation.model';
import {ConsultationInfermierService} from 'src/app/shared/service/infermier/consultatio/ConsultationInfermier.service';
import {MedicamentDto} from 'src/app/shared/model/gestio/Medicament.model';
import {MedicamentInfermierService} from 'src/app/shared/service/infermier/gestio/MedicamentInfermier.service';

@Component({
  selector: 'app-traitement-edit-infermier',
  templateUrl: './traitement-edit-infermier.component.html'
})
export class TraitementEditInfermierComponent implements OnInit {

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


    private _medicamentsElement = new MedicamentDto();

    private _validTraitementRef = true;

    private _validOrdonnanceRef = true;
    private _validConsultatuinRef = true;
    private _validMedicamentsRef = true;



    constructor(private service: TraitementInfermierService , private ordonnanceService: OrdonnanceInfermierService, private consultationService: ConsultationInfermierService, private medicamentService: MedicamentInfermierService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {

        this.ordonnanceService.findAll().subscribe((data) => this.ordonnances = data);
        this.consultationService.findAll().subscribe((data) => this.consultatuins = data);
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
            this.item = new TraitementDto();
        } , error =>{
            console.log(error);
        });
    }



    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public validateMedicaments(){
        this.errorMessages = new Array();
        this.validateMedicamentsRef();
    }

    public setValidation(value: boolean){
        this.validTraitementRef = value;
        this.validMedicamentsRef = value;
    }

   public addMedicaments() {
        if( this.item.medicaments == null )
            this.item.medicaments = new Array<MedicamentDto>();
       this.validateMedicaments();
       if (this.errorMessages.length === 0) {
            if(this.medicamentsElement.id == null){
                this.item.medicaments.push(this.medicamentsElement);
            }else{
                const index = this.item.medicaments.findIndex(e => e.id == this.medicamentsElement.id);
                this.item.medicaments[index] = this.medicamentsElement;
            }
          this.medicamentsElement = new MedicamentDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs', detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
        }
   }

    public deleteMedicaments(p: MedicamentDto) {
        this.item.medicaments.forEach((element, index) => {
            if (element === p) { this.item.medicaments.splice(index, 1); }
        });
    }

    public editMedicaments(p: MedicamentDto) {
        this.medicamentsElement = {... p};
        this.activeTab = 0;
    }


    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateTraitementRef();
    }

    public validateTraitementRef(){
        if (this.stringUtilService.isEmpty(this.item.ref)) {
            this.errorMessages.push('Ref non valide');
            this.validTraitementRef = false;
        } else {
            this.validTraitementRef = true;
        }
    }



    private validateMedicamentsRef(){
        if (this.medicamentsElement.ref == null) {
        this.errorMessages.push('Ref de la medicament est  invalide');
            this.validMedicamentsRef = false;
        } else {
            this.validMedicamentsRef = true;
        }
    }

   public async openCreateOrdonnance(ordonnance: string) {
        const isPermistted = await this.roleService.isPermitted('Ordonnance', 'edit');
        if (isPermistted) {
             this.ordonnance = new OrdonnanceDto();
             this.createOrdonnanceDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
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
    get createConsultatuinDialog(): boolean {
        return this.consultationService.createDialog;
    }
    set createConsultatuinDialog(value: boolean) {
        this.consultationService.createDialog= value;
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
    get createOrdonnanceDialog(): boolean {
        return this.ordonnanceService.createDialog;
    }
    set createOrdonnanceDialog(value: boolean) {
        this.ordonnanceService.createDialog= value;
    }

    get medicamentsElement(): MedicamentDto {
        if( this._medicamentsElement == null )
            this._medicamentsElement = new MedicamentDto();
         return this._medicamentsElement;
    }

    set medicamentsElement(value: MedicamentDto) {
        this._medicamentsElement = value;
    }

    get validTraitementRef(): boolean {
        return this._validTraitementRef;
    }
    set validTraitementRef(value: boolean) {
        this._validTraitementRef = value;
    }

    get validOrdonnanceRef(): boolean {
        return this._validOrdonnanceRef;
    }
    set validOrdonnanceRef(value: boolean) {
        this._validOrdonnanceRef = value;
    }
    get validConsultatuinRef(): boolean {
        return this._validConsultatuinRef;
    }
    set validConsultatuinRef(value: boolean) {
        this._validConsultatuinRef = value;
    }
    get validMedicamentsRef(): boolean {
        return this._validMedicamentsRef;
    }
    set validMedicamentsRef(value: boolean) {
        this._validMedicamentsRef = value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): TraitementCriteria {
        return this.service.criteria;
    }

    set criteria(value: TraitementCriteria) {
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
