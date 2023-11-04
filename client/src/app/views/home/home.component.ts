import { Component } from '@angular/core';
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
    posteosFiltro: IPost[] = [];
    isModalOpen: boolean = false;
    isOpenComments: boolean = false; 
    postComments!: IPost; 
    filtroActual: string = ''; 

    constructor(
        private router: Router,
        private categoryService: CategoryService,
        private postService: PostService,
        private toastr: ToastrService,
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

    setComments(event: any){
        // acá lo que hacemos es que si ya está abierto, lo cerramos y volvemos a abrir
        // para que se resetee la info. Por eso el código así. 
        if (this.isOpenComments) {
            this.isOpenComments = false; 
            setTimeout(() => {
                this.isOpenComments = true; 
            }, 100)
        } else {
            this.isOpenComments = true;
        }
        
        this.postComments = event;      
    }

    closeComments(event: any) {
        this.isOpenComments = false;
    }

    filtrarPosts(filtro: string) {
        this.posteosFiltro = this.posteos.filter(post => post.categoria.title.toUpperCase() === filtro.toUpperCase());        
        this.getPosteos();
    }

}


