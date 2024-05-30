import {Component, OnInit} from "@angular/core";
import {
    ConsultationInfermierService
} from "../../../../../../../shared/service/infermier/consultatio/ConsultationInfermier.service";
import {ConsultationDto} from "../../../../../../../shared/model/consultatio/Consultation.model";
import {PatientInfermierService} from "../../../../../../../shared/service/infermier/patient/PatientInfermier.service";


@Component({
    selector: 'app-patient-consultation-view-infermier',
    templateUrl: './patient-consultation-view-infermier.component.html'
})
export class PatientConsultationViewInfermierComponent implements OnInit{

    constructor(private service :ConsultationInfermierService,private patientInfermierService :PatientInfermierService) {
    }
    ngOnInit(): void {

    }


    get viewDialog2(): boolean {
        return this.patientInfermierService.viewDialog2;
    }

    set viewDialog2(value: boolean) {
        this.patientInfermierService.viewDialog2 = value;
    }


    get consultationPatient(): ConsultationDto {
        return this.service.consultationPatient;
    }

    set consultationPatient(value: ConsultationDto) {
        this.service.consultationPatient = value;
    }

}
