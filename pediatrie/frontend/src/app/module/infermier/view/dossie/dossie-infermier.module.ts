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

import { AntecedentCreateInfermierComponent } from './antecedent/create/antecedent-create-infermier.component';
import { AntecedentEditInfermierComponent } from './antecedent/edit/antecedent-edit-infermier.component';
import { AntecedentViewInfermierComponent } from './antecedent/view/antecedent-view-infermier.component';
import { AntecedentListInfermierComponent } from './antecedent/list/antecedent-list-infermier.component';
import { FichePatientCreateInfermierComponent } from './fiche-patient/create/fiche-patient-create-infermier.component';
import { FichePatientEditInfermierComponent } from './fiche-patient/edit/fiche-patient-edit-infermier.component';
import { FichePatientViewInfermierComponent } from './fiche-patient/view/fiche-patient-view-infermier.component';
import { FichePatientListInfermierComponent } from './fiche-patient/list/fiche-patient-list-infermier.component';
import { TypeImageCreateInfermierComponent } from './type-image/create/type-image-create-infermier.component';
import { TypeImageEditInfermierComponent } from './type-image/edit/type-image-edit-infermier.component';
import { TypeImageViewInfermierComponent } from './type-image/view/type-image-view-infermier.component';
import { TypeImageListInfermierComponent } from './type-image/list/type-image-list-infermier.component';
import { EpreuveCreateInfermierComponent } from './epreuve/create/epreuve-create-infermier.component';
import { EpreuveEditInfermierComponent } from './epreuve/edit/epreuve-edit-infermier.component';
import { EpreuveViewInfermierComponent } from './epreuve/view/epreuve-view-infermier.component';
import { EpreuveListInfermierComponent } from './epreuve/list/epreuve-list-infermier.component';
import { AnalyseMedicaleCreateInfermierComponent } from './analyse-medicale/create/analyse-medicale-create-infermier.component';
import { AnalyseMedicaleEditInfermierComponent } from './analyse-medicale/edit/analyse-medicale-edit-infermier.component';
import { AnalyseMedicaleViewInfermierComponent } from './analyse-medicale/view/analyse-medicale-view-infermier.component';
import { AnalyseMedicaleListInfermierComponent } from './analyse-medicale/list/analyse-medicale-list-infermier.component';
import { RadiologieCreateInfermierComponent } from './radiologie/create/radiologie-create-infermier.component';
import { RadiologieEditInfermierComponent } from './radiologie/edit/radiologie-edit-infermier.component';
import { RadiologieViewInfermierComponent } from './radiologie/view/radiologie-view-infermier.component';
import { RadiologieListInfermierComponent } from './radiologie/list/radiologie-list-infermier.component';
import { GroupeSanguinCreateInfermierComponent } from './groupe-sanguin/create/groupe-sanguin-create-infermier.component';
import { GroupeSanguinEditInfermierComponent } from './groupe-sanguin/edit/groupe-sanguin-edit-infermier.component';
import { GroupeSanguinViewInfermierComponent } from './groupe-sanguin/view/groupe-sanguin-view-infermier.component';
import { GroupeSanguinListInfermierComponent } from './groupe-sanguin/list/groupe-sanguin-list-infermier.component';

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

    AntecedentCreateInfermierComponent,
    AntecedentListInfermierComponent,
    AntecedentViewInfermierComponent,
    AntecedentEditInfermierComponent,
    FichePatientCreateInfermierComponent,
    FichePatientListInfermierComponent,
    FichePatientViewInfermierComponent,
    FichePatientEditInfermierComponent,
    TypeImageCreateInfermierComponent,
    TypeImageListInfermierComponent,
    TypeImageViewInfermierComponent,
    TypeImageEditInfermierComponent,
    EpreuveCreateInfermierComponent,
    EpreuveListInfermierComponent,
    EpreuveViewInfermierComponent,
    EpreuveEditInfermierComponent,
    AnalyseMedicaleCreateInfermierComponent,
    AnalyseMedicaleListInfermierComponent,
    AnalyseMedicaleViewInfermierComponent,
    AnalyseMedicaleEditInfermierComponent,
    RadiologieCreateInfermierComponent,
    RadiologieListInfermierComponent,
    RadiologieViewInfermierComponent,
    RadiologieEditInfermierComponent,
    GroupeSanguinCreateInfermierComponent,
    GroupeSanguinListInfermierComponent,
    GroupeSanguinViewInfermierComponent,
    GroupeSanguinEditInfermierComponent,
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
  AntecedentCreateInfermierComponent,
  AntecedentListInfermierComponent,
  AntecedentViewInfermierComponent,
  AntecedentEditInfermierComponent,
  FichePatientCreateInfermierComponent,
  FichePatientListInfermierComponent,
  FichePatientViewInfermierComponent,
  FichePatientEditInfermierComponent,
  TypeImageCreateInfermierComponent,
  TypeImageListInfermierComponent,
  TypeImageViewInfermierComponent,
  TypeImageEditInfermierComponent,
  EpreuveCreateInfermierComponent,
  EpreuveListInfermierComponent,
  EpreuveViewInfermierComponent,
  EpreuveEditInfermierComponent,
  AnalyseMedicaleCreateInfermierComponent,
  AnalyseMedicaleListInfermierComponent,
  AnalyseMedicaleViewInfermierComponent,
  AnalyseMedicaleEditInfermierComponent,
  RadiologieCreateInfermierComponent,
  RadiologieListInfermierComponent,
  RadiologieViewInfermierComponent,
  RadiologieEditInfermierComponent,
  GroupeSanguinCreateInfermierComponent,
  GroupeSanguinListInfermierComponent,
  GroupeSanguinViewInfermierComponent,
  GroupeSanguinEditInfermierComponent,
  ],
})
export class DossieInfermierModule { }
