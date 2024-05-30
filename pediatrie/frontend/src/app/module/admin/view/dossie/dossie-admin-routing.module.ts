
// const root = environment.rootAppUrl;



import {UserListComponent} from 'src/app/module/security/user/list/user-list.component';
import {ModelPermissionListComponent} from 'src/app/module/security/model-permission/list/model-permission-list.component';
import {ActionPermissionListComponent} from 'src/app/module/security/action-permission/list/action-permission-list.component';
import {ModelPermissionUserListComponent} from 'src/app/module/security/model-permission-utilisateur/list/model-permission-user-list.component';
import {RoleListComponent} from 'src/app/module/security/role/list/role-list.component';



import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';



import { AntecedentListAdminComponent } from './antecedent/list/antecedent-list-admin.component';
import { FichePatientListAdminComponent } from './fiche-patient/list/fiche-patient-list-admin.component';
import { TypeImageListAdminComponent } from './type-image/list/type-image-list-admin.component';
import { EpreuveListAdminComponent } from './epreuve/list/epreuve-list-admin.component';
import { AnalyseMedicaleListAdminComponent } from './analyse-medicale/list/analyse-medicale-list-admin.component';
import { RadiologieListAdminComponent } from './radiologie/list/radiologie-list-admin.component';
import { GroupeSanguinListAdminComponent } from './groupe-sanguin/list/groupe-sanguin-list-admin.component';
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
                                    component: AntecedentListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'fiche-patient',
                            children: [
                                {
                                    path: 'list',
                                    component: FichePatientListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'type-image',
                            children: [
                                {
                                    path: 'list',
                                    component: TypeImageListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'epreuve',
                            children: [
                                {
                                    path: 'list',
                                    component: EpreuveListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'analyse-medicale',
                            children: [
                                {
                                    path: 'list',
                                    component: AnalyseMedicaleListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'radiologie',
                            children: [
                                {
                                    path: 'list',
                                    component: RadiologieListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'groupe-sanguin',
                            children: [
                                {
                                    path: 'list',
                                    component: GroupeSanguinListAdminComponent ,
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
export class DossieAdminRoutingModule { }
