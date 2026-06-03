import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReclamationRequest, ReclamationResponse, ReclamationStatutResponse } from '../../shared/models/reclamation.models';

@Injectable({ providedIn: 'root' })
export class ReclamationService {
  private api = '/api/reclamations';

  constructor(private http: HttpClient) {}

  creerReclamation(request: ReclamationRequest): Observable<ReclamationResponse> {
    return this.http.post<ReclamationResponse>(this.api, request);
  }

  getStatut(numero: string): Observable<ReclamationStatutResponse> {
    return this.http.get<ReclamationStatutResponse>(`${this.api}/${numero}/statut`);
  }

  getMesReclamations(): Observable<ReclamationResponse[]> {
    return this.http.get<ReclamationResponse[]>(`${this.api}/client`);
  }

  uploadPieceJointe(id: number, file: File): Observable<string> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post(`${this.api}/${id}/upload`, formData, { responseType: 'text' });
  }
}