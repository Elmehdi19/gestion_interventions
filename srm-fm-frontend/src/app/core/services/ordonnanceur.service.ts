import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReclamationResponse, ReclamationQualificationRequest } from '../../shared/models/reclamation.models';
import { TypeArbreResponse } from '../../shared/models/type-arbre.models';
import { InterventionRequest, InterventionResponse } from '../../shared/models/intervention.models';

export interface ChefEquipeResponse {
  id: number;
  nom: string;
  prenom: string;
  email: string;
}

@Injectable({ providedIn: 'root' })
export class OrdonnanceurService {
  private api = '/api/ordonnanceur';

  constructor(private http: HttpClient) {}

  getReclamationsNouvelles(): Observable<ReclamationResponse[]> {
    return this.http.get<ReclamationResponse[]>(`${this.api}/reclamations`);
  }

  qualifierReclamation(id: number, request: ReclamationQualificationRequest): Observable<void> {
    return this.http.put<void>(`${this.api}/reclamations/${id}/qualifier`, request);
  }

  getArbreTypes(): Observable<TypeArbreResponse> {
    return this.http.get<TypeArbreResponse>(`${this.api}/types/arbre`);
  }

  creerIntervention(request: InterventionRequest): Observable<InterventionResponse> {
    return this.http.post<InterventionResponse>(`${this.api}/interventions`, request);
  }

  getReclamationsQualifiees(): Observable<ReclamationResponse[]> {
    return this.http.get<ReclamationResponse[]>(`${this.api}/reclamations/qualifiees`);
  }

  // Nouvelle méthode pour récupérer les chefs d'équipe
  getChefsEquipe(): Observable<ChefEquipeResponse[]> {
    return this.http.get<ChefEquipeResponse[]>(`${this.api}/chefs-equipe`);
  }
  getInterventions(): Observable<InterventionResponse[]> {
    return this.http.get<InterventionResponse[]>(`${this.api}/interventions`);
  }
}