import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InterventionResponse, CloturerInterventionRequest, EmpecherInterventionRequest } from '../../shared/models/intervention.models';

@Injectable({ providedIn: 'root' })
export class TechnicienService {
  private api = '/api/technicien';

  constructor(private http: HttpClient) {}

  getMesInterventions(statut?: string): Observable<InterventionResponse[]> {
    let params = {};
    if (statut) params = { statut };
    return this.http.get<InterventionResponse[]>(`${this.api}/interventions`, { params });
  }

  demarrerIntervention(id: number): Observable<void> {
    return this.http.put<void>(`${this.api}/interventions/${id}/demarrer`, null);
  }

  cloturerIntervention(id: number, request: CloturerInterventionRequest): Observable<void> {
    return this.http.put<void>(`${this.api}/interventions/${id}/cloturer`, request);
  }

  empecherIntervention(id: number, request: EmpecherInterventionRequest): Observable<void> {
    return this.http.put<void>(`${this.api}/interventions/${id}/empecher`, request);
  }
}