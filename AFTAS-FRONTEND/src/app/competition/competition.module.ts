import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";
import { NgApexchartsModule } from "ng-apexcharts";
import { DataTablesModule } from 'angular-datatables';
import { CompetitionComponent } from "./competition.component";
import { MatPaginatorModule } from '@angular/material/paginator';


const routes: Routes = [
  {
    path: "",
    data: {
      title: "Competition",
      urls: [{ title: "Competiton", url: "/competition" }, { title: "Competition" }],
    },
    component: CompetitionComponent,
  },
];

@NgModule({
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    RouterModule.forChild(routes),
    NgApexchartsModule,
    DataTablesModule,
    MatPaginatorModule
  ],
  declarations: [
    CompetitionComponent,
  ],
})
export class CompetitionModule {}
