import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SpecialiteResponse } from '../../shared/models/specialite.models';

@Injectable({ providedIn: 'root' })
export class PublicService {
  private api = '/api/public';

  constructor(private http: HttpClient) {}

  getSpecialites(): Observable<SpecialiteResponse[]> {
    return this.http.get<SpecialiteResponse[]>(`${this.api}/specialites`);
  }
}