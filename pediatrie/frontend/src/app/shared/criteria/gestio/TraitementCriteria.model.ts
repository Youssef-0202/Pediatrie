import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {OrdonnanceCriteria} from './OrdonnanceCriteria.model';
import {ConsultationCriteria} from '../consultatio/ConsultationCriteria.model';
import {MedicamentCriteria} from './MedicamentCriteria.model';

export class TraitementCriteria  extends BaseCriteria  {

    public id: number;
    public ref: string;
    public refLike: string;
    public imageOrdonnance: string;
    public imageOrdonnanceLike: string;
  public consultatuin: ConsultationCriteria ;
  public consultatuins: Array<ConsultationCriteria> ;
      public medicaments: Array<MedicamentCriteria>;

}
