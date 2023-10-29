import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ICategory } from 'src/app/core/interfaces/ICategory';
import { IUser } from 'src/app/core/interfaces/IUser';
import { CategoryService } from '../services/category/category.service';
import { PostService } from '../services/posts/post.service';
import { ToastrService } from 'ngx-toastr';
import { IPost } from 'src/app/core/interfaces/IPost';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent {
    user!: IUser;
    categories: ICategory[] = [];
    posteos: IPost[] = []; 

    constructor(
        private router: Router,
        private categoryService: CategoryService,
        private postService: PostService,
        private toastr: ToastrService
        ) {}

    ngOnInit() {
        if (sessionStorage.getItem('session') == null) {
            this.router.navigate([''])
            return; 
        }

        this.user = JSON.parse(sessionStorage.getItem('session')!); 
        this.setCategories(); 
        this.getPosteos();
    }

    logout() {
        sessionStorage.clear(); 
        this.router.navigate(['']);
    }

    setCategories() {
        this.categoryService.getCategories().subscribe(data => {
            this.categories = data; 
        })
    }

    getPosteos() {
        this.postService.getAll().subscribe(data => {
            this.posteos = data; 
        })
    }

    onSubmitCreatePost(form: any) {
        this.postService.createPost({
            categoria: {
                id: form.categoria
            }, 
            descripcion: form.descripcion, 
            ubicacion: form.ubicacion,
            imagen: form.imagen,
            usuario: {
                id: this.user.id
            }
        }).subscribe(res => { 
            this.toastr.success('Publicación creada.');
            this.getPosteos();
        }, err => {
            console.log(err);
            
            this.toastr.error('Ocurrió un error al crear la publicación.');
        })
    }
}
