import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {ConsultationDto} from '../consultatio/Consultation.model';
import {AntecedentDto} from './Antecedent.model';

export class FichePatientDto extends BaseDto{

    public ref: string;

   public dateFichePatient: Date;

    public antecedentFamillial: string;

    public histoireMaladie: string;

    public examenFichePatient: string;

    public conclusionFichePatient: string;

    public antecedent: AntecedentDto ;
    public consultation: ConsultationDto ;
    

    constructor() {
        super();

        this.ref = '';
        this.dateFichePatient = null;
        this.antecedentFamillial = '';
        this.histoireMaladie = '';
        this.examenFichePatient = '';
        this.conclusionFichePatient = '';
        this.antecedent = new AntecedentDto() ;

        }

}
