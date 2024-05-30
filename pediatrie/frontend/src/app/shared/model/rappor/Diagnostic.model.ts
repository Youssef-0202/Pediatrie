import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {ConsultationDto} from '../consultatio/Consultation.model';

export class DiagnosticDto extends BaseDto{

    public ref: string;

   public dateDiagnostic: Date;

    public description: string;

    public consultation: ConsultationDto ;
    

    constructor() {
        super();

        this.ref = '';
        this.dateDiagnostic = null;
        this.description = '';
        this.consultation = new ConsultationDto() ;

        }

}
