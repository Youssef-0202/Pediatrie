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

import { AntecedentCreateMedecinComponent } from './antecedent/create/antecedent-create-medecin.component';
import { AntecedentEditMedecinComponent } from './antecedent/edit/antecedent-edit-medecin.component';
import { AntecedentViewMedecinComponent } from './antecedent/view/antecedent-view-medecin.component';
import { AntecedentListMedecinComponent } from './antecedent/list/antecedent-list-medecin.component';
import { FichePatientCreateMedecinComponent } from './fiche-patient/create/fiche-patient-create-medecin.component';
import { FichePatientEditMedecinComponent } from './fiche-patient/edit/fiche-patient-edit-medecin.component';
import { FichePatientViewMedecinComponent } from './fiche-patient/view/fiche-patient-view-medecin.component';
import { FichePatientListMedecinComponent } from './fiche-patient/list/fiche-patient-list-medecin.component';
import { TypeImageCreateMedecinComponent } from './type-image/create/type-image-create-medecin.component';
import { TypeImageEditMedecinComponent } from './type-image/edit/type-image-edit-medecin.component';
import { TypeImageViewMedecinComponent } from './type-image/view/type-image-view-medecin.component';
import { TypeImageListMedecinComponent } from './type-image/list/type-image-list-medecin.component';
import { EpreuveCreateMedecinComponent } from './epreuve/create/epreuve-create-medecin.component';
import { EpreuveEditMedecinComponent } from './epreuve/edit/epreuve-edit-medecin.component';
import { EpreuveViewMedecinComponent } from './epreuve/view/epreuve-view-medecin.component';
import { EpreuveListMedecinComponent } from './epreuve/list/epreuve-list-medecin.component';
import { AnalyseMedicaleCreateMedecinComponent } from './analyse-medicale/create/analyse-medicale-create-medecin.component';
import { AnalyseMedicaleEditMedecinComponent } from './analyse-medicale/edit/analyse-medicale-edit-medecin.component';
import { AnalyseMedicaleViewMedecinComponent } from './analyse-medicale/view/analyse-medicale-view-medecin.component';
import { AnalyseMedicaleListMedecinComponent } from './analyse-medicale/list/analyse-medicale-list-medecin.component';
import { RadiologieCreateMedecinComponent } from './radiologie/create/radiologie-create-medecin.component';
import { RadiologieEditMedecinComponent } from './radiologie/edit/radiologie-edit-medecin.component';
import { RadiologieViewMedecinComponent } from './radiologie/view/radiologie-view-medecin.component';
import { RadiologieListMedecinComponent } from './radiologie/list/radiologie-list-medecin.component';
import { GroupeSanguinCreateMedecinComponent } from './groupe-sanguin/create/groupe-sanguin-create-medecin.component';
import { GroupeSanguinEditMedecinComponent } from './groupe-sanguin/edit/groupe-sanguin-edit-medecin.component';
import { GroupeSanguinViewMedecinComponent } from './groupe-sanguin/view/groupe-sanguin-view-medecin.component';
import { GroupeSanguinListMedecinComponent } from './groupe-sanguin/list/groupe-sanguin-list-medecin.component';

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

    AntecedentCreateMedecinComponent,
    AntecedentListMedecinComponent,
    AntecedentViewMedecinComponent,
    AntecedentEditMedecinComponent,
    FichePatientCreateMedecinComponent,
    FichePatientListMedecinComponent,
    FichePatientViewMedecinComponent,
    FichePatientEditMedecinComponent,
    TypeImageCreateMedecinComponent,
    TypeImageListMedecinComponent,
    TypeImageViewMedecinComponent,
    TypeImageEditMedecinComponent,
    EpreuveCreateMedecinComponent,
    EpreuveListMedecinComponent,
    EpreuveViewMedecinComponent,
    EpreuveEditMedecinComponent,
    AnalyseMedicaleCreateMedecinComponent,
    AnalyseMedicaleListMedecinComponent,
    AnalyseMedicaleViewMedecinComponent,
    AnalyseMedicaleEditMedecinComponent,
    RadiologieCreateMedecinComponent,
    RadiologieListMedecinComponent,
    RadiologieViewMedecinComponent,
    RadiologieEditMedecinComponent,
    GroupeSanguinCreateMedecinComponent,
    GroupeSanguinListMedecinComponent,
    GroupeSanguinViewMedecinComponent,
    GroupeSanguinEditMedecinComponent,
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
  AntecedentCreateMedecinComponent,
  AntecedentListMedecinComponent,
  AntecedentViewMedecinComponent,
  AntecedentEditMedecinComponent,
  FichePatientCreateMedecinComponent,
  FichePatientListMedecinComponent,
  FichePatientViewMedecinComponent,
  FichePatientEditMedecinComponent,
  TypeImageCreateMedecinComponent,
  TypeImageListMedecinComponent,
  TypeImageViewMedecinComponent,
  TypeImageEditMedecinComponent,
  EpreuveCreateMedecinComponent,
  EpreuveListMedecinComponent,
  EpreuveViewMedecinComponent,
  EpreuveEditMedecinComponent,
  AnalyseMedicaleCreateMedecinComponent,
  AnalyseMedicaleListMedecinComponent,
  AnalyseMedicaleViewMedecinComponent,
  AnalyseMedicaleEditMedecinComponent,
  RadiologieCreateMedecinComponent,
  RadiologieListMedecinComponent,
  RadiologieViewMedecinComponent,
  RadiologieEditMedecinComponent,
  GroupeSanguinCreateMedecinComponent,
  GroupeSanguinListMedecinComponent,
  GroupeSanguinViewMedecinComponent,
  GroupeSanguinEditMedecinComponent,
  ],
})
export class DossieMedecinModule { }
