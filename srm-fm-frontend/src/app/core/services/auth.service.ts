import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';

export interface LoginResponse {
  token: string;
  id: number;
  email: string;
  role: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface ChangePasswordRequest {
  currentPassword: string;
  newPassword: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = '/api/auth'; // à adapter selon votre backend
  private tokenKey = 'token';
  private userKey = 'user';
  private isBrowser: boolean;
  
  // Subject pour notifier les changements d'état de connexion
  private authStatusSubject = new BehaviorSubject<boolean>(false);
  authStatus$ = this.authStatusSubject.asObservable();

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
    this.isBrowser = isPlatformBrowser(this.platformId);
    // Initialiser le statut de connexion (si token présent)
    if (this.isBrowser && this.getToken()) {
      this.authStatusSubject.next(true);
    }
  }

  // ========== GESTION DU TOKEN ==========
  getToken(): string | null {
    if (this.isBrowser) {
      return localStorage.getItem(this.tokenKey);
    }
    return null;
  }

  private setToken(token: string): void {
    if (this.isBrowser) {
      localStorage.setItem(this.tokenKey, token);
    }
  }

  private removeToken(): void {
    if (this.isBrowser) {
      localStorage.removeItem(this.tokenKey);
    }
  }

  // ========== GESTION DES INFOS UTILISATEUR ==========
  private setUser(user: { id: number; email: string; role: string }): void {
    if (this.isBrowser) {
      localStorage.setItem(this.userKey, JSON.stringify(user));
    }
  }

  private getUser(): { id: number; email: string; role: string } | null {
    if (this.isBrowser) {
      const userStr = localStorage.getItem(this.userKey);
      if (userStr) {
        try {
          return JSON.parse(userStr);
        } catch (e) {
          return null;
        }
      }
    }
    return null;
  }

  private removeUser(): void {
    if (this.isBrowser) {
      localStorage.removeItem(this.userKey);
    }
  }

  // ========== MÉTHODES PUBLIQUES ==========
  login(credentials: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, credentials).pipe(
      tap(response => {
        if (response && response.token) {
          this.setToken(response.token);
          this.setUser({
            id: response.id,
            email: response.email,
            role: response.role
          });
          this.authStatusSubject.next(true);
        }
      })
    );
  }

  logout(): void {
    this.removeToken();
    this.removeUser();
    this.authStatusSubject.next(false);
    // Redirection facultative : à gérer dans le composant ou via un router
  }

  isLoggedIn(): boolean {
    return this.isBrowser && !!this.getToken();
  }

  getRole(): string | null {
    const user = this.getUser();
    return user ? user.role : null;
  }

  getEmail(): string | null {
    const user = this.getUser();
    return user ? user.email : null;
  }

  getUserId(): number | null {
    const user = this.getUser();
    return user ? user.id : null;
  }

  changePassword(currentPassword: string, newPassword: string): Observable<any> {
    const userId = this.getUserId();
    if (!userId) throw new Error('Utilisateur non authentifié');
    return this.http.post(`${this.apiUrl}/change-password/${userId}`, {
      currentPassword,
      newPassword
    });
  }
}