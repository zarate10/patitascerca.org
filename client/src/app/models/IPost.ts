import { ICategory } from "./ICategory";
import { IUser } from "./IUser";

export interface IPost { 
    id: number; 
    categoria: ICategory;
    usuario: IUser;
    descripcion: string; 
    fecha: Date;
    ubicacion: string; 
    imagen: string; 
}