export interface DashboardKPIsResponse {
  totalReclamations: number;
  reclamationsOuvertes: number;
  reclamationsCloturees: number;
  totalInterventions: number;
  interventionsEnAttente: number;
  interventionsEnCours: number;
  interventionParMois: { [key: string]: number };
  interventionsCloturees: number;
  delaiMoyenPriseEnChargeHeures: number;
  tauxResolutionSLA: number;
  reclamationsParSpecialite: { [key: string]: number };
  interventionsParType: { [key: string]: number };
  reclamationsParEquipe: { [key: string]: number };
  reclamationsParTechnicien: { [key: string]: number };
  reclamationsParJour: { [key: string]: number };
  reclamationsParMois: { [key: string]: number };
  reclamationsParAnnee: { [key: string]: number };
  reclamationsParStatut: { [key: string]: number };
}