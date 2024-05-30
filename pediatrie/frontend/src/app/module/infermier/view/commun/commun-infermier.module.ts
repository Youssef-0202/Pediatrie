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

import { SexeCreateInfermierComponent } from './sexe/create/sexe-create-infermier.component';
import { SexeEditInfermierComponent } from './sexe/edit/sexe-edit-infermier.component';
import { SexeViewInfermierComponent } from './sexe/view/sexe-view-infermier.component';
import { SexeListInfermierComponent } from './sexe/list/sexe-list-infermier.component';
import { InfermierCreateInfermierComponent } from './infermier/create/infermier-create-infermier.component';
import { InfermierEditInfermierComponent } from './infermier/edit/infermier-edit-infermier.component';
import { InfermierViewInfermierComponent } from './infermier/view/infermier-view-infermier.component';
import { InfermierListInfermierComponent } from './infermier/list/infermier-list-infermier.component';
import { MedecinCreateInfermierComponent } from './medecin/create/medecin-create-infermier.component';
import { MedecinEditInfermierComponent } from './medecin/edit/medecin-edit-infermier.component';
import { MedecinViewInfermierComponent } from './medecin/view/medecin-view-infermier.component';
import { MedecinListInfermierComponent } from './medecin/list/medecin-list-infermier.component';

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

    SexeCreateInfermierComponent,
    SexeListInfermierComponent,
    SexeViewInfermierComponent,
    SexeEditInfermierComponent,
    InfermierCreateInfermierComponent,
    InfermierListInfermierComponent,
    InfermierViewInfermierComponent,
    InfermierEditInfermierComponent,
    MedecinCreateInfermierComponent,
    MedecinListInfermierComponent,
    MedecinViewInfermierComponent,
    MedecinEditInfermierComponent,
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
  SexeCreateInfermierComponent,
  SexeListInfermierComponent,
  SexeViewInfermierComponent,
  SexeEditInfermierComponent,
  InfermierCreateInfermierComponent,
  InfermierListInfermierComponent,
  InfermierViewInfermierComponent,
  InfermierEditInfermierComponent,
  MedecinCreateInfermierComponent,
  MedecinListInfermierComponent,
  MedecinViewInfermierComponent,
  MedecinEditInfermierComponent,
  ],
})
export class CommunInfermierModule { }
