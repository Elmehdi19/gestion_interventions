export interface PermutationRequest {
  technicienId: number;
  equipeDestinationId: number;
  dateDebut: string; // ISO 8601
  dateFin: string;   // ISO 8601
  motif?: string;
  typePermutation: string; // IMMEDIATE, DIFFEREE, TEMPORAIRE
}

export interface PermutationResponse {
[x: string]: any;
  id: number;
  technicienId: number;
  technicienNom: string;
  equipeDestinationId: number;
  equipeDestinationNom: string;
  dateDebut: string;
  dateFin: string;
  motif: string;
  typePermutation: string;
  statut: string;
  dateDemande: string;
}