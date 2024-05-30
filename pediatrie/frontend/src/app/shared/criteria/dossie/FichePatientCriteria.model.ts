import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {ConsultationCriteria} from '../consultatio/ConsultationCriteria.model';
import {AntecedentCriteria} from './AntecedentCriteria.model';

export class FichePatientCriteria  extends BaseCriteria  {

    public id: number;
    public ref: string;
    public refLike: string;
    public dateFichePatient: Date;
    public dateFichePatientFrom: Date;
    public dateFichePatientTo: Date;
    public antecedentFamillial: string;
    public antecedentFamillialLike: string;
    public histoireMaladie: string;
    public histoireMaladieLike: string;
    public examenFichePatient: string;
    public examenFichePatientLike: string;
    public conclusionFichePatient: string;
    public conclusionFichePatientLike: string;
  public antecedent: AntecedentCriteria ;
  public antecedents: Array<AntecedentCriteria> ;

}
