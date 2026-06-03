export interface EquipeResponse {
  id: number;
  nom: string;
  creneauHoraire: string; // ex: "06h00 - 18h00"
  chefNom: string;
  chefId: number;
  techniciens: TechnicienSimpleResponse[];
}

export interface TechnicienSimpleResponse {
  id: number;
  nom: string;
  prenom: string;
}

export interface TechnicienDTO {
  id: number;
  nom: string;
  prenom: string;
  email: string;
  specialite: string;
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
export interface EquipeDTO {
  id: number;
  nom: string;
  creneauHoraire: string; // ex: "06h00 - 18h00"
  chefId: number;
  chefNom: string;
}
export interface ChefEquipeDTO {
  id: number;
  nom: string;
  prenom: string;
  email: string;
}