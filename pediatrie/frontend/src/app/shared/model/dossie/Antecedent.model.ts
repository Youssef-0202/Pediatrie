import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {GroupeSanguinDto} from './GroupeSanguin.model';

export class AntecedentDto extends BaseDto{

    public ref: string;

    public allergie: string;

    public etat_psy: string;

    public respiratoire: string;

    public alimentation: string;

    public mouvement: string;

    public sommeil: string;

    public groupeSanguin: GroupeSanguinDto ;
    

    constructor() {
        super();

        this.ref = '';
        this.allergie = '';
        this.etat_psy = '';
        this.respiratoire = '';
        this.alimentation = '';
        this.mouvement = '';
        this.sommeil = '';

        }

}
