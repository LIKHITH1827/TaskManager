import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { StorageService } from './services/storage.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const storageService = inject(StorageService);
  const router= inject(Router);

  const token=storageService.getToken();
   if(token){
    req=req.clone({
             setHeaders : {
            Authorization : `Bearer ${token}`,
          },
    });

   }



  return next(req)
};
