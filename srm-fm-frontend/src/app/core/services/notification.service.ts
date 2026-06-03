import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class NotificationService {
  private api = '/api/notifications';

  constructor(private http: HttpClient) {}

  getMyNotifications(): Observable<any[]> {
    return this.http.get<any[]>(this.api).pipe(
      catchError(() => of([]))       // retourne un tableau vide si erreur
    );
  }

  getUnreadCount(): Observable<number> {
    return this.http.get<{ count: number }>(`${this.api}/unread-count`).pipe(
      map(res => res?.count ?? 0),
      catchError(() => of(0))        // retourne 0 si erreur
    );
  }

  markAsRead(id: number): Observable<void> {
    return this.http.put<void>(`${this.api}/${id}/read`, null).pipe(
      catchError(() => of(undefined))
    );
  }
}