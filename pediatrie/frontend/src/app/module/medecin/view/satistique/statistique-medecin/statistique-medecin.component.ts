import {Component, OnInit} from '@angular/core';
import {MedecinAdminService} from "../../../../../shared/service/admin/commun/MedecinAdmin.service";
import {InfermierAdminService} from "../../../../../shared/service/admin/commun/InfermierAdmin.service";
import {ConsultationAdminService} from "../../../../../shared/service/admin/consultatio/ConsultationAdmin.service";
import {PatientAdminService} from "../../../../../shared/service/admin/patient/PatientAdmin.service";
import {MedecinMedecinService} from "../../../../../shared/service/medecin/commun/MedecinMedecin.service";
import {InfermierMedecinService} from "../../../../../shared/service/medecin/commun/InfermierMedecin.service";
import {
    ConsultationMedecinService
} from "../../../../../shared/service/medecin/consultatio/ConsultationMedecin.service";
import {PatientMedecinService} from "../../../../../shared/service/medecin/patient/PatientMedecin.service";
import {InfermierDto} from "../../../../../shared/model/commun/Infermier.model";
import {MedecinDto} from "../../../../../shared/model/commun/Medecin.model";
import {PatientDto} from "../../../../../shared/model/patient/Patient.model";
import {ConsultationDto} from "../../../../../shared/model/consultatio/Consultation.model";

@Component({
  selector: 'app-statistique-medecin',
  templateUrl: './statistique-medecin.component.html',
  styleUrls: ['./statistique-medecin.component.scss']
})
export class StatistiqueMedecinComponent implements OnInit{
     chartData: any;
     chartOptions: any;
     data1: any;
     options1: any;
     data2: any;
     options2: any;
     data3: any;
     options3: any;
     documentStyle = getComputedStyle(document.documentElement);
     textColor = this.documentStyle.getPropertyValue('--text-color');
     textColorSecondary = this.documentStyle.getPropertyValue('--text-color-secondary');
     surfaceBorder = this.documentStyle.getPropertyValue('--surface-border');
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
    protected type1: number=0;
    protected type2: number=0;
    protected type3: number=0;
    lastes: [Array<PatientDto>] = [this.lastPationts];


    constructor(private medecinMedecinService :MedecinMedecinService,private infermierMedecinService :InfermierMedecinService,private consultationMedecinService :ConsultationMedecinService,private patientMedecinService:PatientMedecinService) {
    }

    cercle() {
        console.log(this.lastes)
        this.data2 = {
            labels: ['Medecin', 'Infermier', 'Patient', 'Consultation'],
            datasets: [
                {
                    data: [this.nbrMedecin, this.nbrInfernier, this.nbrPatient, this.nbrConsultation],
                    backgroundColor: [this.documentStyle.getPropertyValue('--blue-500'), this.documentStyle.getPropertyValue('--yellow-500'), this.documentStyle.getPropertyValue('--green-500'), this.documentStyle.getPropertyValue('--red-400')],
                    hoverBackgroundColor: [this.documentStyle.getPropertyValue('--blue-400'), this.documentStyle.getPropertyValue('--yellow-400'), this.documentStyle.getPropertyValue('--green-400'), this.documentStyle.getPropertyValue('--red-300')]
                }
            ]
        };


        this.options2 = {
            cutout: '60%',
            plugins: {
                legend: {
                    labels: {
                        color: this.textColor
                    }
                }
            }
        };
    }


