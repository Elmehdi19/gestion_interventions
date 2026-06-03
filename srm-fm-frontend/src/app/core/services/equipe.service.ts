import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EquipeResponse } from '../../shared/models/equipe.models';

@Injectable({ providedIn: 'root' })
export class EquipeService {
  private api = '/api/equipes';

  constructor(private http: HttpClient) {}

  getAll(): Observable<EquipeResponse[]> {
    return this.http.get<EquipeResponse[]>(this.api);
  }
}