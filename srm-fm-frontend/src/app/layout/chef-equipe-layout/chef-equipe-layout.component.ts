import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../core/services/auth.service';
import { MenuItem } from '../../shared/components/sidebar/sidebar.component';

@Component({
  selector: 'app-chef-equipe-layout',
  standalone: true,
  imports: [RouterOutlet, CommonModule, RouterLink, RouterLinkActive],
  template: `
    <div class="flex h-screen">
      <!-- Sidebar -->
      <aside class="h-screen w-64 fixed left-0 top-0 bg-surface-container-low border-r border-outline-variant flex flex-col py-md gap-2 z-50">
        <div class="px-6 py-4 mb-4">
          <h1 class="text-headline-md font-headline-md font-bold text-primary">SRM-FM Control</h1>
          <p class="text-label-md text-on-surface-variant">Chef d'équipe</p>
        </div>
        <nav class="flex-grow space-y-1">
          <a *ngFor="let item of menuItems"
             [routerLink]="item.route"
             routerLinkActive="bg-primary-container text-on-primary-container rounded-lg font-semibold"
             class="text-on-surface-variant hover:text-on-surface mx-2 px-4 py-3 flex items-center gap-3 hover:bg-surface-variant transition-all active:scale-[0.98]">
            <span class="material-symbols-outlined">{{ item.icon }}</span>
            <span class="font-label-md text-label-md font-medium">{{ item.label }}</span>
          </a>
        </nav>
        <div class="px-4 py-4 mt-auto border-t border-outline-variant">
          <div class="space-y-1">
            <a class="text-on-surface-variant hover:text-on-surface px-4 py-2 flex items-center gap-3 font-label-md text-label-md cursor-pointer" (click)="logout()">
              <span class="material-symbols-outlined">logout</span> Déconnexion
            </a>
          </div>
        </div>
      </aside>

      <!-- Main content -->
      <div class="flex-1 flex flex-col ml-64">
        <header class="flex justify-between items-center h-16 px-gutter w-full bg-surface border-b border-outline-variant sticky top-0 z-40 shadow-sm">
          <h2 class="text-title-lg font-bold text-primary">SRM-FM</h2>
          <div class="flex items-center gap-4">
            <span class="text-body-md text-on-surface-variant">{{ userDisplayName }}</span>
          </div>
        </header>
        <main class="flex-1 overflow-y-auto bg-surface p-gutter">
          <router-outlet></router-outlet>
        </main>
      </div>
    </div>
  `
})
export class ChefEquipeLayoutComponent {
  menuItems: MenuItem[] = [
    { label: 'Dashboard', route: '/chef-equipe/dashboard', icon: 'dashboard' },
    { label: 'Interventions', route: '/chef-equipe/interventions', icon: 'assignment' },
    { label: 'Techniciens', route: '/chef-equipe/techniciens', icon: 'people' },
    { label: 'Équipes', route: '/chef-equipe/equipes', icon: 'groups' },
    { label: 'Performances techniciens', route: '/chef-equipe/performances/techniciens', icon: 'bar_chart' },
    { label: 'Performances équipes', route: '/chef-equipe/performances/equipes', icon: 'stacked_line_chart' },
    { label: 'Demander permutation', route: '/chef-equipe/permutations/demander', icon: 'swap_horiz' }
  ];

  userDisplayName = '';

  constructor(private authService: AuthService) {
    this.userDisplayName = this.authService.getEmail() || 'Chef équipe';
  }

  logout() {
    this.authService.logout();
  }
}