import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DashboardKPIsResponse } from '../../shared/models/dashboard.models';

@Injectable({ providedIn: 'root' })
export class AdminDashboardService {
  private api = '/api/admin/dashboard';

  constructor(private http: HttpClient) {}

  getKPIs(): Observable<DashboardKPIsResponse> {
    return this.http.get<DashboardKPIsResponse>(`${this.api}/kpis`);
  }
}