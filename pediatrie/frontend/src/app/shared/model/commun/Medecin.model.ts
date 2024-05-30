import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {SexeDto} from './Sexe.model';

export class MedecinDto extends BaseDto{

    public ref: string;

    public email: string;

    public nom: string;

    public prenom: string;

   public dateNaissance: Date;

    public telephone: string;

    public photoProfil: string;

    public speciality: string;

   public credentialsNonExpired: null | boolean;

   public enabled: null | boolean;

   public accountNonExpired: null | boolean;

   public accountNonLocked: null | boolean;

   public passwordChanged: null | boolean;

    public username: string;

    public password: string;

    public sexe: SexeDto ;

    public createdAt:Date;


    constructor() {
        super();

        this.ref = '';
        this.email = '';
        this.nom = '';
        this.prenom = '';
        this.dateNaissance = null;
        this.createdAt = null;
        this.telephone = '';
        this.photoProfil = '';
        this.speciality = '';
        this.credentialsNonExpired = null;
        this.enabled = null;
        this.accountNonExpired = null;
        this.accountNonLocked = null;
        this.passwordChanged = null;
        this.username = '';
        this.password = '';
        this.sexe = new SexeDto() ;

        }

}
