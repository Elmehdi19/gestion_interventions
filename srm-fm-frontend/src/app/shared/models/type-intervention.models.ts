export interface TypeInterventionResponse {
  id: number;
  libelle: string;
  dureeEstimeeMinutes: number;
  familleId: number;
  familleLibelle: string;
  specialiteId: number;
  specialiteLibelle: string;   // ← ajout
}