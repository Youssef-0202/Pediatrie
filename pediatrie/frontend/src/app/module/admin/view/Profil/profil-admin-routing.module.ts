import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {ActionPermissionListComponent} from "../../../security/action-permission/list/action-permission-list.component";
import {AuthGuard} from "../../../../zynerator/security/guards/auth.guard";
import {
    ModelPermissionUserListComponent
} from "../../../security/model-permission-utilisateur/list/model-permission-user-list.component";
import {RoleListComponent} from "../../../security/role/list/role-list.component";
import {UserListComponent} from "../../../security/user/list/user-list.component";
import {ModelPermissionListComponent} from "../../../security/model-permission/list/model-permission-list.component";
import {SexeListAdminComponent} from "../commun/sexe/list/sexe-list-admin.component";
import {InfermierListAdminComponent} from "../commun/infermier/list/infermier-list-admin.component";
import {MedecinListAdminComponent} from "../commun/medecin/list/medecin-list-admin.component";
import {ProfilComponent} from "./display/profil.component";

@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    component:ProfilComponent,
                    canActivate:[AuthGuard],
                },
            ]
        ),
    ],
    exports: [RouterModule],
})


export class ProfilAdminRoutingModule{}
