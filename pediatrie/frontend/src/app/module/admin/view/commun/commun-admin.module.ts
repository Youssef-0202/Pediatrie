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

import { SexeCreateAdminComponent } from './sexe/create/sexe-create-admin.component';
import { SexeEditAdminComponent } from './sexe/edit/sexe-edit-admin.component';
import { SexeViewAdminComponent } from './sexe/view/sexe-view-admin.component';
import { SexeListAdminComponent } from './sexe/list/sexe-list-admin.component';
import { InfermierCreateAdminComponent } from './infermier/create/infermier-create-admin.component';
import { InfermierEditAdminComponent } from './infermier/edit/infermier-edit-admin.component';
import { InfermierViewAdminComponent } from './infermier/view/infermier-view-admin.component';
import { InfermierListAdminComponent } from './infermier/list/infermier-list-admin.component';
import { MedecinCreateAdminComponent } from './medecin/create/medecin-create-admin.component';
import { MedecinEditAdminComponent } from './medecin/edit/medecin-edit-admin.component';
import { MedecinViewAdminComponent } from './medecin/view/medecin-view-admin.component';
import { MedecinListAdminComponent } from './medecin/list/medecin-list-admin.component';
import {ProfilComponent} from "../Profil/display/profil.component";
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

    SexeCreateAdminComponent,
    SexeListAdminComponent,
    SexeViewAdminComponent,
    SexeEditAdminComponent,
    InfermierCreateAdminComponent,
    InfermierListAdminComponent,
    InfermierViewAdminComponent,
    InfermierEditAdminComponent,
    MedecinCreateAdminComponent,
    MedecinListAdminComponent,
    MedecinViewAdminComponent,
    MedecinEditAdminComponent,
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
  SexeCreateAdminComponent,
  SexeListAdminComponent,
  SexeViewAdminComponent,
  SexeEditAdminComponent,
  InfermierCreateAdminComponent,
  InfermierListAdminComponent,
  InfermierViewAdminComponent,
  InfermierEditAdminComponent,
  MedecinCreateAdminComponent,
  MedecinListAdminComponent,
  MedecinViewAdminComponent,
  MedecinEditAdminComponent,
  ],
})
export class CommunAdminModule { }
