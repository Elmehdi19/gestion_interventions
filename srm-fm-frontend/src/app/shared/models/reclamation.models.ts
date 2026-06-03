import { Specialite } from './specialite.models';

export interface ReclamationRequest {
  description: string;
  typeProbleme?: string;
  urgente: boolean;
  piecesJointes?: string;
  specialiteConcernee?: Specialite;
}

export interface ReclamationResponse {
  id: number;
  numero: string;
  description: string;
  urgente: boolean;
  statut: string;
  dateCreation: string; // ISO 8601
}

export interface ReclamationStatutResponse {
  numero: string;
  statut: string;
  description: string;
  dateCreation: string;
  interventionNumero?: string;
}

export interface ReclamationQualificationRequest {
  urgente: boolean;
}