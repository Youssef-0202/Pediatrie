import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {AuthGuard} from "../../../../zynerator/security/guards/auth.guard";
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


export class ProfilMedecinRoutingModule {}
