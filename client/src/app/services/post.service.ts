import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { IPost } from '../interfaces/IPost';

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
}