import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { IPost } from '../interfaces/IPost';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class PostService {
  constructor(private http: HttpClient, private router: Router) {}

  create(post: IPost) {
    this.http.post("http://localhost:8080/post/create", post).subscribe(data => {
      console.log(data);
    }); 
  }

  get(): Observable<any> {
    return this.http.get("http://localhost:8080/post/all");
  }

  like(postID: number, usuarioID: number) {
    this.http.post("http://localhost:8080/likes/like", {postID, usuarioID}).subscribe(data => {
      console.log(data);
    }); 
  }

  getPostById(id: number): Observable<any> {
    return this.http.get("http://localhost:8080/post/" + id);
  }
}