    ngOnInit(){
        this.infermierMedecinService.findAll().subscribe(
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

        this.medecinMedecinService.findAll().subscribe(
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

        this.consultationMedecinService.findAll().subscribe(
            res=>{
                this.consultations=res;
                this.nbrConsultation=res.length;
                this.type1=(res?.filter(e=>{
                    return e.typeConsultation=="Examens cliniques pédiatriques"
                }).length/this.nbrConsultation)*100;
                this.type2=(res?.filter(e=>{
                    return e.typeConsultation=="Vaccination"
                }).length/this.nbrConsultation)*100;
                this.type3=(res?.filter(e=>{
                    return e.typeConsultation=="Bilan de santé néonatale"
                }).length/this.nbrConsultation)*100;
                this.lastMedecins?.push(this.medecins[this.medecins.length - 1])
                this.lastMedecins?.push(this.medecins[this.medecins.length - 2])
                this.consultationDay=res?.filter(e=> {
                    const dateConsultation = new Date(e.dateConsultation);
                    return  dateConsultation? dateConsultation.getFullYear() == this.today.getFullYear() && dateConsultation.getMonth() == this.today.getMonth() && dateConsultation.getDate() == this.today.getDate() : false;
                }).length
            }
        );

        this.patientMedecinService.findAll().subscribe(
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



        this.chartData = {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [
                {
                    label: 'Consultations',
                    data: [65, 59, 80, 81, 56, 66, 70],
                    fill: false,
                    backgroundColor: this.documentStyle.getPropertyValue('--bluegray-700'),
                    borderColor: this.documentStyle.getPropertyValue('--bluegray-700'),
                    tension: .4
                },

            ]
        };



        this.chartOptions = {
            plugins: {
                legend: {
                    labels: {
                        color: this.textColor
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: this.textColorSecondary
                    },
                    grid: {
                        color: this.surfaceBorder,
                        drawBorder: false
                    }
                },
                y: {
                    ticks: {
                        color: this.textColorSecondary
                    },
                    grid: {
                        color: this.surfaceBorder,
                        drawBorder: false
                    }
                }
            }
        };

        this.data1 = {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [
                {
                    label: 'Homme',
                    backgroundColor: this.documentStyle.getPropertyValue('--blue-500'),
                    borderColor: this.documentStyle.getPropertyValue('--blue-500'),
                    data: [65, 59, 80, 81, 56, 55, 40]
                },
                {
                    label: 'Femme',
                    backgroundColor: this.documentStyle.getPropertyValue('--pink-500'),
                    borderColor: this.documentStyle.getPropertyValue('--pink-500'),
                    data: [28, 48, 40, 19, 86, 27, 90]
                }
            ]
        };

        this.options1 = {
            maintainAspectRatio: false,
            aspectRatio: 0.8,
            plugins: {
                legend: {
                    labels: {
                        color: this.textColor
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: this.textColorSecondary,
                        font: {
                            weight: 500
                        }
                    },
                    grid: {
                        color: this.surfaceBorder,
                        drawBorder: false
                    }
                },
                y: {
                    ticks: {
                        color: this.textColorSecondary
                    },
                    grid: {
                        color: this.surfaceBorder,
                        drawBorder: false
                    }
                }

            }
        };

        this.data2 = {
            labels: ['A', 'B', 'C','D'],
            datasets: [
                {
                    data: [300, 50, 100,90],
                    backgroundColor: [this.documentStyle.getPropertyValue('--blue-500'), this.documentStyle.getPropertyValue('--yellow-500'), this.documentStyle.getPropertyValue('--green-500'), this.documentStyle.getPropertyValue('--red-500')],
                    hoverBackgroundColor: [this.documentStyle.getPropertyValue('--blue-400'), this.documentStyle.getPropertyValue('--yellow-400'), this.documentStyle.getPropertyValue('--green-400'), this.documentStyle.getPropertyValue('--red-500')]
                }
            ]
        };


        this.options2 = {
            cutout: '60%',
            plugins: {
                legend: {
                    labels: {
                        color: this.textColor
                    }
                }
            }
        };

        this.data3 = {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [
                {
                    label: 'Homme',
                    data: [65, 59, 80, 81, 56, 55, 40],
                    fill: false,
                    borderColor: this.documentStyle.getPropertyValue('--blue-500'),
                    tension: 0.4
                },
                {
                    label: 'Femme',
                    data: [28, 48, 40, 19, 86, 27, 90],
                    fill: false,
                    borderColor: this.documentStyle.getPropertyValue('--pink-500'),
                    tension: 0.4
                }
            ]
        };

        this.options3 = {
            maintainAspectRatio: false,
            aspectRatio: 0.6,
            plugins: {
                legend: {
                    labels: {
                        color: this.textColor
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: this.textColorSecondary
                    },
                    grid: {
                        color: this.surfaceBorder,
                        drawBorder: false
                    }
                },
                y: {
                    ticks: {
                        color: this.textColorSecondary
                    },
                    grid: {
                        color: this.surfaceBorder,
                        drawBorder: false
                    }
                }
            }
        };

        this.lineData = {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [
                {
                    label: 'Homme',
                    data: [65, 59, 80, 81, 56, 55, 40],
                    fill: false,
                    backgroundColor: this.documentStyle.getPropertyValue('--primary-500'),
                    borderColor: this.documentStyle.getPropertyValue('--primary-500'),
                    tension: .4
                },
                {
                    label: 'Femme',
                    data: [28, 48, 40, 19, 86, 27, 90],
                    fill: false,
                    backgroundColor: this.documentStyle.getPropertyValue('--primary-200'),
                    borderColor: this.documentStyle.getPropertyValue('--primary-200'),
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
                        color: this.textColorSecondary
                    },
                    grid: {
                        color: this.surfaceBorder,
                        drawBorder: false
                    }
                },
                y: {
                    ticks: {
                        color: this.textColorSecondary
                    },
                    grid: {
                        color: this.surfaceBorder,
                        drawBorder: false
                    }
                },
            }
        };

    }

}
