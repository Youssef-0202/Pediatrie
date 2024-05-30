import {Injectable} from "@angular/core";
import {PatientDto} from "../../../model/patient/Patient.model";
import {ConsultationAdminService} from "../../admin/consultatio/ConsultationAdmin.service";
import {ConsultationDto} from "../../../model/consultatio/Consultation.model";
import {ConsultationMedecinService} from "../consultatio/ConsultationMedecin.service";
import {ConsultationInfermierService} from "../../infermier/consultatio/ConsultationInfermier.service";
import {FichePatientMedecinService} from "../dossie/FichePatientMedecin.service";
import {RadiologieInfermierService} from "../../infermier/dossie/RadiologieInfermier.service";
import {InfermierMedecinService} from "./InfermierMedecin.service";
import {MedecinMedecinService} from "./MedecinMedecin.service";
import {EpreuveMedecinService} from "../dossie/EpreuveMedecin.service";
import {SyntheseMedicaleMedecinService} from "../rappor/SyntheseMedicaleMedecin.service";
import {PatientMedecinService} from "../patient/PatientMedecin.service";
import {DiagnosticMedecinService} from "../rappor/DiagnosticMedecin.service";
import {AnalyseMedicaleMedecinService} from "../dossie/AnalyseMedicaleMedecin.service";
import {TypeImageMedecinService} from "../dossie/TypeImageMedecin.service";
import {AntecedentMedecinService} from "../dossie/AntecedentMedecin.service";
import {PatientContactMedecinService} from "../patient/PatientContactMedecin.service";
import {PatientContactDto} from "../../../model/patient/PatientContact.model";
import {AntecedentDto} from "../../../model/dossie/Antecedent.model";
import {OrdonnanceMedecinService} from "../gestio/OrdonnanceMedecin.service";
import {OrdonnanceDto} from "../../../model/gestio/Ordonnance.model";
import {CertificatDto} from "../../../model/gestio/Certificat.model";
import {CertificatMedecinService} from "../gestio/CertificatMedecin.service";
import {AnalyseMedicaleDto} from "../../../model/dossie/AnalyseMedicale.model";
import {RadiologieDto} from "../../../model/dossie/Radiologie.model";
import {FichePatientDto} from "../../../model/dossie/FichePatient.model";
import {SyntheseMedicaleDto} from "../../../model/rappor/SyntheseMedicale.model";
import {DiagnosticDto} from "../../../model/rappor/Diagnostic.model";



@Injectable({
    providedIn: 'root'
})
export class DossierService {
    private _showDossierDetails:boolean=false;
    private _actualPationt:PatientDto;
    private _consultations:ConsultationDto[];
    private _contacts:PatientContactDto[];
    private _antecedents:AntecedentDto[];
    private _ordonances:OrdonnanceDto[];
    private _certificats:CertificatDto[];
    private _analyses:AnalyseMedicaleDto[];
    private _radiologies:RadiologieDto[];
    private _fichePatients:FichePatientDto[];
    private _actualConsultation:ConsultationDto;
    private _syntheses:SyntheseMedicaleDto[];
    private _diagnostics:DiagnosticDto[];





    constructor(private certificatService:CertificatMedecinService,private consultationService:ConsultationMedecinService,private ordonnanceService :OrdonnanceMedecinService , private fichePatientService: FichePatientMedecinService, private radiologieService: RadiologieInfermierService, private infermierService: InfermierMedecinService, private medecinService: MedecinMedecinService, private epreuveService: EpreuveMedecinService, private syntheseMedicaleService: SyntheseMedicaleMedecinService, private patientService: PatientMedecinService, private diagnosticService: DiagnosticMedecinService, private analyseMedicaleService: AnalyseMedicaleMedecinService, private typeImageService: TypeImageMedecinService, private antecedentService: AntecedentMedecinService,private contactService:PatientContactMedecinService) {
    }


    public findAllConsultation(){
        this.consultationService.findByPationNumDossier(this.actualPationt.numDossier).subscribe(
            res=>{
                this._consultations=res;
                console.log(this._consultations);
            }
        )
    }

    public findAllDiagnostics(){
        this.diagnosticService.findByPationNumDossier(this.actualPationt.numDossier).subscribe(
            res=>{
                this._diagnostics=res;
                console.log('Diagnostic: '+this._diagnostics);
            }
        )
    }

