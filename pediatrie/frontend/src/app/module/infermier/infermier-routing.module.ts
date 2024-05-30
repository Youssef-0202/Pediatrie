
// const root = environment.rootAppUrl;

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

    import { LoginInfermierComponent } from './login-infermier/login-infermier.component';
    import { RegisterInfermierComponent } from './register-infermier/register-infermier.component';

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
                                    component: LoginInfermierComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterInfermierComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'dossie',
                            loadChildren: () => import('./view/dossie/dossie-infermier-routing.module').then(x => x.DossieInfermierRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'rappor',
                            loadChildren: () => import('./view/rappor/rappor-infermier-routing.module').then(x => x.RapporInfermierRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'patient',
                            loadChildren: () => import('./view/patient/patient-infermier-routing.module').then(x => x.PatientInfermierRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'consultatio',
                            loadChildren: () => import('./view/consultatio/consultatio-infermier-routing.module').then(x => x.ConsultatioInfermierRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'commun',
                            loadChildren: () => import('./view/commun/commun-infermier-routing.module').then(x => x.CommunInfermierRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'gestio',
                            loadChildren: () => import('./view/gestio/gestio-infermier-routing.module').then(x => x.GestioInfermierRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'security',
                            loadChildren: () => import('../security/security-routing.module').then(x => x.SecurityRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'profil',
                            loadChildren: () => import('./view/Profil/profil-infermier-routing.module').then(x => x.ProfilInfermierRoutingModule),
                            canActivate: [AuthGuard],
                        },
                    ]
                },
            ]
        ),
    ],
    exports: [RouterModule],
})
export class InfermierRoutingModule { }
