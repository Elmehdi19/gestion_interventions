import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { MenuItem } from '../../shared/components/sidebar/sidebar.component'; // ou définissez l'interface localement

@Component({
  selector: 'app-ordonnanceur-layout',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './ordonnanceur-layout.component.html',
  styleUrls: ['./ordonnanceur-layout.component.scss']
})
export class OrdonnanceurLayoutComponent implements OnInit {
  menuItems: MenuItem[] = [];
  specialite: string = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Récupérer la spécialité depuis le rôle de l'utilisateur
    const role = this.authService.getRole();
    if (role === 'ORDONNANCEUR_ELECTRICITE') {
      this.specialite = 'Électricité';
    } else if (role === 'ORDONNANCEUR_EAU_ASSAINISSEMENT') {
      this.specialite = 'Eau & Assainissement';
    } else {
      this.specialite = '';
    }

    // Définir les éléments du menu
    this.menuItems = [
      { label: 'Dashboard', route: '/ordonnanceur/dashboard', icon: 'dashboard' },
      { label: 'Réclamations', route: '/ordonnanceur/claims', icon: 'list_alt' },
      { label: 'Nouvelle intervention', route: '/ordonnanceur/claims/qualify', icon: 'add_circle' } ,
        { label: 'Interventions', route: '/ordonnanceur/interventions', icon: 'build' }
    ];
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}