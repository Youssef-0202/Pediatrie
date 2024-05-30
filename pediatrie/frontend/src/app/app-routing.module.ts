import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {AuthGuard} from "src/app/zynerator/security/guards/auth.guard";
import {AccessComponent} from "src/app/demo/components/auth/access/access.component";
import {AppLayoutComponent} from "src/app/layout/app.layout.component";

import {LoginAdminComponent} from 'src/app/module/admin/login-admin/login-admin.component';
import {RegisterAdminComponent} from 'src/app/module/admin/register-admin/register-admin.component';
import {LoginMedecinComponent} from 'src/app/module/medecin/login-medecin/login-medecin.component';
import {RegisterMedecinComponent} from 'src/app/module/medecin/register-medecin/register-medecin.component';
import {LoginInfermierComponent} from 'src/app/module/infermier/login-infermier/login-infermier.component';
import {RegisterInfermierComponent} from 'src/app/module/infermier/register-infermier/register-infermier.component';
@NgModule({
    imports: [
        RouterModule.forRoot(
            [
                {path: '', component: LoginAdminComponent},
            {path: 'admin/login', component: LoginAdminComponent },
            {path: 'admin/register', component: RegisterAdminComponent },
            {
            path: 'app',
            component: AppLayoutComponent,
            children: [
                {
                    path: 'admin',
                    loadChildren: () => import( './module/admin/admin-routing.module').then(x => x.AdminRoutingModule),
                    canActivate: [AuthGuard],
                },
                {
                    path: 'medecin',
                    loadChildren: () => import( './module/medecin/medecin-routing.module').then(x => x.MedecinRoutingModule),
                    canActivate: [AuthGuard],
                },
                {
                    path: 'infermier',
                    loadChildren: () => import( './module/infermier/infermier-routing.module').then(x => x.InfermierRoutingModule),
                    canActivate: [AuthGuard],
                },
                    { path: 'denied', component: AccessComponent },
                ],
                canActivate: [AuthGuard]
                },
            ],
                { scrollPositionRestoration: 'enabled' }
            ),
        ],
    exports: [RouterModule],
    })
export class AppRoutingModule { }
