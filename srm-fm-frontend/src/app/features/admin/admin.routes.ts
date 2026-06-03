import { Routes } from '@angular/router';
import { AdminLayoutComponent } from '../../layout/admin-layout/admin-layout.component';

export const ADMIN_ROUTES: Routes = [
  {
    path: '',
    component: AdminLayoutComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', loadComponent: () => import('./dashboard/dashboard.component').then(m => m.AdminDashboardComponent) },
      { path: 'users', loadComponent: () => import('./users/users-list/users-list.component').then(m => m.UsersListComponent) },
      { path: 'users/:id', loadComponent: () => import('./users/users-form/users-form.component').then(m => m.UsersFormComponent) },
      { path: 'permutations', loadComponent: () => import('./permutations/permutations-list/permutations-list.component').then(m => m.PermutationsListComponent) },
      { path: 'analytics', loadComponent: () => import('./analytics/analytics.component').then(m => m.AnalyticsComponent) },
      { path: 'specialites', loadComponent: () => import('./specialites/specialites-list/specialites-list.component').then(m => m.SpecialitesListComponent) },
      { path: 'specialites/:id', loadComponent: () => import('./specialites/specialites-form/specialites-form.component').then(m => m.SpecialitesFormComponent) },
      
      // Routes pour les familles (corrigées)
      { path: 'familles', loadComponent: () => import('./familles/familles-list/familles-list.component').then(m => m.FamillesListComponent) },
      { path: 'familles/new', loadComponent: () => import('./familles/familles-form/familles-form.component').then(m => m.FamillesFormComponent) },
      { path: 'familles/:id/edit', loadComponent: () => import('./familles/familles-form/familles-form.component').then(m => m.FamillesFormComponent) },
      
      { path: 'interventions', loadComponent: () => import('./interventions/interventions-list/interventions-list.component').then(m => m.InterventionsListComponent) },
      
      // Routes pour les types (corrigées)
      { path: 'types', loadComponent: () => import('./types/types-list/types-list.component').then(m => m.TypesListComponent) },
      { path: 'types/new', loadComponent: () => import('./types/types-form/types-form.component').then(m => m.TypesFormComponent) },
      { path: 'types/:id/edit', loadComponent: () => import('./types/types-form/types-form.component').then(m => m.TypesFormComponent) },
      
      { path: 'equipes', loadComponent: () => import('./equipe/equipe-list/equipes-list.component').then(m => m.EquipesListComponent) },
      { path: 'referentiels', loadComponent: () => import('./referentiels/referentiels.component').then(m => m.ReferentielsComponent) }
    ]
  }
];