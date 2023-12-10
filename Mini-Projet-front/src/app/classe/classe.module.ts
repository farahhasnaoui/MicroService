import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";
import { NgApexchartsModule } from "ng-apexcharts";
import { ClasseComponent } from './classe.component';
import { PieChartComponent } from './pie-chart/pie-chart.component';


const routes: Routes = [
  {
    path: "",   
    data: {
      title: "classe",
      urls: [{ title: "classe", url: "/classe" }, { title: "classe" }],
    },
    component: ClasseComponent,
  },
  {path:'pie',component:PieChartComponent},
 
];


@NgModule({
  declarations: [
    ClasseComponent,
    PieChartComponent,
    
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    RouterModule.forChild(routes),
    NgApexchartsModule,
    
  ]
})
export class ClasseModule { }








