import { Component, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { ICategory } from 'src/app/models/ICategory';
import { IUser } from 'src/app/models/IUser';
import { CategoryService } from '../../services/category.service';
import { PostService } from '../../services/post.service';
import { ToastrService } from 'ngx-toastr';
import { IPost } from 'src/app/models/IPost';
import { NgForm } from '@angular/forms';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent {
    user!: IUser;
    categories: ICategory[] = [];
    posteos: IPost[] = [];
    isModalOpen: boolean = false;

    constructor(
        private router: Router,
        private categoryService: CategoryService,
        private postService: PostService,
        private toastr: ToastrService,
        private cdr: ChangeDetectorRef
    ) { }

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

    deletePost(id: number) {
        this.postService.delete(id).subscribe(data => {
            this.posteos = this.posteos.filter(post => post.id !== id);
        });
    }

    getPosteos() {
        this.postService.getAll().subscribe(data => {
            this.posteos = data;
        })
    }

    onSubmitCreatePost(form: NgForm) {
        const { categoria, descripcion, ubicacion, imagen } = form.value;

        this.postService.createPost({
            categoria: {
                id: categoria
            },
            descripcion: descripcion,
            ubicacion: ubicacion,
            imagen: imagen,
            usuario: {
                id: this.user.id
            }
        }).subscribe(res => {
            this.toastr.success('Publicación creada.');
            this.getPosteos();
        }, err => {
            console.log(err);

            this.toastr.error('Ocurrió un error al crear la publicación.');
        });
        this.isModalOpen = false;
        form.reset();
    }

    setModalNewPost() {
        this.isModalOpen = !this.isModalOpen;
    }

    isCommentsOpen: { [postId: number]: boolean } = {};

    toggleComments(post: any) {
        // Cambia el estado de isCommentsOpen para el post correspondiente
        this.isCommentsOpen[post.id] = !this.isCommentsOpen[post.id];
    }
}
