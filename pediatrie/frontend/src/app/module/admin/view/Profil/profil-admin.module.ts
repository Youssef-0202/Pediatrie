import {NgModule} from "@angular/core";
import {SexeCreateAdminComponent} from "../commun/sexe/create/sexe-create-admin.component";
import {SexeListAdminComponent} from "../commun/sexe/list/sexe-list-admin.component";
import {SexeViewAdminComponent} from "../commun/sexe/view/sexe-view-admin.component";
import {SexeEditAdminComponent} from "../commun/sexe/edit/sexe-edit-admin.component";
import {InfermierCreateAdminComponent} from "../commun/infermier/create/infermier-create-admin.component";
import {InfermierListAdminComponent} from "../commun/infermier/list/infermier-list-admin.component";
import {InfermierViewAdminComponent} from "../commun/infermier/view/infermier-view-admin.component";
import {InfermierEditAdminComponent} from "../commun/infermier/edit/infermier-edit-admin.component";
import {MedecinCreateAdminComponent} from "../commun/medecin/create/medecin-create-admin.component";
import {MedecinListAdminComponent} from "../commun/medecin/list/medecin-list-admin.component";
import {MedecinViewAdminComponent} from "../commun/medecin/view/medecin-view-admin.component";
import {MedecinEditAdminComponent} from "../commun/medecin/edit/medecin-edit-admin.component";
import {CommonModule} from "@angular/common";
import {ToastModule} from "primeng/toast";
import {ToolbarModule} from "primeng/toolbar";
import {TableModule} from "primeng/table";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {DialogModule} from "primeng/dialog";
import {PasswordModule} from "primeng/password";
import {InputTextModule} from "primeng/inputtext";
import {ButtonModule} from "primeng/button";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {SplitButtonModule} from "primeng/splitbutton";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {DropdownModule} from "primeng/dropdown";
import {TabViewModule} from "primeng/tabview";
import {InputSwitchModule} from "primeng/inputswitch";
import {InputTextareaModule} from "primeng/inputtextarea";
import {CalendarModule} from "primeng/calendar";
import {PanelModule} from "primeng/panel";
import {MessageModule} from "primeng/message";
import {MessagesModule} from "primeng/messages";
import {InputNumberModule} from "primeng/inputnumber";
import {BadgeModule} from "primeng/badge";
import {MultiSelectModule} from "primeng/multiselect";
import {PaginatorModule} from "primeng/paginator";
import {TranslateModule} from "@ngx-translate/core";
import {FileUploadModule} from "primeng/fileupload";
import {FullCalendarModule} from "@fullcalendar/angular";
import {CardModule} from "primeng/card";
import {EditorModule} from "primeng/editor";
import {ProfilComponent} from "./display/profil.component";
import {
    PatientContactCreateAdminComponent
} from "../patient/patient-contact/create/patient-contact-create-admin.component";
import {PatientContactListAdminComponent} from "../patient/patient-contact/list/patient-contact-list-admin.component";
import {PatientContactViewAdminComponent} from "../patient/patient-contact/view/patient-contact-view-admin.component";
import {PatientContactEditAdminComponent} from "../patient/patient-contact/edit/patient-contact-edit-admin.component";
import {RelationCreateAdminComponent} from "../patient/relation/create/relation-create-admin.component";
import {RelationListAdminComponent} from "../patient/relation/list/relation-list-admin.component";
import {RelationViewAdminComponent} from "../patient/relation/view/relation-view-admin.component";
import {RelationEditAdminComponent} from "../patient/relation/edit/relation-edit-admin.component";
import {PatientCreateAdminComponent} from "../patient/patient/create/patient-create-admin.component";
import {PatientListAdminComponent} from "../patient/patient/list/patient-list-admin.component";
import {PatientViewAdminComponent} from "../patient/patient/view/patient-view-admin.component";
import {PatientEditAdminComponent} from "../patient/patient/edit/patient-edit-admin.component";
import {RippleModule} from "primeng/ripple";

@NgModule(
    {
        declarations: [
           ProfilComponent
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
            RippleModule,

        ],
        exports: [
           ProfilComponent
        ],
    }
)
export class ProfilAdminModule{}
