import { Routes } from '@angular/router';
import { TechnicienLayoutComponent } from '../../layout/technicien-layout/technicien-layout.component';

export const TECHNICIEN_ROUTES: Routes = [
  {
    path: '',
    component: TechnicienLayoutComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', loadComponent: () => import('./dashboard/technicien-dashboard.component').then(m => m.TechnicienDashboardComponent) },
      { path: 'interventions', loadComponent: () => import('./interventions/intervention-list/interventions-list.component').then(m => m.InterventionsListComponent) }
    ]
  }
];