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
import { LoginMedecinComponent } from './login-medecin/login-medecin.component';
import { RegisterMedecinComponent } from './register-medecin/register-medecin.component';
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

import { DossieMedecinModule } from './view/dossie/dossie-medecin.module';
import { DossieMedecinRoutingModule } from './view/dossie/dossie-medecin-routing.module';
import { RapporMedecinModule } from './view/rappor/rappor-medecin.module';
import { RapporMedecinRoutingModule } from './view/rappor/rappor-medecin-routing.module';
import { PatientMedecinModule } from './view/patient/patient-medecin.module';
import { PatientMedecinRoutingModule } from './view/patient/patient-medecin-routing.module';
import { ConsultatioMedecinModule } from './view/consultatio/consultatio-medecin.module';
import { ConsultatioMedecinRoutingModule } from './view/consultatio/consultatio-medecin-routing.module';
import { CommunMedecinModule } from './view/commun/commun-medecin.module';
import { CommunMedecinRoutingModule } from './view/commun/commun-medecin-routing.module';
import { GestioMedecinModule } from './view/gestio/gestio-medecin.module';
import { GestioMedecinRoutingModule } from './view/gestio/gestio-medecin-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';
import {ProfilMedecinRoutingModule} from "./view/Profil/profil-medecin-routing.module";
import {ProfilMedecinModule} from "./view/Profil/profil-medecin.module";
import {StatistiqueMedecinComponent} from "./view/satistique/statistique-medecin/statistique-medecin.component";
import {StatistiqueModule} from "./view/satistique/statistique.module";


@NgModule({
  declarations: [
   LoginMedecinComponent,
   RegisterMedecinComponent
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
  DossieMedecinModule,
  DossieMedecinRoutingModule,
  RapporMedecinModule,
  RapporMedecinRoutingModule,
  PatientMedecinModule,
  PatientMedecinRoutingModule,
  ConsultatioMedecinModule,
  ConsultatioMedecinRoutingModule,
  CommunMedecinModule,
  CommunMedecinRoutingModule,
  GestioMedecinModule,
  GestioMedecinRoutingModule,
  SecurityModule,
  SecurityRoutingModule,
      ProfilMedecinRoutingModule
  ],
  exports: [
  LoginMedecinComponent,
  RegisterMedecinComponent,

    DossieMedecinModule,
    RapporMedecinModule,
    PatientMedecinModule,
    ConsultatioMedecinModule,
    CommunMedecinModule,
    GestioMedecinModule,
  SecurityModule,
      ProfilMedecinModule,
     StatistiqueModule
  ],

})
export class MedecinModule { }
