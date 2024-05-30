import { Component } from '@angular/core';
import {MedecinAdminService} from "../../../../shared/service/admin/commun/MedecinAdmin.service";
import {InfermierMedecinService} from "../../../../shared/service/medecin/commun/InfermierMedecin.service";
import {ConsultationMedecinService} from "../../../../shared/service/medecin/consultatio/ConsultationMedecin.service";
import {MedecinDto} from "../../../../shared/model/commun/Medecin.model";
import {InfermierDto} from "../../../../shared/model/commun/Infermier.model";
import {PatientDto} from "../../../../shared/model/patient/Patient.model";
import {ConsultationDto} from "../../../../shared/model/consultatio/Consultation.model";
import {InfermierAdminService} from "../../../../shared/service/admin/commun/InfermierAdmin.service";
import {ConsultationAdminService} from "../../../../shared/service/admin/consultatio/ConsultationAdmin.service";
import {ProfilAdminService} from "../../../../shared/service/admin/profil/ProfilAdmin.service";
import {PatientAdminService} from "../../../../shared/service/admin/patient/PatientAdmin.service";

@Component({
  selector: 'app-dashbord-admin',
  templateUrl: './dashbord.component.html',
  styleUrls: ['./dashbord.component.scss']
})
export class DashbordComponent {
    data: any;
    options: any;
    products!: number[];
    lineData: any;
    lineOptions: any;
    today = new Date();
    protected infermiers: Array<InfermierDto>;
    protected lastInfermiers: Array<InfermierDto>=[];
    protected medecins: Array<MedecinDto>;
    protected lastMedecins: Array<MedecinDto>=[];
    protected pationts: Array<PatientDto>;
    protected lastPationts: Array<PatientDto>=[];
    protected consultations: Array<ConsultationDto>;
    protected medecinsMonth: any;
    protected infermiersMonth: any;
    protected patientMonth: number | undefined=0;
    protected consultationDay: number | undefined=0;
    protected nbrMedecin:number=1;
    protected nbrInfernier:number=1;
    protected nbrPatient:number=1;
    protected nbrConsultation: number=1;
    protected documentStyle = getComputedStyle(document.documentElement);
    protected textColor = this.documentStyle.getPropertyValue('--text-color');
    protected surfaceBorder = this.documentStyle.getPropertyValue('--surface-border');
        lastes: [Array<PatientDto>] = [this.lastPationts];


    constructor(private medecinAdminService :MedecinAdminService,private infermierAdminService :InfermierAdminService,private consultationAdminService :ConsultationAdminService,private pationAdminService:PatientAdminService) {
    }

    ngOnInit() {
        this.infermierAdminService.findAll().subscribe(
            res=>{
                this.infermiers=res;
                this.nbrInfernier=res.length;
                this.lastInfermiers?.push(this.infermiers[this.infermiers.length-1])
                this.lastInfermiers?.push(this.infermiers[this.infermiers.length-2])
                this.infermiersMonth=res?.filter(e=> {
                   return e.createdAt ?  e.createdAt[0] == this.today.getFullYear() && e.createdAt[1] == this.today.getMonth()+1 : false;
                }).length
            }
            );

        this.medecinAdminService.findAll().subscribe(
            res=>{
                this.medecins=res;
                this.nbrMedecin=res.length;
                this.lastMedecins?.push(this.medecins[this.medecins.length-1])
                this.lastMedecins?.push(this.medecins[this.medecins.length-2])
                this.medecinsMonth=res?.filter(e=> {
                    return e.createdAt? e.createdAt[0] == this.today.getFullYear() && e.createdAt[1] == this.today.getMonth()+1 :false;
                }).length
            }
        );

        this.consultationAdminService.findAll().subscribe(
            res=>{
                this.consultations=res;
                this.nbrConsultation=res.length;
                this.lastMedecins?.push(this.medecins[this.medecins.length - 1])
                this.lastMedecins?.push(this.medecins[this.medecins.length - 2])
                this.consultationDay=res?.filter(e=> {
                    const dateConsultation = new Date(e.dateConsultation);
                    return  dateConsultation? dateConsultation.getFullYear() == this.today.getFullYear() && dateConsultation.getMonth() == this.today.getMonth() && dateConsultation.getDate() == this.today.getDate() : false;
                }).length
            }
        );

        this.pationAdminService.findAll().subscribe(
            res=>{
                this.pationts = res;
                this.lastPationts?.push(this.pationts[this.pationts.length - 1])
                this.lastPationts?.push(this.pationts[this.pationts.length - 2])
                console.log(this.lastPationts)
                this.nbrPatient = res.length;
                this.cercle();
                this.patientMonth=res?.filter(e=> {
                    return  e.createdAt? e.createdAt[0] == this.today.getFullYear() && e.createdAt[1] == this.today.getMonth()+1 : false;
                }).length
            }
        );
    }

    cercle(){
        console.log(this.lastes)
        this.data = {
            labels: ['Medecin', 'Infirmier', 'Patient','Consultation'],
            datasets: [
                {
                    data: [this.nbrMedecin, this.nbrInfernier, this.nbrPatient,this.nbrConsultation],
                    backgroundColor: [this.documentStyle.getPropertyValue('--blue-500'), this.documentStyle.getPropertyValue('--yellow-500'), this.documentStyle.getPropertyValue('--green-500'), this.documentStyle.getPropertyValue('--red-400')],
                    hoverBackgroundColor: [this.documentStyle.getPropertyValue('--blue-400'), this.documentStyle.getPropertyValue('--yellow-400'), this.documentStyle.getPropertyValue('--green-400'), this.documentStyle.getPropertyValue('--red-300')]
                }
            ]
        };


        this.options = {
            cutout: '60%',
            plugins: {
                legend: {
                    labels: {
                        color: this.textColor
                    }
                }
            }
        };

        this.lineData = {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [
                {
                    label: 'Les Hommes',
                    data: [65, 59, 80, 81, 56, 55, 40],
                    fill: false,
                    backgroundColor: this.documentStyle.getPropertyValue('--primary-500'),
                    borderColor: this.documentStyle.getPropertyValue('--primary-500'),
                    tension: .4
                },
                {
                    label: 'Les Femmes',
                    data: [28, 48, 40, 19, 86, 27, 90],
                    fill: false,
                    backgroundColor: this.documentStyle.getPropertyValue('--red-200'),
                    borderColor: this.documentStyle.getPropertyValue('--red-200'),
                    tension: .4
                }
            ]
        };

        this.lineOptions = {
            plugins: {
                legend: {
                    labels: {
                        fontColor: this.textColor
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color:  this.documentStyle.getPropertyValue('--text-color-secondary')
                    },
                    grid: {
                        color: this.surfaceBorder,
                        drawBorder: false
                    }
                },
                y: {
                    ticks: {
                        color:  this.documentStyle.getPropertyValue('--text-color-secondary')
                    },
                    grid: {
                        color: this.surfaceBorder,
                        drawBorder: false
                    }
                },
            }
        };
    }

    protected readonly console = console;
}
