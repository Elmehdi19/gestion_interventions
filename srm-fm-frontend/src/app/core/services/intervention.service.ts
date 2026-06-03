import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InterventionResponse } from '../../shared/models/intervention.models';

@Injectable({ providedIn: 'root' })
export class InterventionService {
  private api = '/api/admin/interventions';   // endpoint à créer dans le backend si absent

  constructor(private http: HttpClient) {}

  getAll(): Observable<InterventionResponse[]> {
    return this.http.get<InterventionResponse[]>(this.api);
  }
}