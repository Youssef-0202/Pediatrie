import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {FichePatientDto} from '../dossie/FichePatient.model';
import {RadiologieDto} from '../dossie/Radiologie.model';
import {InfermierDto} from '../commun/Infermier.model';
import {MedecinDto} from '../commun/Medecin.model';
import {SyntheseMedicaleDto} from '../rappor/SyntheseMedicale.model';
import {PatientDto} from '../patient/Patient.model';
import {DiagnosticDto} from '../rappor/Diagnostic.model';
import {AnalyseMedicaleDto} from '../dossie/AnalyseMedicale.model';

export class ConsultationDto extends BaseDto{

    public ref: string;

   public dateConsultation: Date;

   public heureConsultation: Date;

    public typeConsultation: string;

    public medecin: MedecinDto ;
    public infermier: InfermierDto ;
    public patient: PatientDto ;
     public analyseMedicale: Array<AnalyseMedicaleDto>;
     public fichePatient: Array<FichePatientDto>;
     public radiologie: Array<RadiologieDto>;
     public diagnostic: Array<DiagnosticDto>;
     public syntheseMedicale: Array<SyntheseMedicaleDto>;
    

    constructor() {
        super();

        this.ref = '';
        this.dateConsultation = null;
        this.heureConsultation = null;
        this.typeConsultation = '';
        this.infermier = new InfermierDto() ;
        this.patient = new PatientDto() ;
        this.analyseMedicale = new Array<AnalyseMedicaleDto>();
        this.fichePatient = new Array<FichePatientDto>();
        this.radiologie = new Array<RadiologieDto>();
        this.diagnostic = new Array<DiagnosticDto>();
        this.syntheseMedicale = new Array<SyntheseMedicaleDto>();

        }

}
