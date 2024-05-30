import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatistiqueMedecinComponent } from './statistique-medecin/statistique-medecin.component';
import {ToastModule} from "primeng/toast";
import {ButtonModule} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {CardModule} from "primeng/card";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {DropdownModule} from "primeng/dropdown";
import {InputTextModule} from "primeng/inputtext";
import {PaginatorModule} from "primeng/paginator";
import {PatientMedecinModule} from "../patient/patient-medecin.module";
import {ReactiveFormsModule} from "@angular/forms";
import {SplitButtonModule} from "primeng/splitbutton";
import {TableModule} from "primeng/table";
import {ToolbarModule} from "primeng/toolbar";
import {TranslateModule} from "@ngx-translate/core";
import {ChartModule} from "primeng/chart";



@NgModule({
  declarations: [
       StatistiqueMedecinComponent
  ],
    imports: [
        CommonModule,
        ToastModule,
        ButtonModule,
        CalendarModule,
        CardModule,
        ConfirmDialogModule,
        DropdownModule,
        InputTextModule,
        PaginatorModule,
        PatientMedecinModule,
        ReactiveFormsModule,
        SplitButtonModule,
        TableModule,
        ToolbarModule,
        TranslateModule,
        ChartModule
    ],
    exports:[
        StatistiqueMedecinComponent
    ]
})
export class StatistiqueModule { }
