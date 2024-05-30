import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {FichePatientCriteria} from '../dossie/FichePatientCriteria.model';
import {RadiologieCriteria} from '../dossie/RadiologieCriteria.model';
import {InfermierCriteria} from '../commun/InfermierCriteria.model';
import {MedecinCriteria} from '../commun/MedecinCriteria.model';
import {SyntheseMedicaleCriteria} from '../rappor/SyntheseMedicaleCriteria.model';
import {PatientCriteria} from '../patient/PatientCriteria.model';
import {DiagnosticCriteria} from '../rappor/DiagnosticCriteria.model';
import {AnalyseMedicaleCriteria} from '../dossie/AnalyseMedicaleCriteria.model';

export class ConsultationCriteria  extends BaseCriteria  {

    public id: number;
    public ref: string;
    public refLike: string;
    public dateConsultation: Date;
    public dateConsultationFrom: Date;
    public dateConsultationTo: Date;
    public heureConsultation: Date;
    public heureConsultationFrom: Date;
    public heureConsultationTo: Date;
    public typeConsultation: string;
    public typeConsultationLike: string;
  public infermier: InfermierCriteria ;
  public infermiers: Array<InfermierCriteria> ;
  public patient: PatientCriteria ;
  public patients: Array<PatientCriteria> ;
      public analyseMedicale: Array<AnalyseMedicaleCriteria>;
      public fichePatient: Array<FichePatientCriteria>;
      public radiologie: Array<RadiologieCriteria>;
      public diagnostic: Array<DiagnosticCriteria>;
      public syntheseMedicale: Array<SyntheseMedicaleCriteria>;

}
