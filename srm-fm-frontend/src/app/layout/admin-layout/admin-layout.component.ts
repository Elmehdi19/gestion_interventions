import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from '../../shared/components/navbar/navbar.component';
import { SidebarComponent, MenuItem } from '../../shared/components/sidebar/sidebar.component';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-admin-layout',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, SidebarComponent],
  template: `
    <div class="flex h-screen">
      <app-sidebar
        [menuItems]="adminMenu"
        appName="SRM-FM Control"
        subtitle="Infrastructure Management"
        activeRoute="/admin/dashboard"
        (logout)="onLogout()">
      </app-sidebar>
      <div class="flex-1 flex flex-col ml-64">
        <app-navbar></app-navbar>
        <main class="flex-1 overflow-y-auto bg-surface p-gutter">
          <router-outlet></router-outlet>
        </main>
      </div>
    </div>
  `
})
export class AdminLayoutComponent {
  adminMenu: MenuItem[] = [
    { label: 'Dashboard', route: '/admin/dashboard', icon: 'dashboard' },
    { label: 'Utilisateurs', route: '/admin/users', icon: 'settings_accessibility' },
    { label: 'Référentiels', route: '/admin/referentiels', icon: 'folder_special' },   // ← nouvel élément
    { label: 'Équipes', route: '/admin/equipes', icon: 'groups' },
    { label: 'Permutations', route: '/admin/permutations', icon: 'swap_horiz' },
    { label: 'Analytics', route: '/admin/analytics', icon: 'analytics' }
  ];

  constructor(private authService: AuthService) {}

  onLogout() {
    this.authService.logout();
  }
}