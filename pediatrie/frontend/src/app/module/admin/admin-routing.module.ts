
// const root = environment.rootAppUrl;

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

import { LoginAdminComponent } from './login-admin/login-admin.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';

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
                                    component: LoginAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'profil',
                            loadChildren: () => import('./view/Profil/profil-admin-routing.module').then(x => x.ProfilAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'dossie',
                            loadChildren: () => import('./view/dossie/dossie-admin-routing.module').then(x => x.DossieAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'rappor',
                            loadChildren: () => import('./view/rappor/rappor-admin-routing.module').then(x => x.RapporAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'patient',
                            loadChildren: () => import('./view/patient/patient-admin-routing.module').then(x => x.PatientAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'consultatio',
                            loadChildren: () => import('./view/consultatio/consultatio-admin-routing.module').then(x => x.ConsultatioAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'commun',
                            loadChildren: () => import('./view/commun/commun-admin-routing.module').then(x => x.CommunAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'gestio',
                            loadChildren: () => import('./view/gestio/gestio-admin-routing.module').then(x => x.GestioAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'security',
                            loadChildren: () => import('../security/security-routing.module').then(x => x.SecurityRoutingModule),
                            canActivate: [AuthGuard],
                        }
                    ]
                },
            ]
        ),
    ],
    exports: [RouterModule],
})
export class AdminRoutingModule { }