    public findAllSyntheses(){
        this.syntheseMedicaleService.findByPationNumDossier(this.actualPationt.numDossier).subscribe(
            res=>{
                this._syntheses=res;
                console.log('Synthese: '+this._syntheses);
            }
        )
    }



    public findAllContacts(){
        this.contactService.findByPationNumDossier(this.actualPationt.numDossier).subscribe(
            res=>{
                this._contacts=res;
                console.log(this._contacts);
            }
        )
    }

    public findAllAnticidents(){
        this.antecedentService.findByPationNumDossier(this.actualPationt.numDossier).subscribe(
            res=>{
                console.log(res)
                this._antecedents=res;
            }
        )
    }

    public findAllOrdonnance(){
        this.ordonnanceService.findByPationNumDossier(this.actualPationt.numDossier).subscribe(
            res=>{
                console.log(res)
                this._ordonances=res;
                console.log(this._ordonances);
            }
        )
    }

    public findAllCertificat(){
        this.certificatService.findByPationNumDossier(this.actualPationt.numDossier).subscribe(
            res=>{
                this._certificats=res;
                console.log(this._certificats);
            }
        )
    }

    public findAllAnalyses(){
        this.analyseMedicaleService.findByConsultationRef(this.actualConsultation.ref).subscribe(
            res=>{
                console.log(res)
                this._analyses=res;
                console.log('Analyse: '+this._analyses);
            }
        )
    }

    public findAllFichePatient(){
        this.fichePatientService.findByConsultationRef(this.actualConsultation.ref).subscribe(
            res=>{
                console.log(res)
                this._fichePatients=res;
                console.log('Fiche: '+this._fichePatients);
            }
        )
    }

    public findAllRadiologie(){
        this.radiologieService.findByConsultationRef(this.actualConsultation.ref).subscribe(
            res=>{
                console.log(res)
                this._radiologies=res;
                console.log('Radiologie: '+this._radiologies);
            }
        )
    }




    get actualPationt(): PatientDto {
        return this._actualPationt;
    }

    set actualPationt(value: PatientDto) {
        this._actualPationt = value;
    }

    get showDossierDetails(): boolean {
        return this._showDossierDetails;
    }

    set showDossierDetails(value: boolean) {
        this._showDossierDetails = value;
    }

    get consultations(): ConsultationDto[] {
        return this._consultations;
    }

    set consultations(value: ConsultationDto[]) {
        this._consultations = value;
    }

    get contacts(): PatientContactDto[] {
        return this._contacts;
    }

    set contacts(value: PatientContactDto[]) {
        this._contacts = value;
    }

    get antecedents(): AntecedentDto[] {
        return this._antecedents;
    }

    set antecedents(value: AntecedentDto[]) {
        this._antecedents = value;
    }

    get ordonances(): OrdonnanceDto[] {
        return this._ordonances;
    }

    set ordonances(value: OrdonnanceDto[]) {
        this._ordonances = value;
    }

    get certificats(): CertificatDto[] {
        return this._certificats;
    }

    set certificats(value: CertificatDto[]) {
        this._certificats = value;
    }

    get analyses(): AnalyseMedicaleDto[] {
        return this._analyses;
    }

    set analyses(value: AnalyseMedicaleDto[]) {
        this._analyses = value;
    }

    get radiologies(): RadiologieDto[] {
        return this._radiologies;
    }

    set radiologies(value: RadiologieDto[]) {
        this._radiologies = value;
    }

    get fichePatients(): FichePatientDto[] {
        return this._fichePatients;
    }

    set fichePatients(value: FichePatientDto[]) {
        this._fichePatients = value;
    }

    get actualConsultation(): ConsultationDto {
        return this._actualConsultation;
    }

    set actualConsultation(value: ConsultationDto) {
        this._actualConsultation = value;
    }


    get syntheses(): SyntheseMedicaleDto[] {
        return this._syntheses;
    }

    set syntheses(value: SyntheseMedicaleDto[]) {
        this._syntheses = value;
    }

    get diagnostics(): DiagnosticDto[] {
        return this._diagnostics;
    }

    set diagnostics(value: DiagnosticDto[]) {
        this._diagnostics = value;
    }

}
