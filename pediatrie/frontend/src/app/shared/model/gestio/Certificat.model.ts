import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {ConsultationDto} from '../consultatio/Consultation.model';
import {HttpClient} from "@angular/common/http";

export class CertificatDto extends BaseDto{

    public ref: string;

   public dateDebut: Date;

   public dateFin: Date;

    public description: string;

    public nbrJour: null | number;

    public consultatuin: ConsultationDto ;


    constructor() {
        super();

        this.ref = '';
        this.dateDebut = null;
        this.dateFin = null;
        this.description = '';
        this.nbrJour = null;

        }




}
