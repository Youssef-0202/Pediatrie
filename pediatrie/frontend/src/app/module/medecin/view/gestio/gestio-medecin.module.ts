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

import { CertificatCreateMedecinComponent } from './certificat/create/certificat-create-medecin.component';
import { CertificatEditMedecinComponent } from './certificat/edit/certificat-edit-medecin.component';
import { CertificatViewMedecinComponent } from './certificat/view/certificat-view-medecin.component';
import { CertificatListMedecinComponent } from './certificat/list/certificat-list-medecin.component';
import { TraitementCreateMedecinComponent } from './traitement/create/traitement-create-medecin.component';
import { TraitementEditMedecinComponent } from './traitement/edit/traitement-edit-medecin.component';
import { TraitementViewMedecinComponent } from './traitement/view/traitement-view-medecin.component';
import { TraitementListMedecinComponent } from './traitement/list/traitement-list-medecin.component';
import { MedicamentCreateMedecinComponent } from './medicament/create/medicament-create-medecin.component';
import { MedicamentEditMedecinComponent } from './medicament/edit/medicament-edit-medecin.component';
import { MedicamentViewMedecinComponent } from './medicament/view/medicament-view-medecin.component';
import { MedicamentListMedecinComponent } from './medicament/list/medicament-list-medecin.component';
import { OrdonnanceCreateMedecinComponent } from './ordonnance/create/ordonnance-create-medecin.component';
import { OrdonnanceEditMedecinComponent } from './ordonnance/edit/ordonnance-edit-medecin.component';
import { OrdonnanceViewMedecinComponent } from './ordonnance/view/ordonnance-view-medecin.component';
import { OrdonnanceListMedecinComponent } from './ordonnance/list/ordonnance-list-medecin.component';

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

    CertificatCreateMedecinComponent,
    CertificatListMedecinComponent,
    CertificatViewMedecinComponent,
    CertificatEditMedecinComponent,
    TraitementCreateMedecinComponent,
    TraitementListMedecinComponent,
    TraitementViewMedecinComponent,
    TraitementEditMedecinComponent,
    MedicamentCreateMedecinComponent,
    MedicamentListMedecinComponent,
    MedicamentViewMedecinComponent,
    MedicamentEditMedecinComponent,
    OrdonnanceCreateMedecinComponent,
    OrdonnanceListMedecinComponent,
    OrdonnanceViewMedecinComponent,
    OrdonnanceEditMedecinComponent,
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
  CertificatCreateMedecinComponent,
  CertificatListMedecinComponent,
  CertificatViewMedecinComponent,
  CertificatEditMedecinComponent,
  TraitementCreateMedecinComponent,
  TraitementListMedecinComponent,
  TraitementViewMedecinComponent,
  TraitementEditMedecinComponent,
  MedicamentCreateMedecinComponent,
  MedicamentListMedecinComponent,
  MedicamentViewMedecinComponent,
  MedicamentEditMedecinComponent,
  OrdonnanceCreateMedecinComponent,
  OrdonnanceListMedecinComponent,
  OrdonnanceViewMedecinComponent,
  OrdonnanceEditMedecinComponent,
  ],
})
export class GestioMedecinModule { }
