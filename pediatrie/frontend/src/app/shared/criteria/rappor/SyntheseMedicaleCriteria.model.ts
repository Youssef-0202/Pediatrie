import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {ConsultationCriteria} from '../consultatio/ConsultationCriteria.model';

export class SyntheseMedicaleCriteria  extends BaseCriteria  {

    public id: number;
    public ref: string;
    public refLike: string;
    public dateSyntheseMedicale: Date;
    public dateSyntheseMedicaleFrom: Date;
    public dateSyntheseMedicaleTo: Date;
    public description: string;
    public descriptionLike: string;
  public consultation: ConsultationCriteria ;
  public consultations: Array<ConsultationCriteria> ;

}
