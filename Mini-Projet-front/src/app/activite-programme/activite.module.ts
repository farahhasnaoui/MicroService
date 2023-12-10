import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";
import { NgApexchartsModule } from "ng-apexcharts";
import { ActiviteProgrammeComponent } from "./activite-programme.component";
// import { DashboardComponent } from "./dashboard.component";
// import { SalesRatioComponent } from "./dashboard-components/sales-ratio/sales-ratio.component";
// import { FeedsComponent } from "./dashboard-components/feeds/feeds.component";
// import { TopSellingComponent } from "./dashboard-components/top-selling/top-selling.component";
// import { TopCardsComponent } from "./dashboard-components/top-cards/top-cards.component";
// import { BlogCardsComponent } from "./dashboard-components/blog-cards/blog-cards.component";


const routes: Routes = [
  {
    path: "",   
    data: {
      title: "Programme",
      urls: [{ title: "Programme", url: "/programme" }, { title: "Programme" }],
    },
    component: ActiviteProgrammeComponent,
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
    ActiviteProgrammeComponent,
  ],
})
export class ActiviteModule {}
