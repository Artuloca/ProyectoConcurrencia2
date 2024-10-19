import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DatosService {
  private apiUrl = 'http://localhost:8080/api/datos';  // Ajusta la URL de acuerdo a tu backend

  constructor(private http: HttpClient) {}

  getDatos(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
