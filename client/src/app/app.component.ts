import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from './models/product.models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client';
  http = inject(HttpClient); 
  products: Product[] = []; 

  ngOnInit() {
    this.http.get<Product[]>("https://api.escuelajs.co/api/v1/products")
      .subscribe((data) => {
        this.products = data;
      })
  }
}
