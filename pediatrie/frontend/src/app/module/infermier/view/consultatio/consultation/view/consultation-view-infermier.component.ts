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
  selector: 'app-consultation-view-infermier',
  templateUrl: './consultation-view-infermier.component.html'
})
export class ConsultationViewInfermierComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    analyseMedicale = new AnalyseMedicaleDto();
    analyseMedicales: Array<AnalyseMedicaleDto> = [];
    fichePatient = new FichePatientDto();
    fichePatients: Array<FichePatientDto> = [];
    radiologie = new RadiologieDto();
    radiologies: Array<RadiologieDto> = [];
    diagnostic = new DiagnosticDto();
    diagnostics: Array<DiagnosticDto> = [];
    syntheseMedicale = new SyntheseMedicaleDto();
    syntheseMedicales: Array<SyntheseMedicaleDto> = [];

    constructor(private service: ConsultationInfermierService, private fichePatientService: FichePatientInfermierService, private radiologieService: RadiologieInfermierService, private infermierService: InfermierInfermierService, private medecinService: MedecinInfermierService, private epreuveService: EpreuveInfermierService, private syntheseMedicaleService: SyntheseMedicaleInfermierService, private patientService: PatientInfermierService, private diagnosticService: DiagnosticInfermierService, private analyseMedicaleService: AnalyseMedicaleInfermierService, private typeImageService: TypeImageInfermierService, private antecedentService: AntecedentInfermierService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): ConsultationCriteria {
        return this.service.criteria;
    }

    set criteria(value: ConsultationCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
