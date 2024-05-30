import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {TraitementCriteria} from './TraitementCriteria.model';

export class MedicamentCriteria  extends BaseCriteria  {

    public id: number;
    public ref: string;
    public refLike: string;
    public sointProduit: string;
    public sointProduitLike: string;
    public duree: string;
    public dureeLike: string;
    public consigne: string;
    public consigneLike: string;
  public traitement: TraitementCriteria ;
  public traitements: Array<TraitementCriteria> ;

}
