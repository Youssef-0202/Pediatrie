import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {TraitementCriteria} from './TraitementCriteria.model';
import {ConsultationCriteria} from '../consultatio/ConsultationCriteria.model';

export class OrdonnanceCriteria  extends BaseCriteria  {

    public id: number;
    public ref: string;
    public refLike: string;
    public nomHospital: string;
    public nomHospitalLike: string;
    public dateOrdonnance: Date;
    public dateOrdonnanceFrom: Date;
    public dateOrdonnanceTo: Date;
    public adresseHospitla: string;
    public adresseHospitlaLike: string;
    public signature: string;
    public signatureLike: string;
  public traitement: TraitementCriteria ;
  public traitements: Array<TraitementCriteria> ;
  public consultatuin: ConsultationCriteria ;
  public consultatuins: Array<ConsultationCriteria> ;

}
