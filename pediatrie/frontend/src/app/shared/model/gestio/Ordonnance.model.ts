import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {TraitementDto} from './Traitement.model';
import {ConsultationDto} from '../consultatio/Consultation.model';

export class OrdonnanceDto extends BaseDto{

    public ref: string;

    public nomHospital: string;

   public dateOrdonnance: Date;

    public adresseHospitla: string;

    public signature: string;

    public traitement: TraitementDto ;
    public consultatuin: ConsultationDto ;
    

    constructor() {
        super();

        this.ref = '';
        this.nomHospital = '';
        this.dateOrdonnance = null;
        this.adresseHospitla = '';
        this.signature = '';
        this.traitement = new TraitementDto() ;
        this.consultatuin = new ConsultationDto() ;

        }

}
