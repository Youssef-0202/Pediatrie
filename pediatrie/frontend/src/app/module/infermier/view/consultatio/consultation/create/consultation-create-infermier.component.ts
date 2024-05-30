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



import {ConsultationInfermierService} from 'src/app/shared/service/infermier/consultatio/ConsultationInfermier.service';
import {ConsultationDto} from 'src/app/shared/model/consultatio/Consultation.model';
import {ConsultationCriteria} from 'src/app/shared/criteria/consultatio/ConsultationCriteria.model';
import {FichePatientDto} from 'src/app/shared/model/dossie/FichePatient.model';
import {FichePatientInfermierService} from 'src/app/shared/service/infermier/dossie/FichePatientInfermier.service';
import {RadiologieDto} from 'src/app/shared/model/dossie/Radiologie.model';
import {RadiologieInfermierService} from 'src/app/shared/service/infermier/dossie/RadiologieInfermier.service';
import {InfermierDto} from 'src/app/shared/model/commun/Infermier.model';
import {InfermierInfermierService} from 'src/app/shared/service/infermier/commun/InfermierInfermier.service';
import {MedecinDto} from 'src/app/shared/model/commun/Medecin.model';
import {MedecinInfermierService} from 'src/app/shared/service/infermier/commun/MedecinInfermier.service';
import {EpreuveDto} from 'src/app/shared/model/dossie/Epreuve.model';
import {EpreuveInfermierService} from 'src/app/shared/service/infermier/dossie/EpreuveInfermier.service';
import {SyntheseMedicaleDto} from 'src/app/shared/model/rappor/SyntheseMedicale.model';
import {SyntheseMedicaleInfermierService} from 'src/app/shared/service/infermier/rappor/SyntheseMedicaleInfermier.service';
import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientInfermierService} from 'src/app/shared/service/infermier/patient/PatientInfermier.service';
import {DiagnosticDto} from 'src/app/shared/model/rappor/Diagnostic.model';
import {DiagnosticInfermierService} from 'src/app/shared/service/infermier/rappor/DiagnosticInfermier.service';
import {AnalyseMedicaleDto} from 'src/app/shared/model/dossie/AnalyseMedicale.model';
import {AnalyseMedicaleInfermierService} from 'src/app/shared/service/infermier/dossie/AnalyseMedicaleInfermier.service';
import {TypeImageDto} from 'src/app/shared/model/dossie/TypeImage.model';
import {TypeImageInfermierService} from 'src/app/shared/service/infermier/dossie/TypeImageInfermier.service';
import {AntecedentDto} from 'src/app/shared/model/dossie/Antecedent.model';
import {AntecedentInfermierService} from 'src/app/shared/service/infermier/dossie/AntecedentInfermier.service';
@Component({
  selector: 'app-consultation-create-infermier',
  templateUrl: './consultation-create-infermier.component.html'
})
export class ConsultationCreateInfermierComponent  implements OnInit {

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

    private _analyseMedicaleElement = new AnalyseMedicaleDto();
    private _fichePatientElement = new FichePatientDto();
    private _radiologieElement = new RadiologieDto();
    private _diagnosticElement = new DiagnosticDto();
    private _syntheseMedicaleElement = new SyntheseMedicaleDto();


   private _validConsultationRef = true;
    private _validMedecinRef = true;
    private _validMedecinEmail = true;
    private _validInfermierRef = true;
    private _validInfermierEmail = true;
    private _validPatientNumDossier = true;
    private _validAnalyseMedicaleRef = true;
    private _validFichePatientRef = true;
    private _validRadiologieRef = true;
    private _validDiagnosticRef = true;
    private _validSyntheseMedicaleRef = true;

