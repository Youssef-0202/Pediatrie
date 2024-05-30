import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {ConsultationDto} from '../consultatio/Consultation.model';
import {EpreuveDto} from './Epreuve.model';

export class AnalyseMedicaleDto extends BaseDto{

    public ref: string;

   public dateAnalyseMedicale: Date;

    public valeur: null | number;

    public valeurRang: string;

    public epreuve: EpreuveDto ;
    public consultation: ConsultationDto ;
    

    constructor() {
        super();

        this.ref = '';
        this.dateAnalyseMedicale = null;
        this.valeur = null;
        this.valeurRang = '';
        this.epreuve = new EpreuveDto() ;
        this.consultation = new ConsultationDto() ;

        }

}
