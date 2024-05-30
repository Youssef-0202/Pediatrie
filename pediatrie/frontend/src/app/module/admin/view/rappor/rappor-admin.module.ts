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

import { DiagnosticCreateAdminComponent } from './diagnostic/create/diagnostic-create-admin.component';
import { DiagnosticEditAdminComponent } from './diagnostic/edit/diagnostic-edit-admin.component';
import { DiagnosticViewAdminComponent } from './diagnostic/view/diagnostic-view-admin.component';
import { DiagnosticListAdminComponent } from './diagnostic/list/diagnostic-list-admin.component';
import { SyntheseMedicaleCreateAdminComponent } from './synthese-medicale/create/synthese-medicale-create-admin.component';
import { SyntheseMedicaleEditAdminComponent } from './synthese-medicale/edit/synthese-medicale-edit-admin.component';
import { SyntheseMedicaleViewAdminComponent } from './synthese-medicale/view/synthese-medicale-view-admin.component';
import { SyntheseMedicaleListAdminComponent } from './synthese-medicale/list/synthese-medicale-list-admin.component';

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

    DiagnosticCreateAdminComponent,
    DiagnosticListAdminComponent,
    DiagnosticViewAdminComponent,
    DiagnosticEditAdminComponent,
    SyntheseMedicaleCreateAdminComponent,
    SyntheseMedicaleListAdminComponent,
    SyntheseMedicaleViewAdminComponent,
    SyntheseMedicaleEditAdminComponent,
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
  DiagnosticCreateAdminComponent,
  DiagnosticListAdminComponent,
  DiagnosticViewAdminComponent,
  DiagnosticEditAdminComponent,
  SyntheseMedicaleCreateAdminComponent,
  SyntheseMedicaleListAdminComponent,
  SyntheseMedicaleViewAdminComponent,
  SyntheseMedicaleEditAdminComponent,
  ],
})
export class RapporAdminModule { }
