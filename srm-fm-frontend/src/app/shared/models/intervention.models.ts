export interface InterventionRequest {
  reclamationId: number;
  typeInterventionId: number;
  urgente: boolean;
}

export interface InterventionResponse {
  id: number;
  numero: string;
  urgente: boolean;
  statut: string;
  dateCreation: string;
  reclamationId: number;
  typeLibelle: string;
  reclamationNumero: string;   // nouveau champ
  chefEquipeId?: number;
  chefEquipeNom?: string;
}

export interface CloturerInterventionRequest {
  rapport: string;
  dureeEffectiveMinutes: number;
  materiauxUtilises?: string;
}

export interface EmpecherInterventionRequest {
  motif: string;
}

export interface AffectationRequest {
  technicienId: number;
}