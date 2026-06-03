import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive, Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

export interface MenuItem {
  label: string;
  route: string;
  icon: string;
}

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  template: `
    <aside class="h-screen w-64 fixed left-0 top-0 bg-surface-container-low border-r border-outline-variant flex flex-col py-md gap-2 z-50 overflow-y-auto">
      <div class="px-6 py-4 mb-4">
        <h1 class="text-headline-md font-headline-md font-bold text-primary">{{ appName }}</h1>
        <p class="text-label-md text-on-surface-variant">{{ subtitle }}</p>
      </div>
      <nav class="flex-grow space-y-1">
        <a *ngFor="let item of menuItems"
           [routerLink]="item.route"
           routerLinkActive="bg-primary-container text-on-primary-container rounded-lg font-semibold"
           [routerLinkActiveOptions]="{ exact: item.route === activeRoute }"
           class="text-on-surface-variant hover:text-on-surface mx-2 px-4 py-3 flex items-center gap-3 hover:bg-surface-variant transition-all active:scale-[0.98]">
          <span class="material-symbols-outlined">{{ item.icon }}</span>
          <span class="font-label-md text-label-md font-medium">{{ item.label }}</span>
        </a>
      </nav>
      <div class="px-4 py-4 mt-auto border-t border-outline-variant">
        <button class="w-full bg-primary text-on-primary py-2 px-4 rounded-lg font-bold shadow-sm hover:opacity-90 transition-all"
                *ngIf="actionLabel"
                (click)="action.emit()">
          {{ actionLabel }}
        </button>
        <div class="mt-4 space-y-1">
          <a (click)="logout()" class="text-on-surface-variant hover:text-on-surface px-4 py-2 flex items-center gap-3 font-label-md text-label-md cursor-pointer">
            <span class="material-symbols-outlined">logout</span>
            Logout
          </a>
        </div>
      </div>
    </aside>
  `
})
export class SidebarComponent {
  @Input() appName = 'SRM-FM';
  @Input() subtitle = '';
  @Input() menuItems: MenuItem[] = [];
  @Input() actionLabel = '';
  @Input() activeRoute = '/';

  @Output() action = new EventEmitter<void>();

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}