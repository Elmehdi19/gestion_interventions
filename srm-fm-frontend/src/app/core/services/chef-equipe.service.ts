import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InterventionResponse } from '../../shared/models/intervention.models';
import { ChefEquipeDTO } from '../../shared/models/equipe.models';

export interface EquipeResponse {
  id: number;
  nom: string;
  creneauHoraire: string;
  chefId: number;
  chefNom: string;
  specialite: string;
  nbTechniciens: number;
  techniciens: { id: number; nom: string; prenom: string }[];
}

export interface EquipeRequest {
  nom: string;
  creneauHoraire: string;
  chefId: number | null;
  specialite: string;
}

export interface TechnicienDisponibleResponse {
  id: number;
  nom: string;
  prenom: string;
  email: string;
  equipeNom: string;
  interventionsEnCours: number;
}

export interface PerformanceTechnicienResponse {
  technicienId: number;
  nomComplet: string;
  nbInterventions: number;
  dureeMoyenneMinutes: number;
  nbCloturees: number;
  nbEmpechements: number;
  tauxCloture: number;
}

export interface PerformanceEquipeResponse {
  equipeId: number;
  nomEquipe: string;
  chefNom: string;
  nbTechniciens: number;
  nbInterventionsTotal: number;
  nbCloturees: number;
  nbEnCours: number;
  dureeMoyenneEquipe: number;
  techniciens: any[];
}

@Injectable({ providedIn: 'root' })
export class ChefEquipeService {
  private api = '/api/chef-equipe';

  constructor(private http: HttpClient) {}

  // Interventions
  getInterventionsEnAttente(page: number, size: number): Observable<Page<InterventionResponse>> {
    return this.http.get<Page<InterventionResponse>>(`${this.api}/interventions/en-attente?page=${page}&size=${size}`);
  }
  getInterventionsEnAttenteCount(): Observable<number> {
    return this.http.get<number>(`${this.api}/interventions/en-attente/count`);
  }
  affecterTechnicien(interventionId: number, technicienId: number): Observable<void> {
    return this.http.put<void>(`${this.api}/interventions/${interventionId}/affecter`, { technicienId });
  }

  // Techniciens
  getAllTechniciens(): Observable<any[]> {
    return this.http.get<any[]>(`${this.api}/techniciens`);
  }
  getTechniciensDisponibles(): Observable<TechnicienDisponibleResponse[]> {
    return this.http.get<TechnicienDisponibleResponse[]>(`${this.api}/techniciens/disponibles`);
  }

  // Performances
  getPerformancesTechniciens(): Observable<PerformanceTechnicienResponse[]> {
    return this.http.get<PerformanceTechnicienResponse[]>(`${this.api}/performances/techniciens`);
  }
  getPerformancesEquipes(): Observable<PerformanceEquipeResponse[]> {
    return this.http.get<PerformanceEquipeResponse[]>(`${this.api}/performances/equipes`);
  }

  // Équipes
  getAllEquipes(): Observable<EquipeResponse[]> {
    return this.http.get<EquipeResponse[]>(`${this.api}/equipes`);
  }
  getEquipeById(id: number): Observable<EquipeResponse> {
    return this.http.get<EquipeResponse>(`${this.api}/equipes/${id}`);
  }
  createEquipe(data: EquipeRequest): Observable<EquipeResponse> {
    return this.http.post<EquipeResponse>(`${this.api}/equipes`, data);
  }
  updateEquipe(id: number, data: EquipeRequest): Observable<EquipeResponse> {
    return this.http.put<EquipeResponse>(`${this.api}/equipes/${id}`, data);
  }
  deleteEquipe(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/equipes/${id}`);
  }
  demanderPermutation(data: any): Observable<any> {
  return this.http.post<any>(`${this.api}/permutations`, data);
 }
 getAllChefs(): Observable<ChefEquipeDTO[]> {
  return this.http.get<ChefEquipeDTO[]>(`${this.api}/chefs`);
 }
}

interface Page<T> {
  content: T[];
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
}