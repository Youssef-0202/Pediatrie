import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';
import {RoleService} from "../zynerator/security/shared/service/Role.service";
import {AppComponent} from "../app.component";
import {AuthService} from "../zynerator/security/shared/service/Auth.service";
import {Router} from "@angular/router";
import {AppLayoutComponent} from "./app.layout.component";

@Component({
  selector: 'app-menu',
  templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {
  model: any[];
  modelanonymous: any[];
  modelAdmin: any[];
  modelMedecin: any[];
  modelInfermier: any[];
constructor( public app: AppComponent, public appMain: AppLayoutComponent, private roleService: RoleService, private authService: AuthService, private router: Router) { }
  ngOnInit() {
    this.modelAdmin =
      [         {
          label: 'Home',
          items: [
              { label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/app/admin'] }
          ]
      },

				{
                    label: 'Menu',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'Gestion de medecin',
						icon: 'pi pi-user',
						items: [
								  {
									label: 'Liste medecin',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/commun/medecin/list']
								  },
						]
					  },
                        {
                            label: 'Gestion infirmier',
                            icon: 'pi pi-user',
                            items: [
                                {
                                    label: 'Liste infirmier',
                                    icon: 'pi pi-fw pi-plus-circle',
                                    routerLink: ['/app/admin/commun/infermier/list']
                                },
                            ]
                        },
					  {
						label: 'Commun',
						icon: 'pi pi-clone',
						items: [
								  {
									label: 'Liste sexe',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/commun/sexe/list']
								  },
                            {
                                label: 'Liste relation',
                                icon: 'pi pi-fw pi-plus-circle',
                                routerLink: ['/app/admin/patient/relation/list']
                            }
						]
					  },

					  {
						label: 'Patient',
						icon: 'pi pi-users',
						items: [
								  {
									label: 'Liste patient contact',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/patient/patient-contact/list']
								  },

								  {
									label: 'Liste patient',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/patient/patient/list']
								  },
						]
					  },


			]
	    }
    ];

    this.modelMedecin =

      [

          {
              label: 'Home',
              items: [
                  { label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/app/medecin'] },
                  { label: 'Statistique', icon: 'pi pi-chart-bar', routerLink: ['/app/medecin/statistique'] }
              ]
          },

				{
                    label: 'Menu',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
                        {
                            label: 'Patient',
                            icon: 'pi pi-users',
                            items: [
                                {
                                    label: 'Nouveau Patient',
                                    icon: 'pi pi-fw pi-plus-circle',
                                    routerLink: ['/app/medecin/patient/patient/list']
                                },
                                {
                                    label: 'Patient Contact',
                                    icon: 'pi pi-fw pi-plus-circle',
                                    routerLink: ['/app/medecin/patient/patient-contact/list']
                                },
                                {
                                    label: 'Relation',
                                    icon: 'pi pi-fw pi-plus-circle',
                                    routerLink: ['/app/medecin/patient/relation/list']
                                },
                            ]
                        },
					  {
						label: 'Gestion medicale',
						icon: 'pi pi-folder-open',
						items: [
								  {
									label: 'Certificat',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/medecin/gestio/certificat/list']
								  },
								  {
									label: 'Traitement',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/medecin/gestio/traitement/list']
								  },
								  {
									label: 'Medicament',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/medecin/gestio/medicament/list']
								  },
								  {
									label: 'Ordonnance',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/medecin/gestio/ordonnance/list']
								  },
						]
					  },
                        {
                            label: 'Gestion infirmier',
                            icon: 'pi pi-user',
                            items: [
                                {
                                    label: 'Liste infirmier',
                                    icon: 'pi pi-fw pi-plus-circle',
                                    routerLink: ['/app/medecin/commun/infermier/list']
                                },
                            ]
                        },
					  {
						label: 'Gestion des Consultations',
						icon: 'pi   pi-file-export',
						items: [
                                 {
                                     label: 'Consultation',
                                     icon: 'pi pi-fw pi-plus-circle',
                                     routerLink: ['/app/medecin/consultatio/consultation/list']
                                  },
                                  {
                                      label: 'Fiche Patient',
                                      icon: 'pi pi-fw pi-plus-circle',
                                      routerLink: ['/app/medecin/dossie/fiche-patient/list']
                                  },
								  {
									label: 'Antecedent',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/medecin/dossie/antecedent/list']
								  },
								  {
									label: 'Epreuve',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/medecin/dossie/epreuve/list']
								  },

								  {
									label: 'Diagnostic',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/medecin/rappor/diagnostic/list']
								  },
								  {
									label: 'Analyse Medicale',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/medecin/dossie/analyse-medicale/list']
								  },
								  {
									label: 'Radiologie',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/medecin/dossie/radiologie/list']
								  },
								  {
									label: 'Synthese Medicale',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/medecin/rappor/synthese-medicale/list']
								  },
						]
					  },

                    ]
	    }
    ];
    this.modelInfermier =
      [

          {
                    label: 'Menu',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
                        {
						label: 'Patient',
						icon: 'pi pi-users',
						items: [
                            {
                                label: 'Patient',
                                icon: 'pi pi-fw pi-plus-circle',
                                routerLink: ['/app/infermier/patient/patient/list']
                            },
                            {
                                label: 'Patient Contact',
                                icon: 'pi pi-fw pi-plus-circle',
                                routerLink: ['/app/infermier/patient/patient-contact/list']
                            },

						]
					  },

			]
	    }
    ];

        if (this.authService.authenticated) {
            if (this.authService.authenticatedUser.roleUsers) {
              this.authService.authenticatedUser.roleUsers.forEach(role => {
                  const roleName: string = this.getRole(role.role.authority);
                  this.roleService._role.next(roleName.toUpperCase());
                  eval('this.model = this.model' + this.getRole(role.role.authority));
              })
            }
        }
  }

    getRole(text){
        const [role, ...rest] = text.split('_');
        return this.upperCaseFirstLetter(rest.join(''));
    }

    upperCaseFirstLetter(word: string) {
      if (!word) { return word; }
      return word[0].toUpperCase() + word.substr(1).toLowerCase();
    }

    onMenuClick(event) {
        this.appMain.onMenuClick(event);
    }
}
