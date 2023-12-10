import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EleveComponent } from './eleve.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { Routes, RouterModule } from "@angular/router";
import { NgApexchartsModule } from "ng-apexcharts";



const routes: Routes = [
  {
    path: "",   
    data: {
      title: "eleve",
      urls: [{ title: "eleve", url: "/eleve" }, { title: "eleve" }],
    },
    component: EleveComponent,
  },
];


@NgModule({
  declarations: [
    EleveComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    RouterModule.forChild(routes),
    NgApexchartsModule,
  ]
})
export class EleveModule { }



