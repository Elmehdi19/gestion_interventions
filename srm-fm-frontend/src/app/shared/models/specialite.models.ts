export interface Specialite {
  id: number;
  code: string;   // EAU, ASSAINISSEMENT, ELECTRICITE
  label: string;
}

export interface SpecialiteRequest {
  libelle: string;
}
export interface SpecialiteResponse {
  id: number;
  libelle: string;
}