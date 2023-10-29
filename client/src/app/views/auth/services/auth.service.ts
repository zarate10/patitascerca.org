import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IUserLogin } from 'src/app/core/interfaces/IUserLogin';
import { IUserRegister } from 'src/app/core/interfaces/IUserRegister';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private url: string = 'http://localhost:8080'; 

    constructor(private http: HttpClient) { }

    register(data: IUserRegister): Observable<any> {
        return this.http.post(this.url + '/user/register', data);
    }

    login(data: IUserLogin): Observable<any> {
        return this.http.post(this.url +'/user/login', data);
    }
}
