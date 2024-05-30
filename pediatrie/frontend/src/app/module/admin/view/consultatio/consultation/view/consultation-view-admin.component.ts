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


import {ConsultationAdminService} from 'src/app/shared/service/admin/consultatio/ConsultationAdmin.service';
import {ConsultationDto} from 'src/app/shared/model/consultatio/Consultation.model';
import {ConsultationCriteria} from 'src/app/shared/criteria/consultatio/ConsultationCriteria.model';

import {FichePatientDto} from 'src/app/shared/model/dossie/FichePatient.model';
import {FichePatientAdminService} from 'src/app/shared/service/admin/dossie/FichePatientAdmin.service';
import {RadiologieDto} from 'src/app/shared/model/dossie/Radiologie.model';
import {RadiologieAdminService} from 'src/app/shared/service/admin/dossie/RadiologieAdmin.service';
import {InfermierDto} from 'src/app/shared/model/commun/Infermier.model';
import {InfermierAdminService} from 'src/app/shared/service/admin/commun/InfermierAdmin.service';
import {MedecinDto} from 'src/app/shared/model/commun/Medecin.model';
import {MedecinAdminService} from 'src/app/shared/service/admin/commun/MedecinAdmin.service';
import {EpreuveDto} from 'src/app/shared/model/dossie/Epreuve.model';
import {EpreuveAdminService} from 'src/app/shared/service/admin/dossie/EpreuveAdmin.service';
import {SyntheseMedicaleDto} from 'src/app/shared/model/rappor/SyntheseMedicale.model';
import {SyntheseMedicaleAdminService} from 'src/app/shared/service/admin/rappor/SyntheseMedicaleAdmin.service';
import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientAdminService} from 'src/app/shared/service/admin/patient/PatientAdmin.service';
import {DiagnosticDto} from 'src/app/shared/model/rappor/Diagnostic.model';
import {DiagnosticAdminService} from 'src/app/shared/service/admin/rappor/DiagnosticAdmin.service';
import {AnalyseMedicaleDto} from 'src/app/shared/model/dossie/AnalyseMedicale.model';
import {AnalyseMedicaleAdminService} from 'src/app/shared/service/admin/dossie/AnalyseMedicaleAdmin.service';
import {TypeImageDto} from 'src/app/shared/model/dossie/TypeImage.model';
import {TypeImageAdminService} from 'src/app/shared/service/admin/dossie/TypeImageAdmin.service';
import {AntecedentDto} from 'src/app/shared/model/dossie/Antecedent.model';
import {AntecedentAdminService} from 'src/app/shared/service/admin/dossie/AntecedentAdmin.service';
@Component({
  selector: 'app-consultation-view-admin',
  templateUrl: './consultation-view-admin.component.html'
})
export class ConsultationViewAdminComponent implements OnInit {


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

    constructor(private service: ConsultationAdminService, private fichePatientService: FichePatientAdminService, private radiologieService: RadiologieAdminService, private infermierService: InfermierAdminService, private medecinService: MedecinAdminService, private epreuveService: EpreuveAdminService, private syntheseMedicaleService: SyntheseMedicaleAdminService, private patientService: PatientAdminService, private diagnosticService: DiagnosticAdminService, private analyseMedicaleService: AnalyseMedicaleAdminService, private typeImageService: TypeImageAdminService, private antecedentService: AntecedentAdminService){
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
