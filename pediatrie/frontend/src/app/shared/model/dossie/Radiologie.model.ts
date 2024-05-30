import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {ConsultationDto} from '../consultatio/Consultation.model';
import {TypeImageDto} from './TypeImage.model';

export class RadiologieDto extends BaseDto{

    public ref: string;

   public dateRadiologie: Date;

    public commentaire: string;

    public imageScann: string;

    public consultationDto: ConsultationDto ;
    public typeImage: TypeImageDto ;


    constructor() {
        super();

        this.ref = '';
        this.dateRadiologie = null;
        this.commentaire = '';
        this.imageScann = '';
        this.consultationDto = new ConsultationDto() ;
        this.typeImage = new TypeImageDto() ;

        }

}
