import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../service/auth.service';
import {map, take} from "rxjs";

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const authService = inject(AuthService);

  return authService.AuthenticatedUser$.pipe(
    take(1),
    map(user => {
      console.log("Auth Guard")
      // check if route is restricted by role
      const { roles } = route.data;
      console.log(roles)
      console.log(user)
      if(user && user.role && roles.includes(user.role.name)) {
        return true;
      }
      if(user) {
        console.log("User doesnt have the role")
        return  router.createUrlTree(['/forbidden']);
      }
      return  router.createUrlTree(['/login']);
    })
  )
};
