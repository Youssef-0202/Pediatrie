import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {SexeDto} from './Sexe.model';


export class InfermierDto extends BaseDto{

    public ref: string;

    public email: string;

    public nom: string;

    public prenom: string;

   public dateNaissance: Date;

    public telephone: string;

   public credentialsNonExpired: null | boolean;

   public enabled: null | boolean;

   public accountNonExpired: null | boolean;

   public accountNonLocked: null | boolean;

   public passwordChanged: null | boolean;
   public createdAt: Date;

    public username: string;

    public password: string;

    public photoProfil:String;



    public sexe: SexeDto ;


    constructor() {
        super();
        this.photoProfil='';
        this.ref = '';
        this.email = '';
        this.nom = '';
        this.prenom = '';
        this.dateNaissance = null;
        this.createdAt = null;
        this.telephone = '';
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
