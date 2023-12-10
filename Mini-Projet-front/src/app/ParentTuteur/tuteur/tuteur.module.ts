import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";
import { NgApexchartsModule } from "ng-apexcharts";
import { TuteurComponent } from "./tuteur.component";
const routes: Routes = [
  {
    path: "",   
    data: {
      title: "Tuteur",
      urls: [{ title: "Tuteur", url: "/tuteur" }, { title: "Tuteur" }],
    },
    component: TuteurComponent,
  },
];

@NgModule({
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    RouterModule.forChild(routes),
    NgApexchartsModule,
  ],
  declarations: [
    TuteurComponent,
  ],
})
export class TuteurModule {}
