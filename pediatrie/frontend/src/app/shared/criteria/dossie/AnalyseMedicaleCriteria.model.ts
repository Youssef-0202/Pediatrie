import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {ConsultationCriteria} from '../consultatio/ConsultationCriteria.model';
import {EpreuveCriteria} from './EpreuveCriteria.model';

export class AnalyseMedicaleCriteria  extends BaseCriteria  {

    public id: number;
    public ref: string;
    public refLike: string;
    public dateAnalyseMedicale: Date;
    public dateAnalyseMedicaleFrom: Date;
    public dateAnalyseMedicaleTo: Date;
     public valeur: number;
     public valeurMin: number;
     public valeurMax: number;
    public valeurRang: string;
    public valeurRangLike: string;
  public epreuve: EpreuveCriteria ;
  public epreuves: Array<EpreuveCriteria> ;
  public consultation: ConsultationCriteria ;
  public consultations: Array<ConsultationCriteria> ;

}
