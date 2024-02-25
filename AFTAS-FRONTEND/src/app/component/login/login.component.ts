import {Component, OnDestroy, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})

export class LoginComponent implements OnInit,OnDestroy{

  errorMessage! : string;
  AuthUserSub! : Subscription;

  constructor(private authService : AuthService, private router : Router) {
  }

  ngOnInit() {
    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next : user => {
        if(user) {
          if (user.role.name == "ROLE_MANAGER") {
            this.router.navigate(['competition']);
          } else if (user.role.name == "ROLE_JURY") {
            this.router.navigate(['jury-dashboard']);
          } else {
            this.router.navigate(['member-dashboard']);
          }
        } else {
          console.log("user is null")
        }
      }
    })
  }

  onSubmitLogin(formLogin: NgForm) {
    if(!formLogin.valid){
      return;
    }
    const email = formLogin.value.email;
    const password = formLogin.value.password;

    this.authService.login(email, password).subscribe({
      next: userData => {
        let size: number = userData.roles.length;
        console.log(userData)
        const roleName = userData?.roles[size - 1];
        if (roleName) {
          if (roleName === 'ROLE_MEMBER') {
            this.router.navigate(['member-dashboard']);
          } else if (roleName === 'ROLE_MANAGER') {
            this.router.navigate(['competition']);
          } else {
            this.router.navigate(['jury-dashboard']);
          }
        } else {
          this.router.navigate(['lock']);
        }
      },
      error : err => {
        this.errorMessage = err;
        console.log(err);
      }

    })
  }
  ngOnDestroy() {
    this.AuthUserSub.unsubscribe();
  }

  protected readonly console = console;
}
