import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';

import { DossieInfermierModule } from './view/dossie/dossie-infermier.module';
import { DossieInfermierRoutingModule } from './view/dossie/dossie-infermier-routing.module';
import { RapporInfermierModule } from './view/rappor/rappor-infermier.module';
import { RapporInfermierRoutingModule } from './view/rappor/rappor-infermier-routing.module';
import { PatientInfermierModule } from './view/patient/patient-infermier.module';
import { PatientInfermierRoutingModule } from './view/patient/patient-infermier-routing.module';
import { ConsultatioInfermierModule } from './view/consultatio/consultatio-infermier.module';
import { ConsultatioInfermierRoutingModule } from './view/consultatio/consultatio-infermier-routing.module';
import { CommunInfermierModule } from './view/commun/commun-infermier.module';
import { CommunInfermierRoutingModule } from './view/commun/commun-infermier-routing.module';
import { GestioInfermierModule } from './view/gestio/gestio-infermier.module';
import { GestioInfermierRoutingModule } from './view/gestio/gestio-infermier-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';
import {ProfilInfermierModule} from "./view/Profil/profil-infermier.module";
import {ProfilInfermierRoutingModule} from "./view/Profil/profil-infermier-routing.module";


@NgModule({
  declarations: [

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
  DossieInfermierModule,
  DossieInfermierRoutingModule,
  RapporInfermierModule,
  RapporInfermierRoutingModule,
  PatientInfermierModule,
  PatientInfermierRoutingModule,
  ConsultatioInfermierModule,
  ConsultatioInfermierRoutingModule,
  CommunInfermierModule,
  CommunInfermierRoutingModule,
  GestioInfermierModule,
  GestioInfermierRoutingModule,
  SecurityModule,
  SecurityRoutingModule,
      ProfilInfermierRoutingModule
  ],
  exports: [


    DossieInfermierModule,
    RapporInfermierModule,
    PatientInfermierModule,
    ConsultatioInfermierModule,
    CommunInfermierModule,
    GestioInfermierModule,
  SecurityModule,
      ProfilInfermierModule
  ],

})
export class InfermierModule { }
