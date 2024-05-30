import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {GroupeSanguinCriteria} from './GroupeSanguinCriteria.model';

export class AntecedentCriteria  extends BaseCriteria  {

    public id: number;
    public ref: string;
    public refLike: string;
    public allergie: string;
    public allergieLike: string;
    public etat_psy: string;
    public etat_psyLike: string;
    public respiratoire: string;
    public respiratoireLike: string;
    public alimentation: string;
    public alimentationLike: string;
    public mouvement: string;
    public mouvementLike: string;
    public sommeil: string;
    public sommeilLike: string;

}
