import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {InputTextModule} from 'primeng/inputtext';
import {SidebarModule} from 'primeng/sidebar';
import {BadgeModule} from 'primeng/badge';
import {RadioButtonModule} from 'primeng/radiobutton';
import {InputSwitchModule} from 'primeng/inputswitch';
import {RippleModule} from 'primeng/ripple';
import {AppMenuComponent} from './app.menu.component';
import {AppMenuitemComponent} from './app.menuitem.component';
import {RouterModule} from '@angular/router';
import {AppTopBarComponent} from './app.topbar.component';
import {AppFooterComponent} from './app.footer.component';
import {AppConfigModule} from './config/config.module';
import {AppSidebarComponent} from "./app.sidebar.component";
import {AppLayoutComponent} from "./app.layout.component";
import {PanelMenuModule} from "primeng/panelmenu";
import {TabViewModule} from "primeng/tabview";
import {DialogModule} from "primeng/dialog";
import {TranslateModule} from "@ngx-translate/core";
import {ButtonModule} from "primeng/button";
import {PasswordModule} from "primeng/password";
import {MenuModule} from "primeng/menu";
import {SplitButtonModule} from "primeng/splitbutton";
import {DropdownModule} from "primeng/dropdown";
import {CalendarModule} from "primeng/calendar";
import {CommunAdminModule} from "../module/admin/view/commun/commun-admin.module";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {PaginatorModule} from "primeng/paginator";
import {TableModule} from "primeng/table";
import {ToastModule} from "primeng/toast";
import {ToolbarModule} from "primeng/toolbar";
import {ProfilComponent} from "../module/admin/view/Profil/display/profil.component";
import {ProfilAdminModule} from "../module/admin/view/Profil/profil-admin.module";
import {DossierComponent} from "./dossier_medeical/dossier.component";
import { DashboardComponent } from '../module/medecin/view/dashboard/dashboard.component';
import {AdminModule} from "../module/admin/admin.module";

@NgModule({
    declarations: [
        AppMenuitemComponent,
        AppTopBarComponent,
        AppFooterComponent,
        AppMenuComponent,
        AppSidebarComponent,
        AppLayoutComponent,
        DashboardComponent,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        InputTextModule,
        SidebarModule,
        BadgeModule,
        RadioButtonModule,
        InputSwitchModule,
        RippleModule,
        RouterModule,
        AppConfigModule,
        PanelMenuModule,
        TabViewModule,
        DialogModule,
        TranslateModule,
        ButtonModule,
        PasswordModule,
        MenuModule,
        SplitButtonModule,
        DropdownModule,
        CalendarModule,
        CommunAdminModule,
        ConfirmDialogModule,
        PaginatorModule,
        TableModule,
        ToastModule,
        ToolbarModule,
        DossierComponent,
        AdminModule
    ],
    exports: [AppLayoutComponent]
})
export class AppLayoutModule {
}
