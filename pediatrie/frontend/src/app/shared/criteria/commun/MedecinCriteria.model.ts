import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {SexeCriteria} from './SexeCriteria.model';

export class MedecinCriteria  extends BaseCriteria  {

    public ref: string;
    public refLike: string;
    public email: string;
    public emailLike: string;
    public nom: string;
    public nomLike: string;
    public prenom: string;
    public prenomLike: string;
    public dateNaissance: Date;
    public dateNaissanceFrom: Date;
    public dateNaissanceTo: Date;
    public telephone: string;
    public telephoneLike: string;
    public photoProfil: string;
    public photoProfilLike: string;
    public speciality: string;
    public specialityLike: string;
    public credentialsNonExpired: null | boolean;
    public enabled: null | boolean;
    public accountNonExpired: null | boolean;
    public accountNonLocked: null | boolean;
    public passwordChanged: null | boolean;
    public username: string;
    public usernameLike: string;
    public password: string;
    public passwordLike: string;
  public sexe: SexeCriteria ;
  public sexes: Array<SexeCriteria> ;

}
