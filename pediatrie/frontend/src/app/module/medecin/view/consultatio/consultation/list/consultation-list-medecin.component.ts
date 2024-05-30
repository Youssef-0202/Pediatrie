import {Component, OnInit} from '@angular/core';
import {ConsultationMedecinService} from 'src/app/shared/service/medecin/consultatio/ConsultationMedecin.service';
import {ConsultationDto} from 'src/app/shared/model/consultatio/Consultation.model';
import {ConsultationCriteria} from 'src/app/shared/criteria/consultatio/ConsultationCriteria.model';


import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
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

import {AuthService} from 'src/app/zynerator/security/shared/service/Auth.service';
import {ExportService} from 'src/app/zynerator/util/Export.service';


import {FichePatientDto} from 'src/app/shared/model/dossie/FichePatient.model';
import {FichePatientMedecinService} from 'src/app/shared/service/medecin/dossie/FichePatientMedecin.service';
import {RadiologieDto} from 'src/app/shared/model/dossie/Radiologie.model';
import {RadiologieMedecinService} from 'src/app/shared/service/medecin/dossie/RadiologieMedecin.service';
import {InfermierDto} from 'src/app/shared/model/commun/Infermier.model';
import {InfermierMedecinService} from 'src/app/shared/service/medecin/commun/InfermierMedecin.service';
import {MedecinDto} from 'src/app/shared/model/commun/Medecin.model';
import {MedecinMedecinService} from 'src/app/shared/service/medecin/commun/MedecinMedecin.service';
import {EpreuveDto} from 'src/app/shared/model/dossie/Epreuve.model';
import {EpreuveMedecinService} from 'src/app/shared/service/medecin/dossie/EpreuveMedecin.service';
import {SyntheseMedicaleDto} from 'src/app/shared/model/rappor/SyntheseMedicale.model';
import {SyntheseMedicaleMedecinService} from 'src/app/shared/service/medecin/rappor/SyntheseMedicaleMedecin.service';
import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientMedecinService} from 'src/app/shared/service/medecin/patient/PatientMedecin.service';
import {DiagnosticDto} from 'src/app/shared/model/rappor/Diagnostic.model';
import {DiagnosticMedecinService} from 'src/app/shared/service/medecin/rappor/DiagnosticMedecin.service';
import {AnalyseMedicaleDto} from 'src/app/shared/model/dossie/AnalyseMedicale.model';
import {AnalyseMedicaleMedecinService} from 'src/app/shared/service/medecin/dossie/AnalyseMedicaleMedecin.service';
import {TypeImageDto} from 'src/app/shared/model/dossie/TypeImage.model';
import {TypeImageMedecinService} from 'src/app/shared/service/medecin/dossie/TypeImageMedecin.service';
import {AntecedentDto} from 'src/app/shared/model/dossie/Antecedent.model';
import {AntecedentMedecinService} from 'src/app/shared/service/medecin/dossie/AntecedentMedecin.service';


@Component({
  selector: 'app-consultation-list-medecin',
  templateUrl: './consultation-list-medecin.component.html'
})
export class ConsultationListMedecinComponent implements OnInit {

    protected fileName = 'Consultation';

    protected findByCriteriaShow = false;
    protected cols: any[] = [];
    protected excelPdfButons: MenuItem[];
    protected exportData: any[] = [];
    protected criteriaData: any[] = [];
    protected _totalRecords = 0;
    private _pdfName: string;


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    protected authService: AuthService;
    protected exportService: ExportService;
    protected excelFile: File | undefined;
    protected enableSecurity = false;


    medecins: Array<MedecinDto>;
    infermiers: Array<InfermierDto>;
    patients: Array<PatientDto>;


    constructor( private service: ConsultationMedecinService  , private fichePatientService: FichePatientMedecinService, private radiologieService: RadiologieMedecinService, private infermierService: InfermierMedecinService, private medecinService: MedecinMedecinService, private epreuveService: EpreuveMedecinService, private syntheseMedicaleService: SyntheseMedicaleMedecinService, private patientService: PatientMedecinService, private diagnosticService: DiagnosticMedecinService, private analyseMedicaleService: AnalyseMedicaleMedecinService, private typeImageService: TypeImageMedecinService, private antecedentService: AntecedentMedecinService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.authService = ServiceLocator.injector.get(AuthService);
        this.exportService = ServiceLocator.injector.get(ExportService);
    }

    ngOnInit(): void {
        this.findPaginatedByCriteria();
        this.initExport();
        this.initCol();
        this.loadMedecin();
        this.loadInfermier();
        this.loadPatient();

    }




    public onExcelFileSelected(event: any): void {
        const input = event.target as HTMLInputElement;
        if (input.files && input.files.length > 0) {
            this.excelFile = input.files[0];
        }
    }

