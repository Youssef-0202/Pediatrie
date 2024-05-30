import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {AuthGuard} from "../../../../zynerator/security/guards/auth.guard";
import {StatistiqueMedecinComponent} from "./statistique-medecin/statistique-medecin.component";


@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    component:StatistiqueMedecinComponent,
                    canActivate:[AuthGuard],
                },
            ]
        ),
    ],
    exports: [RouterModule],
})


export class StatistiqueMedecinRoutingModules {}
