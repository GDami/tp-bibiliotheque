import { Injectable } from '@angular/core';
import { AuthRequest } from '../model/auth-request';
import { Observable } from 'rxjs';
import { AuthResponse } from '../model/auth-response';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _token: string = sessionStorage.getItem('token') ?? "";

  public get token(): string {
    return this._token;
  }

  constructor(private http: HttpClient) { }

  public isLogged(): boolean {
    return this._token != "";
  }

  public auth(request: AuthRequest): Observable<void> {
    return new Observable<void>(observer => {
      return this.http.post<AuthResponse>('/auth', request).subscribe(resp => {
        this._token = resp.token;

        // Enregistrement du jeton dans la session Storage
        sessionStorage.setItem('token', resp.token);

        observer.next();
      });
    });
  }

  public disconnect() {
    this._token = "";
    sessionStorage.removeItem('token');
  }
}
