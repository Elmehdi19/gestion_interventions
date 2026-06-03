import { Routes } from '@angular/router';
import { AuthGuard } from './core/guards/auth-guard';
import { RoleGuard } from './core/guards/role-guard';

export const routes: Routes = [
  // remplacez la ligne 8
{ path: '', loadComponent: () => import('./features/landing/landing.component').then(m => m.LandingComponent) },
  {
    path: 'login',
    loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent)
  },
  {
    path: 'change-password',
    canActivate: [AuthGuard],
    loadComponent: () => import('./features/auth/change-password/change-password.component').then(m => m.ChangePasswordComponent)
  },
  {
    path: 'admin',
    canActivate: [AuthGuard, RoleGuard],
    data: { roles: ['ADMIN'] },
    loadChildren: () => import('./features/admin/admin.routes').then(m => m.ADMIN_ROUTES)
  },
  {
    path: 'ordonnanceur',
    canActivate: [AuthGuard, RoleGuard],
    data: { roles: ['ORDONNANCEUR_ELECTRICITE', 'ORDONNANCEUR_EAU_ASSAINISSEMENT'] },
    loadChildren: () => import('./features/ordonnanceur/ordonnanceur.routes').then(m => m.ORDONNANCEUR_ROUTES)
  },
  {
    path: 'chef-equipe',
    canActivate: [AuthGuard, RoleGuard],
    data: { roles: ['CHEF_EQUIPE'] },
    loadChildren: () => import('./features/chef-equipe/chef-equipe.routes').then(m => m.CHEF_EQUIPE_ROUTES)
  },
  {
    path: 'technicien',
    canActivate: [AuthGuard, RoleGuard],
    data: { roles: ['TECHNICIEN'] },
    loadChildren: () => import('./features/technicien/technicien.routes').then(m => m.TECHNICIEN_ROUTES)
  },
  {
    path: 'client',
    canActivate: [AuthGuard, RoleGuard],
    data: { roles: ['CLIENT'] },
    loadChildren: () => import('./features/client/client.routes').then(m => m.CLIENT_ROUTES)
  },
  { path: '**', redirectTo: '' }
];