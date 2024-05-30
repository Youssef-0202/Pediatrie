import {NgModule} from "@angular/core";
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
export class ProfilMedecinModule {}
