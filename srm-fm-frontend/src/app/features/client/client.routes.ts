import { Routes } from '@angular/router';
import { ClientLayoutComponent } from '../../layout/client-layout/client-layout.component';

export const CLIENT_ROUTES: Routes = [
  {
    path: '',
    component: ClientLayoutComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', loadComponent: () => import('./dashboard/client-dashboard.component').then(m => m.ClientDashboardComponent) },
      { path: 'claims', loadComponent: () => import('./claims/claims-list/claims-list.component').then(m => m.ClientClaimsListComponent) },
      { path: 'factures', loadComponent: () => import('./factures/factures-list/factures-list.component').then(m => m.FacturesListComponent) },
      {path: 'claims/new', loadComponent: () => import('./claims/claims-create/claims-create.component').then(m => m.ClaimsCreateComponent) },
      {path: 'consommation', loadComponent: () => import('./consomation/consomation.component').then(m => m.ConsommationComponent) }
    ]
  }
];