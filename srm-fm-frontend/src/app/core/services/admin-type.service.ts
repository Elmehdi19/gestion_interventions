import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TypeInterventionResponse } from '../../shared/models/type-intervention.models';

@Injectable({ providedIn: 'root' })
export class AdminTypeService {
  private api = '/api/admin/types';

  constructor(private http: HttpClient) {}

  getAll(): Observable<TypeInterventionResponse[]> {
    return this.http.get<TypeInterventionResponse[]>(this.api);
  }

  getById(id: number): Observable<TypeInterventionResponse> {
    return this.http.get<TypeInterventionResponse>(`${this.api}/${id}`);
  }

  create(type: TypeInterventionResponse): Observable<TypeInterventionResponse> {
    return this.http.post<TypeInterventionResponse>(this.api, type);
  }

  update(id: number, type: TypeInterventionResponse): Observable<TypeInterventionResponse> {
    return this.http.put<TypeInterventionResponse>(`${this.api}/${id}`, type);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}