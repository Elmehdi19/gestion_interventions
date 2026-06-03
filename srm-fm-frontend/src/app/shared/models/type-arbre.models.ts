export interface TypeArbreResponse {
  id: number;
  libelle: string;
  familles: FamilleArbreResponse[];
}

export interface FamilleArbreResponse {
  id: number;
  libelle: string;
  types: TypeArbreItemResponse[];
}

export interface TypeArbreItemResponse {
  id: number;
  libelle: string;
  dureeEstimeeMinutes: number;
}