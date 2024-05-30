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

import { CertificatCreateInfermierComponent } from './certificat/create/certificat-create-infermier.component';
import { CertificatEditInfermierComponent } from './certificat/edit/certificat-edit-infermier.component';
import { CertificatViewInfermierComponent } from './certificat/view/certificat-view-infermier.component';
import { CertificatListInfermierComponent } from './certificat/list/certificat-list-infermier.component';
import { TraitementCreateInfermierComponent } from './traitement/create/traitement-create-infermier.component';
import { TraitementEditInfermierComponent } from './traitement/edit/traitement-edit-infermier.component';
import { TraitementViewInfermierComponent } from './traitement/view/traitement-view-infermier.component';
import { TraitementListInfermierComponent } from './traitement/list/traitement-list-infermier.component';
import { MedicamentCreateInfermierComponent } from './medicament/create/medicament-create-infermier.component';
import { MedicamentEditInfermierComponent } from './medicament/edit/medicament-edit-infermier.component';
import { MedicamentViewInfermierComponent } from './medicament/view/medicament-view-infermier.component';
import { MedicamentListInfermierComponent } from './medicament/list/medicament-list-infermier.component';
import { OrdonnanceCreateInfermierComponent } from './ordonnance/create/ordonnance-create-infermier.component';
import { OrdonnanceEditInfermierComponent } from './ordonnance/edit/ordonnance-edit-infermier.component';
import { OrdonnanceViewInfermierComponent } from './ordonnance/view/ordonnance-view-infermier.component';
import { OrdonnanceListInfermierComponent } from './ordonnance/list/ordonnance-list-infermier.component';

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

    CertificatCreateInfermierComponent,
    CertificatListInfermierComponent,
    CertificatViewInfermierComponent,
    CertificatEditInfermierComponent,
    TraitementCreateInfermierComponent,
    TraitementListInfermierComponent,
    TraitementViewInfermierComponent,
    TraitementEditInfermierComponent,
    MedicamentCreateInfermierComponent,
    MedicamentListInfermierComponent,
    MedicamentViewInfermierComponent,
    MedicamentEditInfermierComponent,
    OrdonnanceCreateInfermierComponent,
    OrdonnanceListInfermierComponent,
    OrdonnanceViewInfermierComponent,
    OrdonnanceEditInfermierComponent,
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
  CertificatCreateInfermierComponent,
  CertificatListInfermierComponent,
  CertificatViewInfermierComponent,
  CertificatEditInfermierComponent,
  TraitementCreateInfermierComponent,
  TraitementListInfermierComponent,
  TraitementViewInfermierComponent,
  TraitementEditInfermierComponent,
  MedicamentCreateInfermierComponent,
  MedicamentListInfermierComponent,
  MedicamentViewInfermierComponent,
  MedicamentEditInfermierComponent,
  OrdonnanceCreateInfermierComponent,
  OrdonnanceListInfermierComponent,
  OrdonnanceViewInfermierComponent,
  OrdonnanceEditInfermierComponent,
  ],
})
export class GestioInfermierModule { }
