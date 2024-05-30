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

import { PatientContactCreateMedecinComponent } from './patient-contact/create/patient-contact-create-medecin.component';
import { PatientContactEditMedecinComponent } from './patient-contact/edit/patient-contact-edit-medecin.component';
import { PatientContactViewMedecinComponent } from './patient-contact/view/patient-contact-view-medecin.component';
import { PatientContactListMedecinComponent } from './patient-contact/list/patient-contact-list-medecin.component';
import { RelationCreateMedecinComponent } from './relation/create/relation-create-medecin.component';
import { RelationEditMedecinComponent } from './relation/edit/relation-edit-medecin.component';
import { RelationViewMedecinComponent } from './relation/view/relation-view-medecin.component';
import { RelationListMedecinComponent } from './relation/list/relation-list-medecin.component';
import { PatientCreateMedecinComponent } from './patient/create/patient-create-medecin.component';
import { PatientEditMedecinComponent } from './patient/edit/patient-edit-medecin.component';
import { PatientViewMedecinComponent } from './patient/view/patient-view-medecin.component';
import { PatientListMedecinComponent } from './patient/list/patient-list-medecin.component';

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



@NgModule({
  declarations: [

    PatientContactCreateMedecinComponent,
    PatientContactListMedecinComponent,
    PatientContactViewMedecinComponent,
    PatientContactEditMedecinComponent,
    RelationCreateMedecinComponent,
    RelationListMedecinComponent,
    RelationViewMedecinComponent,
    RelationEditMedecinComponent,
    PatientCreateMedecinComponent,
    PatientListMedecinComponent,
    PatientViewMedecinComponent,
    PatientEditMedecinComponent,
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


  ],
  exports: [
  PatientContactCreateMedecinComponent,
  PatientContactListMedecinComponent,
  PatientContactViewMedecinComponent,
  PatientContactEditMedecinComponent,
  RelationCreateMedecinComponent,
  RelationListMedecinComponent,
  RelationViewMedecinComponent,
  RelationEditMedecinComponent,
  PatientCreateMedecinComponent,
  PatientListMedecinComponent,
  PatientViewMedecinComponent,
  PatientEditMedecinComponent,
  ],
})
export class PatientMedecinModule { }
