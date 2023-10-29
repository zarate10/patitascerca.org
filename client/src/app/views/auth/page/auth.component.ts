import { Component } from '@angular/core';
import { IUserRegister } from 'src/app/core/interfaces/IUserRegister';
import { AuthService } from '../services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { IUserLogin } from 'src/app/core/interfaces/IUserLogin';

@Component({
    selector: 'app-auth',
    templateUrl: './auth.component.html',
    styleUrls: ['./auth.component.css']
})

export class AuthComponent {
    isLoginScreen: boolean = true;

    constructor(
        private authService: AuthService, 
        private toastr: ToastrService,
        private router: Router
    ) { }

    changeScreen() {
        this.isLoginScreen = !this.isLoginScreen;
    }

    onSubmitRegister(datos: IUserRegister) {
        this.authService.register(datos).subscribe(response => {
            if (response) {
                this.toastr.success("Usuario creado correctamente.")
                this.isLoginScreen = true; 
            } 
        }, err => {
            this.toastr.error("Ocurrió un error en el registro.");
        })
    }
    
    onSubmitLogin(datos: IUserLogin) {
        this.authService.login(datos).subscribe(response => {
            if (response) {
                sessionStorage.setItem("session", JSON.stringify(response)); 
                this.router.navigate(['home']);
            } 
        }, err => {
            this.toastr.error("Ocurrió un error en el registro.");
        })
    }
}
