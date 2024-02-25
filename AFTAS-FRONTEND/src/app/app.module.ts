import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  CommonModule, LocationStrategy,
  PathLocationStrategy
} from '@angular/common';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatDialogModule } from '@angular/material/dialog';

import { FullComponent } from './layouts/full/full.component';


import { NavigationComponent } from './shared/header/navigation.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';

import { Approutes } from './app-routing.module';
import { AppComponent } from './app.component';
import { SpinnerComponent } from './shared/spinner.component';
import { DataTablesModule } from 'angular-datatables';
import { MemberService } from './service/member.service';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import {AccessDeniedComponent} from "./component/access-denied/access-denied.component";
import {HttpInterceptor} from "./helpers/http.interceptor";
import {MatIconModule} from "@angular/material/icon";
import { ForbiddenComponent } from './errors/forbidden/forbidden.component';
import { MemberDashboardComponent } from './member-dashboard/member-dashboard.component';
import { JuryDashboardComponent } from './jury-dashboard/jury-dashboard.component';
import { MemberSidebarComponent } from './shared/member-sidebar/member-sidebar.component';
import { JurySidebarComponent } from './shared/jury-sidebar/jury-sidebar.component';
import { MemberLayoutComponent } from './layouts/member-layout/member-layout.component';
import { JuryLayoutComponent } from './layouts/jury-layout/jury-layout.component';
import {JwtInterceptor} from "./helpers/jwt.interceptor";
import {MatPaginatorModule} from "@angular/material/paginator";


@NgModule({
  declarations: [
    AppComponent,
    SpinnerComponent,
    LoginComponent,
    RegisterComponent,
    AccessDeniedComponent,
    ForbiddenComponent,
    MemberDashboardComponent,
    JuryDashboardComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    MatDialogModule,
    DataTablesModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    RouterModule.forRoot(Approutes, {useHash: false}),
    FullComponent,
    MemberLayoutComponent,
    JuryLayoutComponent,
    SidebarComponent,
    MemberSidebarComponent,
    JurySidebarComponent,
    NavigationComponent,
    MatIconModule,
    MatPaginatorModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: HttpInterceptor, multi: true },
    MemberService,
    {
      provide: LocationStrategy,
      useClass: PathLocationStrategy
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
