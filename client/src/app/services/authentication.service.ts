import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {
  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string) {
    this.http.post('http://localhost:8080/user/login', {
      "username": username, 
      "password": password
    }).subscribe(
      (data) => {
        sessionStorage.setItem("session", JSON.stringify(data)); 
        this.router.navigate(['/home']);
      },
      (error) => {
        console.error('Error de inicio de sesión:', error);
      }
    );
  }

  logout() {
  }
}