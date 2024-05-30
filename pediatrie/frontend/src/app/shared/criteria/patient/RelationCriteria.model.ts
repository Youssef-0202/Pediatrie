import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class RelationCriteria  extends BaseCriteria  {

    public id: number;
    public ref: string;
    public refLike: string;
    public libelle: string;
    public libelleLike: string;

}
