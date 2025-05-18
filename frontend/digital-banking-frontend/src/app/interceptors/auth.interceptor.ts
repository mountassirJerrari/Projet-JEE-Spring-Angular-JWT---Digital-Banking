import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);

  // Skip interceptor for login request
  if (req.url.includes('/login')) {
    return next(req);
  }

  // Get token from auth service
  const token = authService.getToken();

  // If token exists, add it to the request header
  if (token) {
    console.log('Adding token to request:', req.url);
    const authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(authReq);
  }

  // If no token, proceed with original request
  console.log('No token available for request:', req.url);
  return next(req);
};
