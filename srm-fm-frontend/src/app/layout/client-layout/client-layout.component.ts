import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from '../../shared/components/navbar/navbar.component';
import { SidebarComponent, MenuItem } from '../../shared/components/sidebar/sidebar.component';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-client-layout',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, SidebarComponent],
  template: `
    <div class="flex h-screen">
      <app-sidebar
        [menuItems]="menu"
        appName="SRM-FM Portail"
        subtitle="Espace client"
        activeRoute="/client/dashboard"
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
export class ClientLayoutComponent {
  menu: MenuItem[] = [
    { label: 'Accueil', route: '/client/dashboard', icon: 'home' },
    { label: 'Mes réclamations', route: '/client/claims', icon: 'report_problem' },
    { label: 'Nouvelle réclamation', route: '/client/claims/new', icon: 'add_circle' },
    { label:'Ma consommation', route: '/client/consommation', icon: 'bar_chart' },
  ];

  constructor(private authService: AuthService) {}

  onLogout() {
    this.authService.logout();
  }
}