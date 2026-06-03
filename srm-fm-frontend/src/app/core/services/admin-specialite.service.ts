import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SpecialiteResponse } from '../../shared/models/specialite.models';

@Injectable({ providedIn: 'root' })
export class AdminSpecialiteService {
  private api = '/api/admin/specialites';

  constructor(private http: HttpClient) {}

  getAll(): Observable<SpecialiteResponse[]> {
    return this.http.get<SpecialiteResponse[]>(this.api);
  }

  getById(id: number): Observable<SpecialiteResponse> {
    return this.http.get<SpecialiteResponse>(`${this.api}/${id}`);
  }

  create(specialite: Partial<SpecialiteResponse>): Observable<SpecialiteResponse> {
    return this.http.post<SpecialiteResponse>(this.api, specialite);
  }

  update(id: number, specialite: Partial<SpecialiteResponse>): Observable<SpecialiteResponse> {
    return this.http.put<SpecialiteResponse>(`${this.api}/${id}`, specialite);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}