import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent implements OnInit, OnDestroy{

  errorMessage! : string;
  AuthUserSub! : Subscription;

  constructor(private authService : AuthService, private router : Router) {
  }

  ngOnInit() {
    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next : user => {
        if(user) {
          this.router.navigate(['home']);
        }
      }
    })
  }

  onSubmitRegister(formRegister: NgForm) {
    if(!formRegister.valid){
      return;
    }
    const email = formRegister.value.email;
    const password = formRegister.value.password;
    const firstname = formRegister.value.firstname;
    const lastname = formRegister.value.lastname;
    const role = formRegister.value.role;

    this.authService.register(firstname, lastname, email, password, role).subscribe({
      next : userData => {
        this.router.navigate(['home']);
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

