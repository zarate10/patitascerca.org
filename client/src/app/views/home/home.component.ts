import { Component } from '@angular/core';
import { ICategory } from 'src/app/interfaces/ICategory';
import { IPost } from 'src/app/interfaces/IPost';
import { CategoryService } from 'src/app/services/category.service';
import { PostService } from 'src/app/services/post.service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent {
    categorias!: ICategory[]; 

    constructor(private categoryService: CategoryService, private postService: PostService) { }

    ngOnInit() {
        this.categoryService.get().subscribe(data => (
            this.categorias = data
        ));
    }

    getIdCategoria(titulo: string) {
        const categoriaEncontrada = this.categorias.find((el: any) => el.title === titulo);
        if (categoriaEncontrada) {
            return categoriaEncontrada.id;
        }
        return null;
    }

    handlePost(e: any) {
        e.preventDefault(); 

        const usuario = JSON.parse(sessionStorage.getItem("session")!)
        const categoriaID = this.getIdCategoria((<HTMLInputElement>document.getElementById("categorias")).value); 
        
        const post: IPost = {
            categoria: {
                id: categoriaID!
            }, 
            descripcion: (<HTMLInputElement>document.getElementById("descripcion")).value, 
            ubicacion: (<HTMLInputElement>document.getElementById("ubicacion")).value,
            imagen: (<HTMLInputElement>document.getElementById("imagen")).value,
            usuario: {
                id: usuario.id
            }
        }
        
        if (usuario && categoriaID) {
            this.postService.create(post); 
        }
    }
}
