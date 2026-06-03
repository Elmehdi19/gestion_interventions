import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EquipeResponse } from '../../shared/models/equipe.models';
import { PermutationResponse } from '../../shared/models/permutation.models';

@Injectable({ providedIn: 'root' })
export class AdminEquipeService {
  private api = '/api/admin';

  constructor(private http: HttpClient) {}

  getEquipes(): Observable<EquipeResponse[]> {
    return this.http.get<EquipeResponse[]>(`${this.api}/equipes`);
  }

  getPermutationsEnAttente(): Observable<PermutationResponse[]> {
    return this.http.get<PermutationResponse[]>(`${this.api}/permutations/en-attente`);
  }

  validerPermutation(id: number): Observable<void> {
    return this.http.put<void>(`${this.api}/permutations/${id}/valider`, null);
  }
}