import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {ConsultationCriteria} from '../consultatio/ConsultationCriteria.model';

export class DiagnosticCriteria  extends BaseCriteria  {

    public id: number;
    public ref: string;
    public refLike: string;
    public dateDiagnostic: Date;
    public dateDiagnosticFrom: Date;
    public dateDiagnosticTo: Date;
    public description: string;
    public descriptionLike: string;
  public consultation: ConsultationCriteria ;
  public consultations: Array<ConsultationCriteria> ;

}
