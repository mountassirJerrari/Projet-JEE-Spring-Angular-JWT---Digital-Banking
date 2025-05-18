import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private host = environment.backendHost;
  private readonly TOKEN_KEY = 'auth_token';

  constructor(private http: HttpClient) { }

  // Define interface for JWT payload
  interface TokenPayload {
  sub: string;
  scope: string;
  exp: number;
  iat: number;
  iss: string;
}

login(username: string, password: string): Observable < any > {
  return this.http.post<any>(`${this.host}/login`, { username, password })
    .pipe(
      tap(response => {
        if (response && response.accessToken) {
          this.saveToken(response.accessToken);
        }
      })
    );
}

logout(): void {
  localStorage.removeItem(this.TOKEN_KEY);
}

saveToken(token: string): void {
  localStorage.setItem(this.TOKEN_KEY, token);
}

getToken(): string | null {
  return localStorage.getItem(this.TOKEN_KEY);
}

isLoggedIn(): boolean {
  const token = this.getToken();
  if (!token) {
    return false;
  }

  try {
    const decodedToken = this.decodeToken();
    if (!decodedToken) {
      return false;
    }

    // Check if token is expired
    const currentTime = Math.floor(Date.now() / 1000);
    return decodedToken.exp > currentTime;
  } catch (error) {
    console.error('Error decoding token:', error);
    return false;
  }
}

decodeToken(): TokenPayload | null {
  const token = this.getToken();
  if (!token) {
    return null;
  }

  try {
    return jwtDecode<TokenPayload>(token);
  } catch (error) {
    console.error('Error decoding token:', error);
    return null;
  }
}

getUserRoles(): string[] {
  const decodedToken = this.decodeToken();
  if (!decodedToken || !decodedToken.scope) {
    return [];
  }

  return decodedToken.scope.split(' ');
}

hasRole(role: string): boolean {
  const roles = this.getUserRoles();
  return roles.includes(role);
}

isAdmin(): boolean {
  return this.hasRole('SCOPE_ADMIN');
}

isUser(): boolean {
  return this.hasRole('SCOPE_USER');
}

getUsername(): string | null {
  const decodedToken = this.decodeToken();
  return decodedToken ? decodedToken.sub : null;
}
}
