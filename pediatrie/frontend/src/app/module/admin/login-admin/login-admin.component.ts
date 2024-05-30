import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {Router} from '@angular/router';
import {Message} from 'primeng/api';
import {AuthService} from 'src/app/zynerator/security/shared/service/Auth.service';
import {LayoutService} from 'src/app/layout/service/app.layout.service';
import {AppLayoutComponent} from "../../../layout/app.layout.component";


@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.css']
})
export class LoginAdminComponent implements OnInit {
  loginForm = new FormGroup({
    username: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required)
  });

  messages: Message[] = [];
    hide = true;
    loading = false;
  constructor(private authService: AuthService,public layoutService: LayoutService, private router: Router) { }

  ngOnInit(): void {
    this.messages = [];
  }

  submit(){
    const formValues = this.loginForm.value;
    const username = formValues.username;
    const passowrd = formValues.password;
    this.authService.login(username, passowrd);

  }
    register(){
    this.router.navigate(['/admin/register']);
  }

    adminSet() {
        this.loginForm.get('username')?.setValue('anas_');
        this.loginForm.get('password')?.setValue('123');
    }
    medecinSet() {
        this.loginForm.get('username')?.setValue('adil_');
        this.loginForm.get('password')?.setValue('123');
    }
    infermierSet() {
        this.loginForm.get('username')?.setValue('amal_');
        this.loginForm.get('password')?.setValue('123');
    }


}
