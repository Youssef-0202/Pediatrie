import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {RelationDto} from './Relation.model';
import {PatientDto} from './Patient.model';

export class PatientContactDto extends BaseDto{

    public cin: string;

    public email: string;

    public nom: string;

    public prenom: string;

    public telephone: string;

    public adresse: string;

    public relation: RelationDto ;
    public patient: PatientDto ;
    

    constructor() {
        super();

        this.cin = '';
        this.email = '';
        this.nom = '';
        this.prenom = '';
        this.telephone = '';
        this.adresse = '';

        }

}
