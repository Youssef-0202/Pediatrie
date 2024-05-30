import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {RelationCriteria} from './RelationCriteria.model';
import {PatientCriteria} from './PatientCriteria.model';

export class PatientContactCriteria  extends BaseCriteria  {

    public id: number;
    public cin: string;
    public cinLike: string;
    public email: string;
    public emailLike: string;
    public nom: string;
    public nomLike: string;
    public prenom: string;
    public prenomLike: string;
    public telephone: string;
    public telephoneLike: string;
    public adresse: string;
    public adresseLike: string;

}
