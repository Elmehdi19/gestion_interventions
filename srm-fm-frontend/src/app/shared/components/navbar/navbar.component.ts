import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../core/services/auth.service';
import { NotificationService } from '../../../core/services/notification.service';
import { Router } from '@angular/router';
import { interval, Subscription } from 'rxjs';
import { startWith, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  template: `
    <header class="flex justify-between items-center h-16 px-6 w-full bg-surface border-b border-outline-variant shadow-sm">
      <div>
        <h2 class="text-title-lg font-bold text-primary">SRM-FM</h2>
      </div>
      <div class="flex items-center gap-4">
        <!-- Notifications -->
        <div class="relative" (click)="toggleNotifications()" style="cursor: pointer;">
          <button class="btn-icon relative">
            <span class="material-symbols-outlined text-primary">notifications</span>
            <span *ngIf="unreadCount > 0"
                  class="absolute -top-1 -right-1 bg-error text-on-error text-xs rounded-full w-5 h-5 flex items-center justify-center font-bold">
              {{ unreadCount > 99 ? '99+' : unreadCount }}
            </span>
          </button>
          <!-- Dropdown -->
          <div *ngIf="showNotifications"
               class="absolute right-0 mt-2 w-80 bg-surface-container-lowest border border-outline-variant rounded-xl shadow-xl z-50 max-h-80 overflow-y-auto">
            <div class="p-3 border-b border-outline-variant sticky top-0 bg-surface-container-lowest">
              <p class="font-label-md font-bold">Notifications</p>
            </div>
            <div class="divide-y divide-outline-variant">
              <div *ngFor="let notif of notifications"
                   class="p-3 hover:bg-surface-container-low cursor-pointer"
                   (click)="markAsRead(notif.id)">
                <p class="text-body-md" [class.font-bold]="!notif.lu">{{ notif.message }}</p>
                <p class="text-label-md text-on-surface-variant">{{ notif.dateEnvoi | date:'short' }}</p>
              </div>
              <div *ngIf="notifications.length === 0" class="p-3 text-center text-on-surface-variant">
                Aucune notification
              </div>
            </div>
          </div>
        </div>

        <!-- Utilisateur -->
        <div class="text-right">
          <p class="text-body-md font-semibold text-on-surface">{{ userDisplayName }}</p>
          <p class="text-label-md text-on-surface-variant">{{ roleLabel }}</p>
        </div>

        <!-- Déconnexion -->
        <button (click)="logout()"
                class="btn-primary">
          Déconnexion
        </button>
      </div>
    </header>
  `
})
export class NavbarComponent implements OnInit, OnDestroy {
  userDisplayName = '';
  roleLabel = '';
  notifications: any[] = [];
  unreadCount = 0;
  showNotifications = false;
  private pollSubscription?: Subscription;

  constructor(
    private authService: AuthService,
    private notificationService: NotificationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Informations utilisateur
    const email = this.authService.getEmail();
    const role = this.authService.getRole();
    this.roleLabel = this.formatRole(role);
    this.userDisplayName = email || 'Utilisateur';

    // Charger les notifications
    this.loadNotifications();

    // Rafraîchir le compteur toutes les 30 secondes
    setInterval(() => {
      this.notificationService.getUnreadCount().subscribe(count => this.unreadCount = count);
    }, 30000);
  }

  loadNotifications(): void {
    this.notificationService.getMyNotifications().subscribe(data => this.notifications = data.slice(0, 10));
  }

  toggleNotifications(): void {
    this.showNotifications = !this.showNotifications;
    if (this.showNotifications) {
      this.loadNotifications();
    }
  }

  markAsRead(id: number): void {
    this.notificationService.markAsRead(id).subscribe(() => {
      // Met à jour localement
      this.notifications = this.notifications.map(n => n.id === id ? { ...n, lu: true } : n);
      this.unreadCount = Math.max(0, this.unreadCount - 1);
    });
  }

  ngOnDestroy(): void {
    this.pollSubscription?.unsubscribe();
  }

  private formatRole(role: string | null): string {
    if (!role) return '';
    const mapping: Record<string, string> = {
      ADMIN: 'Administrateur',
      ORDONNANCEUR_ELECTRICITE: 'Ordonnanceur Électricité',
      ORDONNANCEUR_EAU_ASSAINISSEMENT: 'Ordonnanceur Eau & Assainissement',
      CHEF_EQUIPE: "Chef d'équipe",
      TECHNICIEN: 'Technicien',
      CLIENT: 'Client'
    };
    return mapping[role] || role;
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);   // Redirection vers la page de connexion
  }
}