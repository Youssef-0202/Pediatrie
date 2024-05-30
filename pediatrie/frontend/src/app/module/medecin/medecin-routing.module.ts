
// const root = environment.rootAppUrl;

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

import { LoginMedecinComponent } from './login-medecin/login-medecin.component';
import { RegisterMedecinComponent } from './register-medecin/register-medecin.component';

@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    children: [
                        {
                            path: 'login',
                            children: [
                                {
                                    path: '',
                                    component: LoginMedecinComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterMedecinComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'dossie',
                            loadChildren: () => import('./view/dossie/dossie-medecin-routing.module').then(x => x.DossieMedecinRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'rappor',
                            loadChildren: () => import('./view/rappor/rappor-medecin-routing.module').then(x => x.RapporMedecinRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'patient',
                            loadChildren: () => import('./view/patient/patient-medecin-routing.module').then(x => x.PatientMedecinRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'consultatio',
                            loadChildren: () => import('./view/consultatio/consultatio-medecin-routing.module').then(x => x.ConsultatioMedecinRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'commun',
                            loadChildren: () => import('./view/commun/commun-medecin-routing.module').then(x => x.CommunMedecinRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'gestio',
                            loadChildren: () => import('./view/gestio/gestio-medecin-routing.module').then(x => x.GestioMedecinRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'security',
                            loadChildren: () => import('../security/security-routing.module').then(x => x.SecurityRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'profil',
                            loadChildren: () => import('./view/Profil/profil-medecin-routing.module').then(x => x.ProfilMedecinRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'statistique',
                            loadChildren: () => import('./view/satistique/statistique-medecin-routing.modules').then(x => x.StatistiqueMedecinRoutingModules),
                            canActivate: [AuthGuard],
                        },
                    ]
                },
            ]
        ),
    ],
    exports: [RouterModule],
})
export class MedecinRoutingModule { }
