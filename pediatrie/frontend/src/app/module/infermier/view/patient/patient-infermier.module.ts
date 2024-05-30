import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {EditorModule} from "primeng/editor";

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';
import {TranslateModule} from '@ngx-translate/core';
import {FileUploadModule} from 'primeng/fileupload';
import {FullCalendarModule} from '@fullcalendar/angular';
import {CardModule} from "primeng/card";

import { PatientContactCreateInfermierComponent } from './patient-contact/create/patient-contact-create-infermier.component';
import { PatientContactEditInfermierComponent } from './patient-contact/edit/patient-contact-edit-infermier.component';
import { PatientContactViewInfermierComponent } from './patient-contact/view/patient-contact-view-infermier.component';
import { PatientContactListInfermierComponent } from './patient-contact/list/patient-contact-list-infermier.component';
import { RelationCreateInfermierComponent } from './relation/create/relation-create-infermier.component';
import { RelationEditInfermierComponent } from './relation/edit/relation-edit-infermier.component';
import { RelationViewInfermierComponent } from './relation/view/relation-view-infermier.component';
import { RelationListInfermierComponent } from './relation/list/relation-list-infermier.component';
import { PatientCreateInfermierComponent } from './patient/create/patient-create-infermier.component';
import { PatientEditInfermierComponent } from './patient/edit/patient-edit-infermier.component';
import { PatientViewInfermierComponent } from './patient/view/patient-view-infermier.component';
import { PatientListInfermierComponent } from './patient/list/patient-list-infermier.component';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
import {PaginatorModule} from 'primeng/paginator';
import {MatInputModule} from "@angular/material/input";
import {NgxMatFileInputModule} from "@angular-material-components/file-input";
import {MatIconModule} from "@angular/material/icon";
import {PatientConsultationInfermier} from "./patient/patientConsultation/list/patient-consultation-infermier";
import {
    PatientConsultationViewInfermierComponent
} from "./patient/patientConsultation/view/patient-consultation-view-infermier.component";



@NgModule({
  declarations: [

    PatientContactCreateInfermierComponent,
    PatientContactListInfermierComponent,
    PatientContactViewInfermierComponent,
    PatientContactEditInfermierComponent,
    RelationCreateInfermierComponent,
    RelationListInfermierComponent,
    RelationViewInfermierComponent,
    RelationEditInfermierComponent,
    PatientCreateInfermierComponent,
    PatientListInfermierComponent,
    PatientViewInfermierComponent,
    PatientEditInfermierComponent,
      PatientConsultationInfermier,
      PatientConsultationViewInfermierComponent
  ],
    imports: [
        CommonModule,
        ToastModule,
        ToolbarModule,
        TableModule,
        ConfirmDialogModule,
        DialogModule,
        PasswordModule,
        InputTextModule,
        ButtonModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule,
        SplitButtonModule,
        BrowserAnimationsModule,
        DropdownModule,
        TabViewModule,
        InputSwitchModule,
        InputTextareaModule,
        CalendarModule,
        PanelModule,
        MessageModule,
        MessagesModule,
        InputNumberModule,
        BadgeModule,
        MultiSelectModule,
        PaginatorModule,
        TranslateModule,
        FileUploadModule,
        FullCalendarModule,
        CardModule,
        EditorModule,
        MatInputModule,
        NgxMatFileInputModule,
        MatIconModule,

    ],
  exports: [
  PatientContactCreateInfermierComponent,
  PatientContactListInfermierComponent,
  PatientContactViewInfermierComponent,
  PatientContactEditInfermierComponent,
  RelationCreateInfermierComponent,
  RelationListInfermierComponent,
  RelationViewInfermierComponent,
  RelationEditInfermierComponent,
  PatientCreateInfermierComponent,
  PatientListInfermierComponent,
  PatientViewInfermierComponent,
  PatientEditInfermierComponent,
      PatientConsultationInfermier,
      PatientConsultationViewInfermierComponent

  ],
})
export class PatientInfermierModule { }
