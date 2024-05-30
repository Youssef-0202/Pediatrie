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

import { PatientContactCreateAdminComponent } from './patient-contact/create/patient-contact-create-admin.component';
import { PatientContactEditAdminComponent } from './patient-contact/edit/patient-contact-edit-admin.component';
import { PatientContactViewAdminComponent } from './patient-contact/view/patient-contact-view-admin.component';
import { PatientContactListAdminComponent } from './patient-contact/list/patient-contact-list-admin.component';
import { RelationCreateAdminComponent } from './relation/create/relation-create-admin.component';
import { RelationEditAdminComponent } from './relation/edit/relation-edit-admin.component';
import { RelationViewAdminComponent } from './relation/view/relation-view-admin.component';
import { RelationListAdminComponent } from './relation/list/relation-list-admin.component';
import { PatientCreateAdminComponent } from './patient/create/patient-create-admin.component';
import { PatientEditAdminComponent } from './patient/edit/patient-edit-admin.component';
import { PatientViewAdminComponent } from './patient/view/patient-view-admin.component';
import { PatientListAdminComponent } from './patient/list/patient-list-admin.component';

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

    PatientContactCreateAdminComponent,
    PatientContactListAdminComponent,
    PatientContactViewAdminComponent,
    PatientContactEditAdminComponent,
    RelationCreateAdminComponent,
    RelationListAdminComponent,
    RelationViewAdminComponent,
    RelationEditAdminComponent,
    PatientCreateAdminComponent,
    PatientListAdminComponent,
    PatientViewAdminComponent,
    PatientEditAdminComponent,
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
  PatientContactCreateAdminComponent,
  PatientContactListAdminComponent,
  PatientContactViewAdminComponent,
  PatientContactEditAdminComponent,
  RelationCreateAdminComponent,
  RelationListAdminComponent,
  RelationViewAdminComponent,
  RelationEditAdminComponent,
  PatientCreateAdminComponent,
  PatientListAdminComponent,
  PatientViewAdminComponent,
  PatientEditAdminComponent,
  ],
})
export class PatientAdminModule { }