	constructor(private service: ConsultationInfermierService , private fichePatientService: FichePatientInfermierService, private radiologieService: RadiologieInfermierService, private infermierService: InfermierInfermierService, private medecinService: MedecinInfermierService, private epreuveService: EpreuveInfermierService, private syntheseMedicaleService: SyntheseMedicaleInfermierService, private patientService: PatientInfermierService, private diagnosticService: DiagnosticInfermierService, private analyseMedicaleService: AnalyseMedicaleInfermierService, private typeImageService: TypeImageInfermierService, private antecedentService: AntecedentInfermierService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.analyseMedicaleElement.epreuve = new EpreuveDto();
        this.epreuveService.findAll().subscribe((data) => this.epreuves = data);
        this.fichePatientElement.antecedent = new AntecedentDto();
        this.antecedentService.findAll().subscribe((data) => this.antecedents = data);
        this.radiologieElement.typeImage = new TypeImageDto();
        this.typeImageService.findAll().subscribe((data) => this.typeImages = data);
        this.medecinService.findAll().subscribe((data) => this.medecins = data);
        this.infermierService.findAll().subscribe((data) => this.infermiers = data);
        this.patientService.findAll().subscribe((data) => this.patients = data);
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
                this.item = new ConsultationDto();
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



    validateAnalyseMedicale(){
        this.errorMessages = new Array();
        this.validateAnalyseMedicaleRef();
    }
    validateFichePatient(){
        this.errorMessages = new Array();
        this.validateFichePatientRef();
    }
    validateRadiologie(){
        this.errorMessages = new Array();
        this.validateRadiologieRef();
    }
    validateDiagnostic(){
        this.errorMessages = new Array();
        this.validateDiagnosticRef();
    }
    validateSyntheseMedicale(){
        this.errorMessages = new Array();
        this.validateSyntheseMedicaleRef();
    }


    public  setValidation(value: boolean){
        this.validConsultationRef = value;
        this.validAnalyseMedicaleRef = value;
        this.validFichePatientRef = value;
        this.validRadiologieRef = value;
        this.validDiagnosticRef = value;
        this.validSyntheseMedicaleRef = value;
    }

    public addAnalyseMedicale() {
        if( this.item.analyseMedicale == null )
            this.item.analyseMedicale = new Array<AnalyseMedicaleDto>();
       this.validateAnalyseMedicale();
       if (this.errorMessages.length === 0) {
              this.item.analyseMedicale.push({... this.analyseMedicaleElement});
              this.analyseMedicaleElement = new AnalyseMedicaleDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }


    public deleteanalyseMedicale(p: AnalyseMedicaleDto) {
        this.item.analyseMedicale.forEach((element, index) => {
            if (element === p) { this.item.analyseMedicale.splice(index, 1); }
        });
    }

    public editanalyseMedicale(p: AnalyseMedicaleDto) {
        this.analyseMedicaleElement = {... p};
        this.activeTab = 0;
    }
    public addFichePatient() {
        if( this.item.fichePatient == null )
            this.item.fichePatient = new Array<FichePatientDto>();
       this.validateFichePatient();
       if (this.errorMessages.length === 0) {
              this.item.fichePatient.push({... this.fichePatientElement});
              this.fichePatientElement = new FichePatientDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }


    public deletefichePatient(p: FichePatientDto) {
        this.item.fichePatient.forEach((element, index) => {
            if (element === p) { this.item.fichePatient.splice(index, 1); }
        });
    }

    public editfichePatient(p: FichePatientDto) {
        this.fichePatientElement = {... p};
        this.activeTab = 0;
    }
    public addRadiologie() {
        if( this.item.radiologie == null )
            this.item.radiologie = new Array<RadiologieDto>();
       this.validateRadiologie();
       if (this.errorMessages.length === 0) {
              this.item.radiologie.push({... this.radiologieElement});
              this.radiologieElement = new RadiologieDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }


    public deleteradiologie(p: RadiologieDto) {
        this.item.radiologie.forEach((element, index) => {
            if (element === p) { this.item.radiologie.splice(index, 1); }
        });
    }

    public editradiologie(p: RadiologieDto) {
        this.radiologieElement = {... p};
        this.activeTab = 0;
    }
    public addDiagnostic() {
        if( this.item.diagnostic == null )
            this.item.diagnostic = new Array<DiagnosticDto>();
       this.validateDiagnostic();
       if (this.errorMessages.length === 0) {
              this.item.diagnostic.push({... this.diagnosticElement});
              this.diagnosticElement = new DiagnosticDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }


    public deletediagnostic(p: DiagnosticDto) {
        this.item.diagnostic.forEach((element, index) => {
            if (element === p) { this.item.diagnostic.splice(index, 1); }
        });
    }

    public editdiagnostic(p: DiagnosticDto) {
        this.diagnosticElement = {... p};
        this.activeTab = 0;
    }
    public addSyntheseMedicale() {
        if( this.item.syntheseMedicale == null )
            this.item.syntheseMedicale = new Array<SyntheseMedicaleDto>();
       this.validateSyntheseMedicale();
       if (this.errorMessages.length === 0) {
              this.item.syntheseMedicale.push({... this.syntheseMedicaleElement});
              this.syntheseMedicaleElement = new SyntheseMedicaleDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }


    public deletesyntheseMedicale(p: SyntheseMedicaleDto) {
        this.item.syntheseMedicale.forEach((element, index) => {
            if (element === p) { this.item.syntheseMedicale.splice(index, 1); }
        });
    }

    public editsyntheseMedicale(p: SyntheseMedicaleDto) {
        this.syntheseMedicaleElement = {... p};
        this.activeTab = 0;
    }


    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateConsultationRef();
    }

    public validateConsultationRef(){
        if (this.stringUtilService.isEmpty(this.item.ref)) {
        this.errorMessages.push('Ref non valide');
        this.validConsultationRef = false;
        } else {
            this.validConsultationRef = true;
        }
    }

    public validateAnalyseMedicaleRef(){
        if (this.analyseMedicaleElement.ref == null) {
            this.errorMessages.push('Ref de la analyseMedicale est  invalide');
            this.validAnalyseMedicaleRef = false;
        } else {
            this.validAnalyseMedicaleRef = true;
        }
    }
    public validateFichePatientRef(){
        if (this.fichePatientElement.ref == null) {
            this.errorMessages.push('Ref de la fichePatient est  invalide');
            this.validFichePatientRef = false;
        } else {
            this.validFichePatientRef = true;
        }
    }
    public validateRadiologieRef(){
        if (this.radiologieElement.ref == null) {
            this.errorMessages.push('Ref de la radiologie est  invalide');
            this.validRadiologieRef = false;
        } else {
            this.validRadiologieRef = true;
        }
    }
    public validateDiagnosticRef(){
        if (this.diagnosticElement.ref == null) {
            this.errorMessages.push('Ref de la diagnostic est  invalide');
            this.validDiagnosticRef = false;
        } else {
            this.validDiagnosticRef = true;
        }
    }
    public validateSyntheseMedicaleRef(){
        if (this.syntheseMedicaleElement.ref == null) {
            this.errorMessages.push('Ref de la syntheseMedicale est  invalide');
            this.validSyntheseMedicaleRef = false;
        } else {
            this.validSyntheseMedicaleRef = true;
        }
    }


    get medecin(): MedecinDto {
        return this.medecinService.item;
    }
    set medecin(value: MedecinDto) {
        this.medecinService.item = value;
    }
    get medecins(): Array<MedecinDto> {
        return this.medecinService.items;
    }
    set medecins(value: Array<MedecinDto>) {
        this.medecinService.items = value;
    }
    get createMedecinDialog(): boolean {
        return this.medecinService.createDialog;
    }
    set createMedecinDialog(value: boolean) {
        this.medecinService.createDialog= value;
    }
    get infermier(): InfermierDto {
        return this.infermierService.item;
    }
    set infermier(value: InfermierDto) {
        this.infermierService.item = value;
    }
    get infermiers(): Array<InfermierDto> {
        return this.infermierService.items;
    }
    set infermiers(value: Array<InfermierDto>) {
        this.infermierService.items = value;
    }
    get createInfermierDialog(): boolean {
        return this.infermierService.createDialog;
    }
    set createInfermierDialog(value: boolean) {
        this.infermierService.createDialog= value;
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
    get createPatientDialog(): boolean {
        return this.patientService.createDialog;
    }
    set createPatientDialog(value: boolean) {
        this.patientService.createDialog= value;
    }
    get typeImage(): TypeImageDto {
        return this.typeImageService.item;
    }
    set typeImage(value: TypeImageDto) {
        this.typeImageService.item = value;
    }
    get typeImages(): Array<TypeImageDto> {
        return this.typeImageService.items;
    }
    set typeImages(value: Array<TypeImageDto>) {
        this.typeImageService.items = value;
    }
    get createTypeImageDialog(): boolean {
        return this.typeImageService.createDialog;
    }
    set createTypeImageDialog(value: boolean) {
        this.typeImageService.createDialog= value;
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
    get createAntecedentDialog(): boolean {
        return this.antecedentService.createDialog;
    }
    set createAntecedentDialog(value: boolean) {
        this.antecedentService.createDialog= value;
    }



    get validConsultationRef(): boolean {
        return this._validConsultationRef;
    }

    set validConsultationRef(value: boolean) {
         this._validConsultationRef = value;
    }

    get validMedecinRef(): boolean {
        return this._validMedecinRef;
    }
    set validMedecinRef(value: boolean) {
        this._validMedecinRef = value;
    }
    get validMedecinEmail(): boolean {
        return this._validMedecinEmail;
    }
    set validMedecinEmail(value: boolean) {
        this._validMedecinEmail = value;
    }
    get validInfermierRef(): boolean {
        return this._validInfermierRef;
    }
    set validInfermierRef(value: boolean) {
        this._validInfermierRef = value;
    }
    get validInfermierEmail(): boolean {
        return this._validInfermierEmail;
    }
    set validInfermierEmail(value: boolean) {
        this._validInfermierEmail = value;
    }
    get validPatientNumDossier(): boolean {
        return this._validPatientNumDossier;
    }
    set validPatientNumDossier(value: boolean) {
        this._validPatientNumDossier = value;
    }
    get validAnalyseMedicaleRef(): boolean {
        return this._validAnalyseMedicaleRef;
    }
    set validAnalyseMedicaleRef(value: boolean) {
        this._validAnalyseMedicaleRef = value;
    }
    get validFichePatientRef(): boolean {
        return this._validFichePatientRef;
    }
    set validFichePatientRef(value: boolean) {
        this._validFichePatientRef = value;
    }
    get validRadiologieRef(): boolean {
        return this._validRadiologieRef;
    }
    set validRadiologieRef(value: boolean) {
        this._validRadiologieRef = value;
    }
    get validDiagnosticRef(): boolean {
        return this._validDiagnosticRef;
    }
    set validDiagnosticRef(value: boolean) {
        this._validDiagnosticRef = value;
    }
    get validSyntheseMedicaleRef(): boolean {
        return this._validSyntheseMedicaleRef;
    }
    set validSyntheseMedicaleRef(value: boolean) {
        this._validSyntheseMedicaleRef = value;
    }

    get analyseMedicaleElement(): AnalyseMedicaleDto {
        if( this._analyseMedicaleElement == null )
            this._analyseMedicaleElement = new AnalyseMedicaleDto();
        return this._analyseMedicaleElement;
    }

    set analyseMedicaleElement(value: AnalyseMedicaleDto) {
        this._analyseMedicaleElement = value;
    }
    get fichePatientElement(): FichePatientDto {
        if( this._fichePatientElement == null )
            this._fichePatientElement = new FichePatientDto();
        return this._fichePatientElement;
    }

    set fichePatientElement(value: FichePatientDto) {
        this._fichePatientElement = value;
    }
    get radiologieElement(): RadiologieDto {
        if( this._radiologieElement == null )
            this._radiologieElement = new RadiologieDto();
        return this._radiologieElement;
    }

    set radiologieElement(value: RadiologieDto) {
        this._radiologieElement = value;
    }
    get diagnosticElement(): DiagnosticDto {
        if( this._diagnosticElement == null )
            this._diagnosticElement = new DiagnosticDto();
        return this._diagnosticElement;
    }

    set diagnosticElement(value: DiagnosticDto) {
        this._diagnosticElement = value;
    }
    get syntheseMedicaleElement(): SyntheseMedicaleDto {
        if( this._syntheseMedicaleElement == null )
            this._syntheseMedicaleElement = new SyntheseMedicaleDto();
        return this._syntheseMedicaleElement;
    }

    set syntheseMedicaleElement(value: SyntheseMedicaleDto) {
        this._syntheseMedicaleElement = value;
    }

    get items(): Array<ConsultationDto> {
        return this.service.items;
    }

    set items(value: Array<ConsultationDto>) {
        this.service.items = value;
    }

    get item(): ConsultationDto {
        return this.service.item;
    }

    set item(value: ConsultationDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): ConsultationCriteria {
        return this.service.criteria;
    }

    set criteria(value: ConsultationCriteria) {
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
