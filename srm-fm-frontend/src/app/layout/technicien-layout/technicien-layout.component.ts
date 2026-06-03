import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from '../../shared/components/navbar/navbar.component';
import { SidebarComponent, MenuItem } from '../../shared/components/sidebar/sidebar.component';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-technicien-layout',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, SidebarComponent],
  template: `
    <div class="flex h-screen">
      <app-sidebar
        [menuItems]="menu"
        appName="SRM-FM Control"
        subtitle="Agent technique"
        activeRoute="/technicien/dashboard"
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
export class TechnicienLayoutComponent {
  menu: MenuItem[] = [
    { label: 'Dashboard', route: '/technicien/dashboard', icon: 'dashboard' },
    { label: 'Mes interventions', route: '/technicien/interventions', icon: 'build' }
  ];

  constructor(private authService: AuthService) {}

  onLogout() {
    this.authService.logout();
  }
}