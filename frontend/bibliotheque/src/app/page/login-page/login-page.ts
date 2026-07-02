import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth-service';

@Component({
  selector: 'app-login-page',
  imports: [ ReactiveFormsModule ],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage implements OnInit {
  protected formAuth!: FormGroup;
  protected formCtrlUsername!: FormControl;
  protected formCtrlPassword!: FormControl;

  constructor(private authService: AuthService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.formCtrlUsername = this.formBuilder.control("", Validators.required);
    this.formCtrlPassword = this.formBuilder.control("", [ Validators.required, Validators.minLength(6) ]);

    this.formAuth = this.formBuilder.group({
      login: this.formCtrlUsername,
      password: this.formCtrlPassword
    });
  }

  public auth() {
    this.authService.auth(this.formAuth.getRawValue()).subscribe(() => {
      this.router.navigate([ 'collection' ]);
    });
  }
}
