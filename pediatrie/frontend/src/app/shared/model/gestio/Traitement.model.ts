import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {OrdonnanceDto} from './Ordonnance.model';
import {ConsultationDto} from '../consultatio/Consultation.model';
import {MedicamentDto} from './Medicament.model';

export class TraitementDto extends BaseDto{

    public ref: string;

    public imageOrdonnance: string;

    public ordonnance: OrdonnanceDto ;
    public consultatuin: ConsultationDto ;
     public medicaments: Array<MedicamentDto>;
    

    constructor() {
        super();

        this.ref = '';
        this.imageOrdonnance = '';
        this.consultatuin = new ConsultationDto() ;
        this.medicaments = new Array<MedicamentDto>();

        }

}
