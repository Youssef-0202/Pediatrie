import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class RelationDto extends BaseDto{

    public ref: string;

    public libelle: string;

    

    constructor() {
        super();

        this.ref = '';
        this.libelle = '';

        }

}
