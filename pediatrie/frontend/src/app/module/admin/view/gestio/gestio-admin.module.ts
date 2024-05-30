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

import { CertificatCreateAdminComponent } from './certificat/create/certificat-create-admin.component';
import { CertificatEditAdminComponent } from './certificat/edit/certificat-edit-admin.component';
import { CertificatViewAdminComponent } from './certificat/view/certificat-view-admin.component';
import { CertificatListAdminComponent } from './certificat/list/certificat-list-admin.component';
import { TraitementCreateAdminComponent } from './traitement/create/traitement-create-admin.component';
import { TraitementEditAdminComponent } from './traitement/edit/traitement-edit-admin.component';
import { TraitementViewAdminComponent } from './traitement/view/traitement-view-admin.component';
import { TraitementListAdminComponent } from './traitement/list/traitement-list-admin.component';
import { MedicamentCreateAdminComponent } from './medicament/create/medicament-create-admin.component';
import { MedicamentEditAdminComponent } from './medicament/edit/medicament-edit-admin.component';
import { MedicamentViewAdminComponent } from './medicament/view/medicament-view-admin.component';
import { MedicamentListAdminComponent } from './medicament/list/medicament-list-admin.component';
import { OrdonnanceCreateAdminComponent } from './ordonnance/create/ordonnance-create-admin.component';
import { OrdonnanceEditAdminComponent } from './ordonnance/edit/ordonnance-edit-admin.component';
import { OrdonnanceViewAdminComponent } from './ordonnance/view/ordonnance-view-admin.component';
import { OrdonnanceListAdminComponent } from './ordonnance/list/ordonnance-list-admin.component';

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

    CertificatCreateAdminComponent,
    CertificatListAdminComponent,
    CertificatViewAdminComponent,
    CertificatEditAdminComponent,
    TraitementCreateAdminComponent,
    TraitementListAdminComponent,
    TraitementViewAdminComponent,
    TraitementEditAdminComponent,
    MedicamentCreateAdminComponent,
    MedicamentListAdminComponent,
    MedicamentViewAdminComponent,
    MedicamentEditAdminComponent,
    OrdonnanceCreateAdminComponent,
    OrdonnanceListAdminComponent,
    OrdonnanceViewAdminComponent,
    OrdonnanceEditAdminComponent,
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
  CertificatCreateAdminComponent,
  CertificatListAdminComponent,
  CertificatViewAdminComponent,
  CertificatEditAdminComponent,
  TraitementCreateAdminComponent,
  TraitementListAdminComponent,
  TraitementViewAdminComponent,
  TraitementEditAdminComponent,
  MedicamentCreateAdminComponent,
  MedicamentListAdminComponent,
  MedicamentViewAdminComponent,
  MedicamentEditAdminComponent,
  OrdonnanceCreateAdminComponent,
  OrdonnanceListAdminComponent,
  OrdonnanceViewAdminComponent,
  OrdonnanceEditAdminComponent,
  ],
})
export class GestioAdminModule { }