    public importExcel(): void {
        if (this.excelFile) {
            this.service.importExcel(this.excelFile).subscribe(
                response => {
                    console.log('File uploaded successfully!', response);
                },
                error => {
                    console.error('Error uploading file:', error);
                }
            );
        }
    }

    public findPaginatedByCriteria() {
        this.service.findPaginatedByCriteria(this.criteria).subscribe(paginatedItems => {
            this.items = paginatedItems.list;
            this.totalRecords = paginatedItems.dataSize;
            this.selections = new Array<ConsultationDto>();
        }, error => console.log(error));
    }

    public onPage(event: any) {
        this.criteria.page = event.page;
        this.criteria.maxResults = event.rows;
        this.findPaginatedByCriteria();
    }

    public async edit(dto: ConsultationDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            console.log(res);
            this.editDialog = true;
        });

    }

    public async view(dto: ConsultationDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            this.viewDialog = true;
        });
    }

    public async openCreate() {
        this.item = new ConsultationDto();
        this.createDialog = true;
    }

    public async deleteMultiple() {
        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer ces éléments ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.service.deleteMultiple().subscribe(() => {
                    this.items = this.items.filter(item => !this.selections.includes(item));
                    this.selections = new Array<ConsultationDto>();
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Succès',
                        detail: 'Les éléments sélectionnés ont été supprimés',
                        life: 3000
                    });

                }, error => console.log(error));
            }
        });
    }


    public isSelectionDisabled(): boolean {
        return this.selections == null || this.selections.length == 0;
    }


    public async delete(dto: ConsultationDto) {

        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer cet élément ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.service.delete(dto).subscribe(status => {
                    if (status > 0) {
                        const position = this.items.indexOf(dto);
                        position > -1 ? this.items.splice(position, 1) : false;
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Succès',
                            detail: 'Element Supprimé',
                            life: 3000
                        });
                    }

                }, error => console.log(error));
            }
        });

    }

    public async duplicate(dto: ConsultationDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(
            res => {
                this.initDuplicate(res);
                this.item = res;
                this.item.id = null;
                this.createDialog = true;
            });
    }

    // TODO : check if correct
    public initExport(): void {
        this.excelPdfButons = [
            {
                label: 'CSV', icon: 'pi pi-file', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterCSV(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'XLS', icon: 'pi pi-file-excel', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterExcel(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'PDF', icon: 'pi pi-file-pdf', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterPdf(this.criteriaData, this.exportData, this.fileName);
                }
            }
        ];
    }

    public exportPdf(dto: ConsultationDto): void {
        this.service.exportPdf(dto).subscribe((data: ArrayBuffer) => {
            const blob = new Blob([data], {type: 'application/pdf'});
            const url = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = url;
            link.download = this.pdfName;
            link.setAttribute('target', '_blank'); // open link in new tab
            link.click();
            window.URL.revokeObjectURL(url);
        }, (error) => {
            console.error(error); // handle any errors that occur
        });
    }

    public showSearch(): void {
        this.findByCriteriaShow = !this.findByCriteriaShow;
    }


    update() {
        this.service.edit().subscribe(data => {
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = data;
            this.editDialog = false;
            this.item = new ConsultationDto();
        } , error => {
            console.log(error);
        });
    }

    public save() {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;


                this.item = new ConsultationDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }
        }, error => {
            console.log(error);
        });
    }

