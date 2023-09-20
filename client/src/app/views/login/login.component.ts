import { Component } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  constructor(private authService: AuthenticationService) {}
  
  handleForm(e: any) {
    e.preventDefault(); 
    let user = (<HTMLInputElement>document.getElementById("usuario")).value;
    let password = (<HTMLInputElement>document.getElementById("contrasena")).value;
    this.authService.login(user, password); 
  }
}
