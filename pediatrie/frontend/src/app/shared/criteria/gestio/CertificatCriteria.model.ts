import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {ConsultationCriteria} from '../consultatio/ConsultationCriteria.model';

export class CertificatCriteria  extends BaseCriteria  {

    public id: number;
    public ref: string;
    public refLike: string;
    public dateDebut: Date;
    public dateDebutFrom: Date;
    public dateDebutTo: Date;
    public dateFin: Date;
    public dateFinFrom: Date;
    public dateFinTo: Date;
    public description: string;
    public descriptionLike: string;
     public nbrJour: number;
     public nbrJourMin: number;
     public nbrJourMax: number;

}
