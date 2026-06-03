import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PermutationRequest, PermutationResponse } from '../../shared/models/permutation.models';

@Injectable({ providedIn: 'root' })
export class PermutationService {
  private api = '/api/permutations';

  constructor(private http: HttpClient) {}

  demanderPermutation(request: PermutationRequest): Observable<PermutationResponse> {
    return this.http.post<PermutationResponse>(this.api, request);
  }

  validerPermutation(id: number): Observable<PermutationResponse> {
    return this.http.put<PermutationResponse>(`${this.api}/${id}/valider`, null);
  }

  getAll(): Observable<PermutationResponse[]> {
    return this.http.get<PermutationResponse[]>(this.api);
  }
}