// add


    public initCol() {
        this.cols = [
            {field: 'ref', header: 'Ref'},
            {field: 'dateConsultation', header: 'Date consultation'},
            {field: 'heureConsultation', header: 'Heure consultation'},
            {field: 'typeConsultation', header: 'Type consultation'},
            {field: 'medecin?.email', header: 'Medecin'},
            {field: 'infermier?.email', header: 'Infermier'},
            {field: 'patient?.numDossier', header: 'Patient'},
        ];
    }


    public async loadMedecin(){
        this.medecinService.findAllOptimized().subscribe(medecins => this.medecins = medecins, error => console.log(error))
    }
    public async loadInfermier(){
        this.infermierService.findAllOptimized().subscribe(infermiers => this.infermiers = infermiers, error => console.log(error))
    }
    public async loadPatient(){
        this.patientService.findAllOptimized().subscribe(patients => this.patients = patients, error => console.log(error))
    }


	public initDuplicate(res: ConsultationDto) {
        if (res.analyseMedicale != null) {
             res.analyseMedicale.forEach(d => { d.consultation = null; d.id = null; });
        }
        if (res.fichePatient != null) {
             res.fichePatient.forEach(d => { d.consultation = null; d.id = null; });
        }
        if (res.radiologie != null) {
             res.radiologie.forEach(d => { d.consultationDto = null; d.id = null; });
        }
        if (res.diagnostic != null) {
             res.diagnostic.forEach(d => { d.consultation = null; d.id = null; });
        }
        if (res.syntheseMedicale != null) {
             res.syntheseMedicale.forEach(d => { d.consultation = null; d.id = null; });
        }
	}



   public prepareColumnExport(): void {
        this.exportData = this.items.map(e => {
            return {
                 'Ref': e.ref ,
                'Date consultation': this.datePipe.transform(e.dateConsultation , 'dd/MM/yyyy hh:mm'),
                'Heure consultation': this.datePipe.transform(e.heureConsultation , 'dd/MM/yyyy hh:mm'),
                 'Type consultation': e.typeConsultation ,
                'Medecin': e.medecin?.email ,
                'Infermier': e.infermier?.email ,
                'Patient': e.patient?.numDossier ,
            }
        });

        this.criteriaData = [{
            'Ref': this.criteria.ref ? this.criteria.ref : environment.emptyForExport ,
            'Date consultation Min': this.criteria.dateConsultationFrom ? this.datePipe.transform(this.criteria.dateConsultationFrom , this.dateFormat) : environment.emptyForExport ,
            'Date consultation Max': this.criteria.dateConsultationTo ? this.datePipe.transform(this.criteria.dateConsultationTo , this.dateFormat) : environment.emptyForExport ,
            'Heure consultation Min': this.criteria.heureConsultationFrom ? this.datePipe.transform(this.criteria.heureConsultationFrom , this.dateFormat) : environment.emptyForExport ,
            'Heure consultation Max': this.criteria.heureConsultationTo ? this.datePipe.transform(this.criteria.heureConsultationTo , this.dateFormat) : environment.emptyForExport ,
            'Type consultation': this.criteria.typeConsultation ? this.criteria.typeConsultation : environment.emptyForExport ,
        //'Medecin': this.criteria.medecin?.email ? this.criteria.medecin?.email : environment.emptyForExport ,
        //'Infermier': this.criteria.infermier?.email ? this.criteria.infermier?.email : environment.emptyForExport ,
        //'Patient': this.criteria.patient?.numDossier ? this.criteria.patient?.numDossier : environment.emptyForExport ,
        }];
      }



    get items(): Array<ConsultationDto> {
        return this.service.items;
    }

    set items(value: Array<ConsultationDto>) {
        this.service.items = value;
    }

        get selections(): Array<ConsultationDto> {
            return this.service.selections;
        }

    set selections(value: Array<ConsultationDto>) {
        this.service.selections = value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
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

    get dateFormat() {
        return environment.dateFormatList;
    }


    get totalRecords(): number {
        return this._totalRecords;
    }

    set totalRecords(value: number) {
        this._totalRecords = value;
    }

    get pdfName(): string {
        return this._pdfName;
    }

    set pdfName(value: string) {
        this._pdfName = value;
    }

    get createActionIsValid(): boolean {
        return this.service.createActionIsValid;
    }

    set createActionIsValid(value: boolean) {
        this.service.createActionIsValid = value;
    }


    get editActionIsValid(): boolean {
        return this.service.editActionIsValid;
    }

    set editActionIsValid(value: boolean) {
        this.service.editActionIsValid = value;
    }

    get listActionIsValid(): boolean {
        return this.service.listActionIsValid;
    }

    set listActionIsValid(value: boolean) {
        this.service.listActionIsValid = value;
    }

    get deleteActionIsValid(): boolean {
        return this.service.deleteActionIsValid;
    }

    set deleteActionIsValid(value: boolean) {
        this.service.deleteActionIsValid = value;
    }


    get viewActionIsValid(): boolean {
        return this.service.viewActionIsValid;
    }

    set viewActionIsValid(value: boolean) {
        this.service.viewActionIsValid = value;
    }

    get duplicateActionIsValid(): boolean {
        return this.service.duplicateActionIsValid;
    }

    set duplicateActionIsValid(value: boolean) {
        this.service.duplicateActionIsValid = value;
    }

    get createAction(): string {
        return this.service.createAction;
    }

    set createAction(value: string) {
        this.service.createAction = value;
    }

    get listAction(): string {
        return this.service.listAction;
    }

    set listAction(value: string) {
        this.service.listAction = value;
    }

    get editAction(): string {
        return this.service.editAction;
    }

    set editAction(value: string) {
        this.service.editAction = value;
    }

    get deleteAction(): string {
        return this.service.deleteAction;
    }

    set deleteAction(value: string) {
        this.service.deleteAction = value;
    }

    get viewAction(): string {
        return this.service.viewAction;
    }

    set viewAction(value: string) {
        this.service.viewAction = value;
    }

    get duplicateAction(): string {
        return this.service.duplicateAction;
    }

    set duplicateAction(value: string) {
        this.service.duplicateAction = value;
    }

    get entityName(): string {
        return this.service.entityName;
    }

    set entityName(value: string) {
        this.service.entityName = value;
    }
}
