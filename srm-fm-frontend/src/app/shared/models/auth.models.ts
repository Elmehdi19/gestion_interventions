export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  type: string; // "Bearer"
  id: number;
  email: string;
  role: string;
}

export interface RegisterRequest {
  nom: string;
  prenom: string;
  email: string;
  motDePasse: string;
  telephone?: string;
  adresse?: string;
  numeroContrat?: string;
}