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

import { DiagnosticCreateInfermierComponent } from './diagnostic/create/diagnostic-create-infermier.component';
import { DiagnosticEditInfermierComponent } from './diagnostic/edit/diagnostic-edit-infermier.component';
import { DiagnosticViewInfermierComponent } from './diagnostic/view/diagnostic-view-infermier.component';
import { DiagnosticListInfermierComponent } from './diagnostic/list/diagnostic-list-infermier.component';
import { SyntheseMedicaleCreateInfermierComponent } from './synthese-medicale/create/synthese-medicale-create-infermier.component';
import { SyntheseMedicaleEditInfermierComponent } from './synthese-medicale/edit/synthese-medicale-edit-infermier.component';
import { SyntheseMedicaleViewInfermierComponent } from './synthese-medicale/view/synthese-medicale-view-infermier.component';
import { SyntheseMedicaleListInfermierComponent } from './synthese-medicale/list/synthese-medicale-list-infermier.component';

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

    DiagnosticCreateInfermierComponent,
    DiagnosticListInfermierComponent,
    DiagnosticViewInfermierComponent,
    DiagnosticEditInfermierComponent,
    SyntheseMedicaleCreateInfermierComponent,
    SyntheseMedicaleListInfermierComponent,
    SyntheseMedicaleViewInfermierComponent,
    SyntheseMedicaleEditInfermierComponent,
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
  DiagnosticCreateInfermierComponent,
  DiagnosticListInfermierComponent,
  DiagnosticViewInfermierComponent,
  DiagnosticEditInfermierComponent,
  SyntheseMedicaleCreateInfermierComponent,
  SyntheseMedicaleListInfermierComponent,
  SyntheseMedicaleViewInfermierComponent,
  SyntheseMedicaleEditInfermierComponent,
  ],
})
export class RapporInfermierModule { }
