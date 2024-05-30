import {Component, OnInit} from "@angular/core";
import {ConsultationDto} from "../../../../../../../shared/model/consultatio/Consultation.model";
import {
    ConsultationMedecinService
} from "../../../../../../../shared/service/medecin/consultatio/ConsultationMedecin.service";
import {FichePatientMedecinService} from "../../../../../../../shared/service/medecin/dossie/FichePatientMedecin.service";
import {RadiologieMedecinService} from "../../../../../../../shared/service/medecin/dossie/RadiologieMedecin.service";
import {InfermierMedecinService} from "../../../../../../../shared/service/medecin/commun/InfermierMedecin.service";
import {MedecinMedecinService} from "../../../../../../../shared/service/medecin/commun/MedecinMedecin.service";
import {EpreuveMedecinService} from "../../../../../../../shared/service/medecin/dossie/EpreuveMedecin.service";
import {
    SyntheseMedicaleMedecinService
} from "../../../../../../../shared/service/medecin/rappor/SyntheseMedicaleMedecin.service";
import {PatientMedecinService} from "../../../../../../../shared/service/medecin/patient/PatientMedecin.service";
import {DiagnosticMedecinService} from "../../../../../../../shared/service/medecin/rappor/DiagnosticMedecin.service";
import {
    AnalyseMedicaleMedecinService
} from "../../../../../../../shared/service/medecin/dossie/AnalyseMedicaleMedecin.service";
import {TypeImageMedecinService} from "../../../../../../../shared/service/medecin/dossie/TypeImageMedecin.service";
import {AntecedentMedecinService} from "../../../../../../../shared/service/medecin/dossie/AntecedentMedecin.service";
import {ConsultationCriteria} from "../../../../../../../shared/criteria/consultatio/ConsultationCriteria.model";
import {
    ConsultationInfermierService
} from "../../../../../../../shared/service/infermier/consultatio/ConsultationInfermier.service";
import {PatientInfermierService} from "../../../../../../../shared/service/infermier/patient/PatientInfermier.service";
import {Router} from "@angular/router";
import {
    AnalyseMedicaleInfermierService
} from "../../../../../../../shared/service/infermier/dossie/AnalyseMedicaleInfermier.service";
import {
    FichePatientInfermierService
} from "../../../../../../../shared/service/infermier/dossie/FichePatientInfermier.service";
import {
    RadiologieInfermierService
} from "../../../../../../../shared/service/infermier/dossie/RadiologieInfermier.service";
import {
    InfermierInfermierService
} from "../../../../../../../shared/service/infermier/commun/InfermierInfermier.service";
import {MedecinInfermierService} from "../../../../../../../shared/service/infermier/commun/MedecinInfermier.service";
import {
    DiagnosticInfermierService
} from "../../../../../../../shared/service/infermier/rappor/DiagnosticInfermier.service";
import {
    SyntheseMedicaleInfermierService
} from "../../../../../../../shared/service/infermier/rappor/SyntheseMedicaleInfermier.service";
import {EpreuveInfermierService} from "../../../../../../../shared/service/infermier/dossie/EpreuveInfermier.service";

@Component({
    selector: 'app-patient-consultation-infermier',
    templateUrl: './patient-consultation-infermier.html'
})
export class PatientConsultationInfermier implements OnInit {

    cols: any[] | undefined;

    ngOnInit(){
      this.findPaginatedByCriteria();
      this.initCol();
    }

    constructor(private router:Router,private  service :PatientInfermierService,private serviceConsultation: ConsultationInfermierService  , private fichePatientService: FichePatientInfermierService, private radiologieService: RadiologieInfermierService, private infermierService: InfermierInfermierService, private medecinService: MedecinInfermierService, private epreuveService: EpreuveInfermierService, private syntheseMedicaleService: SyntheseMedicaleInfermierService, private patientService: PatientInfermierService, private diagnosticService: DiagnosticInfermierService, private analyseMedicaleInfermierService: AnalyseMedicaleInfermierService, private typeImageService: TypeImageMedecinService, private antecedentService: AntecedentMedecinService) {
    }

    public findPaginatedByCriteria() {
        this.serviceConsultation.findPaginatedByCriteria(this.criteria).subscribe(paginatedItems => {
            this.items = paginatedItems.list;
            this.selections = new Array<ConsultationDto>();
        }, error => console.log(error));
    }

    view(ref:string){
        this.viewDialog2=true;
        this.consultationPatient=this.serviceConsultation.items.find(e=>e.ref==ref);
        this.analyseMedicaleInfermierService.findByConsultationRef(this.consultationPatient.ref).subscribe(res=>{
           this.consultationPatient.analyseMedicale=res
        });
        this.fichePatientService.findByConsultationRef(this.consultationPatient.ref).subscribe(res=>{
            this.consultationPatient.fichePatient=res
        });
        this.radiologieService.findByConsultationRef(this.consultationPatient.ref).subscribe(res=>{
            this.consultationPatient.radiologie=res
        });
        this.diagnosticService.findByPationNumDossier(this.consultationPatient.patient.numDossier).subscribe(res=>{
            this.consultationPatient.diagnostic=res
        });
        this.syntheseMedicaleService.findByPationNumDossier(this.consultationPatient.patient.numDossier).subscribe(res=>{
            this.consultationPatient.syntheseMedicale=res
        });
        this.router.navigateByUrl('/app/infermier/patient/patient/consultation')
    }

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

    get items(): Array<ConsultationDto> {
        return this.serviceConsultation.items;
    }

    set items(value: Array<ConsultationDto>) {
        this.serviceConsultation.items = value;
    }

    get viewDialog2(): boolean {
        return this.service.viewDialog2;
    }

    set viewDialog2(value: boolean) {
        this.service.viewDialog2 = value;
    }

    get patientConsultations(): Array<ConsultationDto> {
        return this.service.patientsConsultation;
    }

    set patientConsultations(value: Array<ConsultationDto>) {
        this.service.patientsConsultation = value;
    }

    get selections(): any {
        return this.serviceConsultation.selections;
    }

    get criteria(): ConsultationCriteria {
        return this.serviceConsultation.criteria;
    }

    set criteria(value: ConsultationCriteria) {
        this.serviceConsultation.criteria = value;
    }


    public set selections(value: Array<ConsultationDto>) {
        this.serviceConsultation.selections = value;
    }

    get consultationPatient(): ConsultationDto {
        return this.serviceConsultation.consultationPatient;
    }

    set consultationPatient(value: ConsultationDto) {
        this.serviceConsultation.consultationPatient = value;
    }



}
