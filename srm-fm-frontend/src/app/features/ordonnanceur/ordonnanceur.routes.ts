import { Routes } from '@angular/router';
import { OrdonnanceurLayoutComponent } from '../../layout/ordonanceur-layout/ordonnanceur-layout.component';

export const ORDONNANCEUR_ROUTES: Routes = [
  {
    path: '',
    component: OrdonnanceurLayoutComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', loadComponent: () => import('./dashboard/ordonnaceur-dashboard.component').then(m => m.OrdonnanceurDashboardComponent) },
      { path: 'claims', loadComponent: () => import('./claims/claims-list/claims-list.component').then(m => m.ClaimsListComponent) },
      // ⚠️ Route fixe AVANT la route avec paramètre
      { path: 'claims/qualify', loadComponent: () => import('./claims/claims-qualify-list/claims-qualify-list.component').then(m => m.ClaimsQualifyListComponent) },
      // Route paramétrée APRÈS
      { path: 'claims/:id/qualify', loadComponent: () => import('./claims/claims-qualify/claims-qualify.component').then(m => m.ClaimsQualifyComponent) },
      { path: 'interventions/new', loadComponent: () => import('./interventions/intervention-create/intervention-create.component').then(m => m.InterventionCreateComponent) },
      { path: 'interventions', loadComponent: () => import('./interventions/interventions-list/intervention-list.component').then(m => m.InterventionsListComponent) }
    ]
  }
];