import { ICategory } from "./ICategory";
import { IUsuario } from "./IUsuario";

export interface IPost { 
    id?: number;
    categoria: ICategory;
    descripcion: string; 
    ubicacion: string; 
    imagen: string; 
    usuario: IUsuario; 
}