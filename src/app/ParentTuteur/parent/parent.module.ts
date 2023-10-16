import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";
import { NgApexchartsModule } from "ng-apexcharts";
import { ParentComponent } from "./parent.component";
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
      title: "Parent",
      urls: [{ title: "Parent", url: "/parent" }, { title: "Parent" }],
    },
    component: ParentComponent,
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
    ParentComponent,
  ],
})
export class ParentModule {}
