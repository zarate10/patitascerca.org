import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class LikeService {

    private url: string = 'http://localhost:8080';

    constructor(private http: HttpClient) { }

    like(postID: number, usuarioID: number): Observable<any> {
        return this.http.post(this.url + '/likes/like', { postID, usuarioID });
    }

    getPostsLikedByUser(usuarioID: number):  Observable<any> {
        return this.http.get(this.url + '/likes/posts/' + usuarioID); 
    }
}
