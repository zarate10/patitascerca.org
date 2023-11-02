import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class CommentsService {

    private url: string = 'http://localhost:8080'; 

    constructor(private http: HttpClient) {}

    getCommentsByPost(id: number): Observable<any> {
        return this.http.get(this.url + '/comments/' + id); 
    }
}
