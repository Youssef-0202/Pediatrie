import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {TraitementDto} from './Traitement.model';

export class MedicamentDto extends BaseDto{

    public ref: string;

    public sointProduit: string;

    public duree: string;

    public consigne: string;

    public traitement: TraitementDto ;
    

    constructor() {
        super();

        this.ref = '';
        this.sointProduit = '';
        this.duree = '';
        this.consigne = '';
        this.traitement = new TraitementDto() ;

        }

}
