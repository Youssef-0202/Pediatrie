import {Component, OnInit} from "@angular/core";
import {DossierService} from "../../shared/service/medecin/commun/Dossier.service";
import {DialogModule} from "primeng/dialog";
import {DatePipe, NgClass, NgStyle} from "@angular/common";
import {TabViewModule} from "primeng/tabview";
import {TranslateModule} from "@ngx-translate/core";
import {InputSwitchModule} from "primeng/inputswitch";
import {ButtonModule} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";
import {CalendarModule} from "primeng/calendar";
import {PatientDto} from "../../shared/model/patient/Patient.model";
import {TableModule} from "primeng/table";
import {RippleModule} from "primeng/ripple";
import {ToolbarModule} from "primeng/toolbar";
import {ConsultationDto} from "../../shared/model/consultatio/Consultation.model";
import {PatientContactDto} from "../../shared/model/patient/PatientContact.model";
import {AntecedentDto} from "../../shared/model/dossie/Antecedent.model";
import {OrdonnanceDto} from "../../shared/model/gestio/Ordonnance.model";
import {CertificatDto} from "../../shared/model/gestio/Certificat.model";
import {AnalyseMedicaleDto} from "../../shared/model/dossie/AnalyseMedicale.model";
import {RadiologieDto} from "../../shared/model/dossie/Radiologie.model";
import {FichePatientDto} from "../../shared/model/dossie/FichePatient.model";
import {SyntheseMedicaleDto} from "../../shared/model/rappor/SyntheseMedicale.model";
import {DiagnosticDto} from "../../shared/model/rappor/Diagnostic.model";
import {ChartModule} from "primeng/chart";

@Component({
    selector: 'app-dossier',
    standalone: true,
    imports: [
        DialogModule,
        NgClass,
        TabViewModule,
        TranslateModule,
        InputSwitchModule,
        ButtonModule,
        DropdownModule,
        CalendarModule,
        TableModule,
        RippleModule,
        ToolbarModule,
        DatePipe,
        NgStyle,
        ChartModule
    ],
    templateUrl: './dossier.component.html',
    styleUrls:['/dossier.component.css']
})
export class DossierComponent implements OnInit {
    data: any;
    options: any;
    array = new Array<PatientDto>(this.actualPationt)
    private _activeTab = 0;
    showConsultationDetails: boolean = false;
    documentStyle = getComputedStyle(document.documentElement);
    textColor = this.documentStyle.getPropertyValue('--text-color');
    textColorSecondary = this.documentStyle.getPropertyValue('--text-color-secondary');
    surfaceBorder = this.documentStyle.getPropertyValue('--surface-border');


    constructor(private service:DossierService) {

    }

    ngOnInit(): void {
       this.loadChart();
        this.data = {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [
                {
                    label: "Bilan d'annalyses : ",
                    data: [28, 48, 40, 19, 86, 27, 90],
                    fill: false,
                    borderDash: [5, 5],
                    tension: 0.4,
                    borderColor: this.documentStyle.getPropertyValue('--teal-500')
                }
            ]
        };

        this.options = {
            maintainAspectRatio: false,
            aspectRatio: 0.6,
            plugins: {
                legend: {
                    labels: {
                        color: this.textColor
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: this.textColorSecondary
                    },
                    grid: {
                        color: this.surfaceBorder
                    }
                },
                y: {
                    ticks: {
                        color: this.textColorSecondary
                    },
                    grid: {
                        color: this.surfaceBorder
                    }
                }
            }
        };
    }

    loadChart() {
        this.data = {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [
                {
                    label: 'First Dataset',
                    data: [65, 59, 80, 81, 56, 55, 40],
                    fill: false,
                    borderColor: this.documentStyle.getPropertyValue('--blue-500'),
                    tension: 0.4
                },
                {
                    label: 'Second Dataset',
                    data: [28, 48, 40, 19, 86, 27, 90],
                    fill: false,
                    borderColor: this.documentStyle.getPropertyValue('--pink-500'),
                    tension: 0.4
                }
            ]
        };

        this.options = {
            maintainAspectRatio: false,
            aspectRatio: 0.6,
            plugins: {
                legend: {
                    labels: {
                        color: this.textColor
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: this.textColorSecondary
                    },
                    grid: {
                        color: this.surfaceBorder,
                        drawBorder: false
                    }
                },
                y: {
                    ticks: {
                        color: this.textColorSecondary
                    },
                    grid: {
                        color: this.surfaceBorder,
                        drawBorder: false
                    }
                }
            }
        };
    }
    get showDossierDetails(): boolean {
        return this.service.showDossierDetails;
    }
    set showDossierDetails(value: boolean) {
        this.service.showDossierDetails = value;
    }
    get actualPationt(): PatientDto {
        return this.service.actualPationt;
    }

    set actualPationt(value: PatientDto) {
        this.service.actualPationt = value;
    }

    get consultations(): ConsultationDto[] {
        return this.service.consultations;
    }

    set consultations(value: ConsultationDto[]) {
        this.service.consultations = value;
    }

    selectProduct(product: any) {
    }

    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }

    get contacts(): PatientContactDto[] {
        return this.service.contacts;
    }

    set contacts(value: PatientContactDto[]) {
        this.service.contacts = value;
    }

    get antecedents(): AntecedentDto[] {
        return this.service.antecedents;
    }

    set antecedents(value: AntecedentDto[]) {
        this.service.antecedents = value;
    }

    get ordonances(): OrdonnanceDto[] {
        return this.service.ordonances;
    }

    set ordonances(value: OrdonnanceDto[]) {
        this.service.ordonances = value;
    }

    get certificats(): CertificatDto[] {
        return this.service.certificats;
    }

    set certificats(value: CertificatDto[]) {
        this.service.certificats = value;

    }

    showConsultation(consultation:ConsultationDto) {
        this.showConsultationDetails = !this.showConsultationDetails;
        this.actualConsultation = consultation;
        this.service.findAllFichePatient();
        this.service.findAllAnalyses();
        this.service.findAllRadiologie();
    }

    get actualConsultation(): ConsultationDto {
        return this.service.actualConsultation;
    }

    set actualConsultation(value: ConsultationDto) {
        this.service.actualConsultation = value;
    }

    get analyses(): AnalyseMedicaleDto[] {
        return this.service.analyses;
    }
    set analyses(value: AnalyseMedicaleDto[]) {
        this.service.analyses = value;
    }

    get radiologies(): RadiologieDto[] {
        return this.service.radiologies;
    }

    set radiologies(value: RadiologieDto[]) {
        this.service.radiologies = value;
    }

    get fichePatients(): FichePatientDto[] {
        return this.service.fichePatients;
    }

    set fichePatients(value: FichePatientDto[]) {
        this.service.fichePatients = value;
    }

    get syntheses(): SyntheseMedicaleDto[] {
        return this.service.syntheses;
    }

    set syntheses(value: SyntheseMedicaleDto[]) {
        this.service.syntheses = value;
    }

    get diagnostics(): DiagnosticDto[] {
        return this.service.diagnostics;
    }

    set diagnostics(value: DiagnosticDto[]) {
        this.service.diagnostics = value;
    }

}
