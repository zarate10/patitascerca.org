import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})

export class CategoryService {
    constructor(private http: HttpClient, private router: Router) { }

    get(): Observable<any> {
        return this.http.get("http://localhost:8080/category/all");
    }

}