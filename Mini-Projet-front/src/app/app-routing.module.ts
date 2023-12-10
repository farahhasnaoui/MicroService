import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FullComponent } from './layouts/full/full.component';


export const Approutes: Routes = [
  {
    path: 'auth', 
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
  },
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
      }, {
        path: 'enseignant',
        loadChildren: () => import('./enseignant/ensignant.module').then(m => m.EnseignantModule)
      } , {
        path: 'contrat',
        loadChildren: () => import('./contrat/contrat.module').then(m => m.ContratModule)
      } ,
      {
        path: 'activite',
        loadChildren: () => import('./programme-activite/ProgActivite.module').then(m => m.ProgActiveModule)
      } ,
      {
        path: 'programme',
        loadChildren: () => import('./activite-programme/activite.module').then(m => m.ActiviteModule)
      } , {
        path: 'eleve',
        loadChildren: () => import('./eleve/eleve.module').then(m => m.EleveModule)
      },
      {
       path: 'classe',
       loadChildren: () => import('./classe/classe.module').then(m => m.ClasseModule)
     },
     {
      path: 'parent',
      loadChildren: () => import('./ParentTuteur/parent/parent.module').then(m => m.ParentModule)
    } ,
    {
     path: 'tuteur',
     loadChildren: () => import('./ParentTuteur/tuteur/tuteur.module').then(m => m.TuteurModule)
   } ,
  
     
    ]
  },
  {
    path: '**',
    redirectTo: '/starter'
  }
];
