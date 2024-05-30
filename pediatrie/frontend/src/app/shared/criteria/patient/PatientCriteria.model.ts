import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {PatientContactCriteria} from './PatientContactCriteria.model';
import {SexeCriteria} from '../commun/SexeCriteria.model';

export class PatientCriteria  extends BaseCriteria  {

    public id: number;
    public numDossier: string;
    public numDossierLike: string;
    public nom: string;
    public nomLike: string;
    public prenom: string;
    public prenomLike: string;
    public dateNaissance: Date;
    public dateNaissanceFrom: Date;
    public dateNaissanceTo: Date;
    public photoProfil: string;
    public photoProfilLike: string;
  public sexe: SexeCriteria ;
  public sexes: Array<SexeCriteria> ;
      public patientContact: Array<PatientContactCriteria>;

}
