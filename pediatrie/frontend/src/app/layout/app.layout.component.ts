import {Component, DoCheck, OnChanges, OnDestroy, OnInit, Renderer2, ViewChild} from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter, Subscription } from 'rxjs';
import { LayoutService } from "./service/app.layout.service";
import { AppSidebarComponent } from "./app.sidebar.component";
import { AppTopBarComponent } from './app.topbar.component';
import { ButtonModule } from 'primeng/button';
import {reflectTypeEntityToDeclaration} from "@angular/compiler-cli/src/ngtsc/reflection";
import {AuthService} from "../zynerator/security/shared/service/Auth.service";
import {PatientMedecinService} from "../shared/service/medecin/patient/PatientMedecin.service";
import {PatientCriteria} from "../shared/criteria/patient/PatientCriteria.model";
import {MedecinDto} from "../shared/model/commun/Medecin.model";
import {PatientDto} from "../shared/model/patient/Patient.model";
import {error} from "protractor";
import {DossierService} from "../shared/service/medecin/commun/Dossier.service";


@Component({
    selector: 'app-layout',
    templateUrl: './app.layout.component.html',
    styleUrls:['./app.layout.component.css']
})
export class AppLayoutComponent implements OnDestroy ,OnInit{

    menuClick = true;
    resetMenu = true;
    overlayMenuOpenSubscription: Subscription;
    currentPath=""
    showStatistic=true;

    get totalRecords(): number {
        return this._totalRecords;
    }

    set totalRecords(value: number) {
        this._totalRecords = value;
    }

    menuOutsideClickListener: any;

    profileMenuOutsideClickListener: any;
    private _totalRecords = 0;

    @ViewChild(AppSidebarComponent) appSidebar!: AppSidebarComponent;

    @ViewChild(AppTopBarComponent) appTopbar!: AppTopBarComponent;

    constructor(private  authService: AuthService,private dossierService:DossierService , private patientService:PatientMedecinService, private service:AuthService , public layoutService: LayoutService, public renderer: Renderer2, public router: Router) {
        this.overlayMenuOpenSubscription = this.layoutService.overlayOpen$.subscribe(() => {
            if (!this.menuOutsideClickListener) {
                this.menuOutsideClickListener = this.renderer.listen('document', 'click', event => {
                    const isOutsideClicked = !(this.appSidebar.el.nativeElement.isSameNode(event.target) || this.appSidebar.el.nativeElement.contains(event.target)
                        || this.appTopbar.menuButton.nativeElement.isSameNode(event.target) || this.appTopbar.menuButton.nativeElement.contains(event.target));
                    if (isOutsideClicked) {
                        this.hideMenu();
                    }
                });
            }

            if (!this.profileMenuOutsideClickListener) {
                this.profileMenuOutsideClickListener = this.renderer.listen('document', 'click', event => {
                    const isOutsideClicked = !(this.appTopbar.menu.nativeElement.isSameNode(event.target) || this.appTopbar.menu.nativeElement.contains(event.target)
                        || this.appTopbar.topbarMenuButton.nativeElement.isSameNode(event.target) || this.appTopbar.topbarMenuButton.nativeElement.contains(event.target));

                    if (isOutsideClicked) {
                        this.hideProfileMenu();
                    }
                });
            }

            if (this.layoutService.state.staticMenuMobileActive) {
                this.blockBodyScroll();
            }
        }

        );



        this.router.events.pipe(filter(event => event instanceof NavigationEnd))
            .subscribe(() => {
                this.hideMenu();
                this.hideProfileMenu();
            });


    }
    ngOnInit() {
        this.searchClick();
        this.router.events.subscribe(event => {
            if (event instanceof NavigationEnd) {
                this.currentPath = event.url;
                console.log(this.currentPath);
            }
        });
    }


    hideMenu() {
        this.layoutService.state.overlayMenuActive = false;
        this.layoutService.state.staticMenuMobileActive = false;
        this.layoutService.state.menuHoverActive = false;
        if (this.menuOutsideClickListener) {
            this.menuOutsideClickListener();
            this.menuOutsideClickListener = null;
        }
        this.unblockBodyScroll();
    }

    hideProfileMenu() {
        this.layoutService.state.profileSidebarVisible = false;
        if (this.profileMenuOutsideClickListener) {
            this.profileMenuOutsideClickListener();
            this.profileMenuOutsideClickListener = null;
        }
    }

    blockBodyScroll(): void {
        if (document.body.classList) {
            document.body.classList.add('blocked-scroll');
        }
        else {
            document.body.className += ' blocked-scroll';
        }
    }

    unblockBodyScroll(): void {
        if (document.body.classList) {
            document.body.classList.remove('blocked-scroll');
        }
        else {
            document.body.className = document.body.className.replace(new RegExp('(^|\\b)' +
                'blocked-scroll'.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
        }
    }

    checkMedecinPath() {
        if((this.currentPath=="/app/medecin"||this.currentPath=="") && this.service.authenticatedUser.roleUsers[0].role.authority === 'ROLE_MEDECIN'){
            return true;
        }else
            return false;
    }

    checkAdminPath() {
        if((this.currentPath=="/app/admin"||this.currentPath=="") && this.service.authenticatedUser.roleUsers[0].role.authority === 'ROLE_ADMIN'){
            return true;
        }else
            return false;
    }

    get  containerClass() {
        return {
            'layout-theme-light': this.layoutService.config.colorScheme === 'light',
            'layout-theme-dark': this.layoutService.config.colorScheme === 'dark',
            'layout-overlay': this.layoutService.config.menuMode === 'overlay',
            'layout-static': this.layoutService.config.menuMode === 'static',
            'layout-static-inactive': this.layoutService.state.staticMenuDesktopInactive && this.layoutService.config.menuMode === 'static',
            'layout-overlay-active': this.layoutService.state.overlayMenuActive,
            'layout-mobile-active': this.layoutService.state.staticMenuMobileActive,
            'p-input-filled': this.layoutService.config.inputStyle === 'filled',
            'p-ripple-disabled': !this.layoutService.config.ripple
        }
    }
    onMenuClick(event) {
        this.menuClick = true;
        this.resetMenu = false;
    }

    ngOnDestroy() {
        if (this.overlayMenuOpenSubscription) {
            this.overlayMenuOpenSubscription.unsubscribe();
        }

        if (this.menuOutsideClickListener) {
            this.menuOutsideClickListener();
        }
    }


    click() {
        this.router.navigateByUrl('/app/admin/profil');
    }

    protected readonly alert = alert;
    protected readonly console = console;
    findByCriteriaShow: boolean=false;
    protected cols: any[] = [];


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
       if(this.authService.authenticatedMedecin.ref != ''){
           this.patientService.findAll().subscribe(paginatedItems => {
               this.items = paginatedItems;
               this.totalRecords = paginatedItems.length;
               console.log(this.items)
               /*this.totalRecords = paginatedItems.dataSize;*/
               this.selections = new Array<PatientDto>();
           }, error => console.log(error));
       }
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

    getBackgroundImage(): string {
       if (this.authService.authenticatedUser.roleUsers[0]?.role.authority === 'ROLE_MEDECIN') {
           return 'url(src/assets/layout/images/cc-med.jpg)';
        }
       else  return 'url(src/assets/layout/images/cc-med.jpg)';
    }




    get actualPationt(): PatientDto {
        return this.dossierService.actualPationt;
    }

    set actualPationt(value: PatientDto) {
        this.dossierService.actualPationt = value;
    }
}
