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

import { AntecedentCreateAdminComponent } from './antecedent/create/antecedent-create-admin.component';
import { AntecedentEditAdminComponent } from './antecedent/edit/antecedent-edit-admin.component';
import { AntecedentViewAdminComponent } from './antecedent/view/antecedent-view-admin.component';
import { AntecedentListAdminComponent } from './antecedent/list/antecedent-list-admin.component';
import { FichePatientCreateAdminComponent } from './fiche-patient/create/fiche-patient-create-admin.component';
import { FichePatientEditAdminComponent } from './fiche-patient/edit/fiche-patient-edit-admin.component';
import { FichePatientViewAdminComponent } from './fiche-patient/view/fiche-patient-view-admin.component';
import { FichePatientListAdminComponent } from './fiche-patient/list/fiche-patient-list-admin.component';
import { TypeImageCreateAdminComponent } from './type-image/create/type-image-create-admin.component';
import { TypeImageEditAdminComponent } from './type-image/edit/type-image-edit-admin.component';
import { TypeImageViewAdminComponent } from './type-image/view/type-image-view-admin.component';
import { TypeImageListAdminComponent } from './type-image/list/type-image-list-admin.component';
import { EpreuveCreateAdminComponent } from './epreuve/create/epreuve-create-admin.component';
import { EpreuveEditAdminComponent } from './epreuve/edit/epreuve-edit-admin.component';
import { EpreuveViewAdminComponent } from './epreuve/view/epreuve-view-admin.component';
import { EpreuveListAdminComponent } from './epreuve/list/epreuve-list-admin.component';
import { AnalyseMedicaleCreateAdminComponent } from './analyse-medicale/create/analyse-medicale-create-admin.component';
import { AnalyseMedicaleEditAdminComponent } from './analyse-medicale/edit/analyse-medicale-edit-admin.component';
import { AnalyseMedicaleViewAdminComponent } from './analyse-medicale/view/analyse-medicale-view-admin.component';
import { AnalyseMedicaleListAdminComponent } from './analyse-medicale/list/analyse-medicale-list-admin.component';
import { RadiologieCreateAdminComponent } from './radiologie/create/radiologie-create-admin.component';
import { RadiologieEditAdminComponent } from './radiologie/edit/radiologie-edit-admin.component';
import { RadiologieViewAdminComponent } from './radiologie/view/radiologie-view-admin.component';
import { RadiologieListAdminComponent } from './radiologie/list/radiologie-list-admin.component';
import { GroupeSanguinCreateAdminComponent } from './groupe-sanguin/create/groupe-sanguin-create-admin.component';
import { GroupeSanguinEditAdminComponent } from './groupe-sanguin/edit/groupe-sanguin-edit-admin.component';
import { GroupeSanguinViewAdminComponent } from './groupe-sanguin/view/groupe-sanguin-view-admin.component';
import { GroupeSanguinListAdminComponent } from './groupe-sanguin/list/groupe-sanguin-list-admin.component';

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

    AntecedentCreateAdminComponent,
    AntecedentListAdminComponent,
    AntecedentViewAdminComponent,
    AntecedentEditAdminComponent,
    FichePatientCreateAdminComponent,
    FichePatientListAdminComponent,
    FichePatientViewAdminComponent,
    FichePatientEditAdminComponent,
    TypeImageCreateAdminComponent,
    TypeImageListAdminComponent,
    TypeImageViewAdminComponent,
    TypeImageEditAdminComponent,
    EpreuveCreateAdminComponent,
    EpreuveListAdminComponent,
    EpreuveViewAdminComponent,
    EpreuveEditAdminComponent,
    AnalyseMedicaleCreateAdminComponent,
    AnalyseMedicaleListAdminComponent,
    AnalyseMedicaleViewAdminComponent,
    AnalyseMedicaleEditAdminComponent,
    RadiologieCreateAdminComponent,
    RadiologieListAdminComponent,
    RadiologieViewAdminComponent,
    RadiologieEditAdminComponent,
    GroupeSanguinCreateAdminComponent,
    GroupeSanguinListAdminComponent,
    GroupeSanguinViewAdminComponent,
    GroupeSanguinEditAdminComponent,
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
  AntecedentCreateAdminComponent,
  AntecedentListAdminComponent,
  AntecedentViewAdminComponent,
  AntecedentEditAdminComponent,
  FichePatientCreateAdminComponent,
  FichePatientListAdminComponent,
  FichePatientViewAdminComponent,
  FichePatientEditAdminComponent,
  TypeImageCreateAdminComponent,
  TypeImageListAdminComponent,
  TypeImageViewAdminComponent,
  TypeImageEditAdminComponent,
  EpreuveCreateAdminComponent,
  EpreuveListAdminComponent,
  EpreuveViewAdminComponent,
  EpreuveEditAdminComponent,
  AnalyseMedicaleCreateAdminComponent,
  AnalyseMedicaleListAdminComponent,
  AnalyseMedicaleViewAdminComponent,
  AnalyseMedicaleEditAdminComponent,
  RadiologieCreateAdminComponent,
  RadiologieListAdminComponent,
  RadiologieViewAdminComponent,
  RadiologieEditAdminComponent,
  GroupeSanguinCreateAdminComponent,
  GroupeSanguinListAdminComponent,
  GroupeSanguinViewAdminComponent,
  GroupeSanguinEditAdminComponent,
  ],
})
export class DossieAdminModule { }
