import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserResponse, UserCreateRequest, UserUpdateRequest } from '../../shared/models/user.model';

@Injectable({ providedIn: 'root' })
export class AdminUserService {
  private api = '/api/admin/users';

  constructor(private http: HttpClient) {}

  getAll(): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(this.api);
  }

  getById(id: number): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.api}/${id}`);
  }

  create(user: UserCreateRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(this.api, user);
  }

  update(id: number, user: UserUpdateRequest): Observable<UserResponse> {
    return this.http.put<UserResponse>(`${this.api}/${id}`, user);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}