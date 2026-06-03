import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FactureResponse } from '../../shared/models/facture.models';

@Injectable({ providedIn: 'root' })
export class FactureService {
  private api = '/api/factures';

  constructor(private http: HttpClient) {}

  getMesFactures(): Observable<FactureResponse[]> {
    return this.http.get<FactureResponse[]>(`${this.api}/client`);
  }
}