
// const root = environment.rootAppUrl;



import {UserListComponent} from 'src/app/module/security/user/list/user-list.component';
import {ModelPermissionListComponent} from 'src/app/module/security/model-permission/list/model-permission-list.component';
import {ActionPermissionListComponent} from 'src/app/module/security/action-permission/list/action-permission-list.component';
import {ModelPermissionUserListComponent} from 'src/app/module/security/model-permission-utilisateur/list/model-permission-user-list.component';
import {RoleListComponent} from 'src/app/module/security/role/list/role-list.component';



import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';



import { AntecedentListMedecinComponent } from './antecedent/list/antecedent-list-medecin.component';
import { FichePatientListMedecinComponent } from './fiche-patient/list/fiche-patient-list-medecin.component';
import { TypeImageListMedecinComponent } from './type-image/list/type-image-list-medecin.component';
import { EpreuveListMedecinComponent } from './epreuve/list/epreuve-list-medecin.component';
import { AnalyseMedicaleListMedecinComponent } from './analyse-medicale/list/analyse-medicale-list-medecin.component';
import { RadiologieListMedecinComponent } from './radiologie/list/radiologie-list-medecin.component';
import { GroupeSanguinListMedecinComponent } from './groupe-sanguin/list/groupe-sanguin-list-medecin.component';
@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    children: [
                        {

                            path: 'action-permission',
                            children: [
                                {
                                    path: 'list',
                                    component: ActionPermissionListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'model-permission-user',
                            children: [
                                {
                                    path: 'list',
                                    component: ModelPermissionUserListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },
                        {

                            path: 'role',
                            children: [
                                {
                                    path: 'list',
                                    component: RoleListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },
                        {

                            path: 'user',
                            children: [
                                {
                                    path: 'list',
                                    component: UserListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'model-permission',
                            children: [
                                {
                                    path: 'list',
                                    component: ModelPermissionListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },


                        {

                            path: 'antecedent',
                            children: [
                                {
                                    path: 'list',
                                    component: AntecedentListMedecinComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'fiche-patient',
                            children: [
                                {
                                    path: 'list',
                                    component: FichePatientListMedecinComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'type-image',
                            children: [
                                {
                                    path: 'list',
                                    component: TypeImageListMedecinComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'epreuve',
                            children: [
                                {
                                    path: 'list',
                                    component: EpreuveListMedecinComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'analyse-medicale',
                            children: [
                                {
                                    path: 'list',
                                    component: AnalyseMedicaleListMedecinComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'radiologie',
                            children: [
                                {
                                    path: 'list',
                                    component: RadiologieListMedecinComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'groupe-sanguin',
                            children: [
                                {
                                    path: 'list',
                                    component: GroupeSanguinListMedecinComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                    ]
                },
            ]
        ),
    ],
    exports: [RouterModule],
})
export class DossieMedecinRoutingModule { }
