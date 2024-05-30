import {Component, OnInit, Renderer2, ViewChild} from '@angular/core';
import {DossierService} from "../../../../shared/service/medecin/commun/Dossier.service";
import {PatientMedecinService} from "../../../../shared/service/medecin/patient/PatientMedecin.service";
import {AuthService} from "../../../../zynerator/security/shared/service/Auth.service";
import {LayoutService} from "../../../../layout/service/app.layout.service";
import {NavigationEnd, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {PatientCriteria} from "../../../../shared/criteria/patient/PatientCriteria.model";
import {PatientDto} from "../../../../shared/model/patient/Patient.model";
import {any} from "codelyzer/util/function";

@Component({
  selector: 'app-dashboard-medecin',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit{
    protected findByCriteriaShow: boolean=false;
    protected cols: any[] = [];
   constructor(private dossierService:DossierService , private patientService:PatientMedecinService, private service:AuthService , public layoutService: LayoutService, public renderer: Renderer2, public router: Router) {
   }

    menuClick = true;
    resetMenu = true;
    overlayMenuOpenSubscription: Subscription;
    currentPath=""
    showStatistic=true;
    @ViewChild(any) dt: any;
    filterTable(value: string) {
        this.dt.filterGlobal(value, 'contains');
    }


    get totalRecords(): number {
        return this._totalRecords;
    }

    set totalRecords(value: number) {
        this._totalRecords = value;
    }

    menuOutsideClickListener: any;

    profileMenuOutsideClickListener: any;
    private _totalRecords = 0;

    ngOnInit() {
        this.searchClick();
        this.router.events.subscribe(event => {
            if (event instanceof NavigationEnd) {
                this.currentPath = event.url;
                console.log(this.currentPath);
            }
        });
    }


    showSearch() {
        this.findByCriteriaShow = !this.findByCriteriaShow;
    }

    public get criteria(): PatientCriteria {
        if (this.patientService.criteria == null) {
            this.patientService.criteria = new PatientCriteria();
        }
        return this.patientService.criteria;
    }

    public set criteria(value: PatientCriteria) {
        this.patientService.criteria = value;
    }

    get item() : PatientDto{
        return this.patientService.item;
    }

    set item(item){
        this.patientService.item=item;
    }

    public get items(): Array<PatientDto> {
        if (this.patientService.items == null) {
            this.patientService.items = new Array<PatientDto>();
        }
        return this.patientService.items;
    }

    public set items(value: Array<PatientDto>) {
        this.patientService.items = value;
    }

    get selections(): Array<PatientDto> {
        return this.patientService.selections;
    }

    set selections(value: Array<PatientDto>) {
        this.patientService.selections = value;
    }

    get showDossierDetails(): boolean {
        return this.dossierService.showDossierDetails;
    }

    set showDossierDetails(value: boolean) {
        this.dossierService.showDossierDetails = value;
    }

    findPaginatedByCriteria() {
        this.patientService.findPaginatedByCriteria(this.criteria).subscribe(paginatedItems => {
            this.items = paginatedItems.list;
            this.totalRecords = paginatedItems.dataSize;
            console.log(this.items)
            /*this.totalRecords = paginatedItems.dataSize;*/
            this.selections = new Array<PatientDto>();
        }, error => console.log(error));
    }

    searchClick() {
        this.patientService.findAll().subscribe(paginatedItems => {
            this.items = paginatedItems;
            this.totalRecords = paginatedItems.length;
            console.log(this.items)
            /*this.totalRecords = paginatedItems.dataSize;*/
            this.selections = new Array<PatientDto>();
        }, error => console.log(error));
    }



    showDossier(pation: PatientDto) {
        this.showDossierDetails=true;
        this.actualPationt=pation;
        this.dossierService.findAllConsultation();
        this.dossierService.findAllContacts()
        this.dossierService.findAllAnticidents()
        this.dossierService.findAllCertificat()
        this.dossierService.findAllOrdonnance()
        this.dossierService.findAllDiagnostics();
        this.dossierService.findAllSyntheses();
    }


    get actualPationt(): PatientDto {
        return this.dossierService.actualPationt;
    }

    set actualPationt(value: PatientDto) {
        this.dossierService.actualPationt = value;
    }


    clickNew() {
        this.router.navigateByUrl('/app/medecin/patient/patient/list')
    }
}
