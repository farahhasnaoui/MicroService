import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FullComponent } from './layouts/full/full.component';


export const Approutes: Routes = [
  {
    path: '',
    component: FullComponent,
    children: [
      { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
      {
        path: 'dashboard',
        loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)
      },
      {
        path: 'about',
        loadChildren: () => import('./about/about.module').then(m => m.AboutModule)
      },
      {
        path: 'component',
        loadChildren: () => import('./component/component.module').then(m => m.ComponentsModule)
      } ,
       {
        path: 'parent',
        loadChildren: () => import('./ParentTuteur/parent/parent.module').then(m => m.ParentModule)
      }, {
        path: 'enseignant',
        loadChildren: () => import('./enseignant/ensignant.module').then(m => m.EnseignantModule)
      } , {
        path: 'contrat',
        loadChildren: () => import('./contrat/contrat.module').then(m => m.ContratModule)
      } 
    ]
  },
  {
    path: '**',
    redirectTo: '/starter'
  }
];
