import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FamilleResponse } from '../../shared/models/famille.models';

@Injectable({ providedIn: 'root' })
export class AdminFamilleService {
  private api = '/api/admin/familles';

  constructor(private http: HttpClient) {}

  getAll(): Observable<FamilleResponse[]> {
    return this.http.get<FamilleResponse[]>(this.api);
  }

  getById(id: number): Observable<FamilleResponse> {
    return this.http.get<FamilleResponse>(`${this.api}/${id}`);
  }

  create(famille: FamilleResponse): Observable<FamilleResponse> {
    return this.http.post<FamilleResponse>(this.api, famille);
  }

  update(id: number, famille: FamilleResponse): Observable<FamilleResponse> {
    return this.http.put<FamilleResponse>(`${this.api}/${id}`, famille);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}