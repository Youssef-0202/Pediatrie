import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {ConsultationCriteria} from '../consultatio/ConsultationCriteria.model';
import {TypeImageCriteria} from './TypeImageCriteria.model';

export class RadiologieCriteria  extends BaseCriteria  {

    public id: number;
    public ref: string;
    public refLike: string;
    public dateRadiologie: Date;
    public dateRadiologieFrom: Date;
    public dateRadiologieTo: Date;
    public commentaire: string;
    public commentaireLike: string;
    public imageScann: string;
    public imageScannLike: string;
  public consultation: ConsultationCriteria ;
  public consultations: Array<ConsultationCriteria> ;
  public typeImage: TypeImageCriteria ;
  public typeImages: Array<TypeImageCriteria> ;

}
