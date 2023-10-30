import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IPost } from 'src/app/models/IPost';

@Injectable({
    providedIn: 'root'
})
export class PostService {
    private url: string = 'http://localhost:8080';

    constructor(private http: HttpClient) { }

    createPost(form: any): Observable<any> {
        return this.http.post(this.url + '/post/create', form);
    }

    getAll(): Observable<any> {
        return this.http.get<IPost[]>(this.url + '/post/all'); 
    }
}
