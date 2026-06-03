import { Routes } from '@angular/router';
import { ChefEquipeLayoutComponent } from '../../layout/chef-equipe-layout/chef-equipe-layout.component';

export const CHEF_EQUIPE_ROUTES: Routes = [
  {
    path: '',
    component: ChefEquipeLayoutComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', loadComponent: () => import('./dashboard/chef-equipe-dashboard.component').then(m => m.ChefEquipeDashboardComponent) },
      { path: 'interventions', loadComponent: () => import('./interventions/interventions-list/interventions-list.component').then(m => m.InterventionsListComponent) },
      { path: 'techniciens', loadComponent: () => import('./techniciens/techniciens-list/techniciens-list.component').then(m => m.TechniciensListComponent) },
      
      // Performances
      { path: 'performances/techniciens', loadComponent: () => import('./performances/performances.component').then(m => m.PerformancesTechniciensComponent) },
      { path : 'performances/equipes', loadComponent: () => import('./equipe/performances/performances-equipes.component').then(m => m.PerformancesEquipesComponent) },
      // Permutations
      { path: 'permutations/demander', loadComponent: () => import('./permutations/demander-permutation/demander-permutation.component').then(m => m.DemanderPermutationComponent) },
      
      // Gestion des équipes (CRUD)
      { path: 'equipes', loadComponent: () => import('./equipe/equipe-list/equipe-list.component').then(m => m.EquipeListComponent) },
      { path: 'equipes/new', loadComponent: () => import('./equipe/equipe-form/equipe-form.component').then(m => m.EquipeFormComponent) },
      { path: 'equipes/:id/edit', loadComponent: () => import('./equipe/equipe-form/equipe-form.component').then(m => m.EquipeFormComponent) }
    ]
  }
];