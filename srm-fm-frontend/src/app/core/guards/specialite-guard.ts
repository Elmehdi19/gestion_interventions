// core/guards/specialite.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({ providedIn: 'root' })
export class SpecialiteGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const userSpecialite = (this.auth as any).getSpecialite();   // ex: 'EAU', 'ELECTRICITE'
    
    // On peut définir des routes avec data.specialite pour restreindre
    const routeSpecialite = route.data['specialite'] as string | undefined;
    
    if (!routeSpecialite) {
      // Si pas de restriction, on autorise (l'ordonnanceur voit ses propres données)
      return true;
    }
    
    if (userSpecialite === routeSpecialite) {
      return true;
    }
    
    // Sinon redirection vers son tableau de bord
    this.router.navigate(['/ordonnanceur/dashboard']);
    return false;
  }
}