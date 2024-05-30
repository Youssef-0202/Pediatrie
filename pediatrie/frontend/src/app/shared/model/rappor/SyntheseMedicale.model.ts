import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {ConsultationDto} from '../consultatio/Consultation.model';

export class SyntheseMedicaleDto extends BaseDto{

    public ref: string;

   public dateSyntheseMedicale: Date;

    public description: string;

    public consultation: ConsultationDto ;
    

    constructor() {
        super();

        this.ref = '';
        this.dateSyntheseMedicale = null;
        this.description = '';
        this.consultation = new ConsultationDto() ;

        }

}
