import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {
  isLoggedIn = false;
  userRole: string | null = null;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    if (this.isLoggedIn) {
      this.userRole = this.authService.getRole();
    }
  }

  navigateTo(path: string): void {
    this.router.navigate([path]);
  }

  // Redirige vers l'espace correspondant au rôle
  goToMySpace(): void {
    const role = this.userRole;
    if (role === 'ADMIN') this.router.navigate(['/admin/dashboard']);
    else if (role?.startsWith('ORDONNANCEUR')) this.router.navigate(['/ordonnanceur/dashboard']);
    else if (role === 'CHEF_EQUIPE') this.router.navigate(['/chef-equipe/dashboard']);
    else if (role === 'TECHNICIEN') this.router.navigate(['/technicien/dashboard']);
    else if (role === 'CLIENT') this.router.navigate(['/client/dashboard']);
    else this.router.navigate(['/login']);
  }

  stats = [
    { icon: 'groups', value: '4,5M', label: 'Habitants' },
    { icon: 'map', value: '9', label: 'Préf/Prov' },
    { icon: 'factory', value: '4 200', label: 'Grands Clients Elec' },
    { icon: 'bolt', value: '+1,4M', label: 'Foyers Elec' },
    { icon: 'water_full', value: '+1,0M', label: 'Foyers Eau' },
    { icon: 'sanitizer', value: '+874k', label: 'Foyers Assain.' },
    { icon: 'electrical_services', value: '43k km', label: 'Réseau Elec' },
    { icon: 'piping', value: '15,5k km', label: 'Réseau Eau' },
    { icon: 'waves', value: '5 192 km', label: 'Réseau Assain.' },
    { icon: 'water_damage', value: '696', label: 'Réservoirs' },
    { icon: 'bolt', value: '29', label: 'Postes Sources' },
    { icon: 'biotech', value: '13', label: 'STEP (Stations)' }
  ];

  paiementCards = [
    { title: 'EX-RADEEF', subtitle: 'Agence en ligne', icon: 'account_balance' },
    { title: 'EX-RADEM', subtitle: 'Agence en ligne', icon: 'account_balance' },
    { title: 'EX-RADEETA', subtitle: 'Agence en ligne', icon: 'account_balance' },
    { title: 'EX-ONEE électricité', subtitle: 'Espace de paiement', icon: 'bolt' },
    { title: 'EX-ONEE eau', subtitle: 'Espace de paiement', icon: 'water_drop' }
  ];

  piliers = [
    {
      icon: 'settings_input_component',
      title: 'Renforcement des réseaux',
      description: 'Modernisation et extension continue des infrastructures pour accompagner la croissance régionale.'
    },
    {
      icon: 'energy_savings_leaf',
      title: 'Optimisation des ressources',
      description: 'Gestion intelligente et durable de l\'eau et de l\'électricité pour préserver notre patrimoine naturel.'
    },
    {
      icon: 'terminal',
      title: 'Innovation & Digitalisation',
      description: 'Intégration des technologies de pointe pour simplifier le parcours client et améliorer la maintenance.'
    },
    {
      icon: 'gavel',
      title: 'Gouvernance Responsable',
      description: 'Transparence, intégrité et engagement social au cœur de notre modèle de gestion publique.'
    }
  ];
}