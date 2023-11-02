import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ICategory } from 'src/app/models/ICategory';
import { IPost } from 'src/app/models/IPost';
import { IUser } from 'src/app/models/IUser';
import { LikeService } from 'src/app/services/like.service';
import { PostService } from 'src/app/services/post.service';

@Component({
    selector: 'home-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.css']
})

export class PostComponent {
    @Input() post!: IPost;
    @Input() user!: IUser;
    @Input() categories!: ICategory[];
    @Output() deletePostFunction: EventEmitter<number> = new EventEmitter();

    isModalOpen: boolean = false;
    isLiked: boolean = false;
    isOpenPostDetail: boolean = true; 

    postToUpdate = {
        id: 0,
        categoria: {
            id: 0,
            title: ''
        },
        descripcion: "",
        ubicacion: "",
        imagen: "",
        usuario: {
            id: 0
        }
    };

    constructor(
        private toastr: ToastrService,
        private likeService: LikeService,
        private postService: PostService
    ) { }


    ngOnInit() {
        this.setIsLikedForPost();
    }

    deletePost(id: number) {
        this.deletePostFunction.emit(id);
        this.toastr.success("Publicación eliminada correctamente.");
        this.postService.delete(id).subscribe(data => {
   
        });
    }

    editPost(post: any) {

        this.postToUpdate = {
            id: post.id,
            ubicacion: post.ubicacion,
            categoria: post.categoria,
            descripcion: post.descripcion,
            imagen: post.imagen,
            usuario: {
                id: post.usuario.id
            }
        };

        this.isModalOpen = true;
    }

    likePost(post: IPost, idUser: number) {
        this.likeService.like(post.id, idUser).subscribe(data => {
            console.log(data);
        })

        if (this.isLiked) {
            post.totalLikes--;
        } else {
            post.totalLikes++;
        }
        this.isLiked = !this.isLiked;
    }

    setIsLikedForPost() {
        this.likeService.getPostsLikedByUser(this.user.id).subscribe((data: number[]) => {
            data.forEach(id => {
                if (id === this.post.id) {
                    this.isLiked = true;
                }
            })
        })
    }

    onSubmitUpdate(form: NgForm) {
        const { categoria, descripcion, ubicacion, imagen } = form.value;

        let nuevo = {
            id: this.postToUpdate.id,
            categoria: {
                id: categoria.id,
                title: categoria.title
            },
            descripcion: descripcion,
            ubicacion: ubicacion,
            imagen: imagen,
        };
        
        this.post = { ...this.post, ...nuevo };

        this.postService.update(nuevo).subscribe(res => {
            this.toastr.success('Publicación actualizada.');
        }, err => {
            this.toastr.error('Ocurrió un error al actualizar la publicación.');
        });

        this.isModalOpen = false;
    }

    setModal(post: any) {
        this.isModalOpen = !this.isModalOpen;

        this.postToUpdate = {
            id: post.id,
            ubicacion: post.ubicacion,
            categoria: post.categoria,
            descripcion: post.descripcion,
            imagen: post.imagen,
            usuario: {
                id: post.usuario.id
            }
        };

    }

    openPostDetail(post: any) {
        this.isOpenPostDetail = true; 
    }
}
