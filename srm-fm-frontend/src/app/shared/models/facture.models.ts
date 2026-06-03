export interface FactureResponse {
  id: number;
  numeroFacture: string;
  montantTTC: number; // BigDecimal serialisé en number
  dateEmission: string; // ISO 8601 date
  dateEcheance: string;
  statut: string;
  pdfUrl: string;
  periodeMois: number;
  periodeAnnee: number;
}