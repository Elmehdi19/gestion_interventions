import { Role } from './role.models';
import { Specialite } from './specialite.models';

export interface UserRequest {
  nom: string;
  prenom: string;
  email: string;
  motDePasse: string;
  telephone?: string;
  adresse?: string;
  role: Role;
  specialite?: Specialite;   // pour ordonnanceur
  numeroContrat?: string;    // pour client
}

export interface UserResponse {
  id: number;
  nom: string;
  prenom: string;
  email: string;
  telephone: string;
  adresse: string;
  actif: boolean;
  role: Role;
  specialite: Specialite;
  numeroContrat: string;
  dateInscription: string;
}

export interface UserUpdateRequest {
  nom?: string;
  prenom?: string;
  telephone?: string;
  adresse?: string;
  actif?: boolean;
}

export interface UserCreateRequest {
  nom: string;
  prenom: string;
  email: string;
  password: string;
  telephone?: string;
  adresse?: string;
  role: Role;
  specialite?: Specialite;
  numeroContrat?: string;
}