import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {PatientContactDto} from './PatientContact.model';
import {SexeDto} from '../commun/Sexe.model';

export class PatientDto extends BaseDto{

    public numDossier: string;

    public nom: string;

    public prenom: string;

   public dateNaissance: Date;

    public photoProfil: string;

    public createdAt:Date;

    public sexe: SexeDto ;
     public patientContact: Array<PatientContactDto>;


    constructor() {
        super();

        this.numDossier = '';
        this.nom = '';
        this.prenom = '';
        this.dateNaissance = null;
        this.createdAt = null;
        this.photoProfil = '';
        this.sexe = new SexeDto() ;
        this.patientContact = new Array<PatientContactDto>();

        }

}
