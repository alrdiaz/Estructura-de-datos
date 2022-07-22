import { Injectable } from '@angular/core';
import { Cliente } from './cliente';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError, throwError } from 'rxjs';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class ClienteService {
  private urlEndPointGetAll: string = 'http://localhost:8080/clientes/tree';
  private urlEndPointSave: string = 'http://localhost:8080/clientes/save';
  private urlEndPointFind: string = 'http://localhost:8080/clientes/find';
  private urlEndPointUpdate: string = 'http://localhost:8080/clientes/update';
  private urlEndPointDelete: string = 'http://localhost:8080/clientes/delete';
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient, private router: Router) {}

  getClientes(): Observable<Cliente[]> {
    return this.http.get(this.urlEndPointGetAll).pipe(
      map((response) => {
        return response as Cliente[];
      })
    );
  }

  create(cliente: Cliente): Observable<Cliente> {
    return this.http
      .post<Cliente>(this.urlEndPointSave, cliente, {
        headers: this.httpHeaders,
      })
      .pipe(
        catchError((e) => {
          console.log(e.error.error);
          Swal.fire('Error en creación', e.error.error, 'error');
          return throwError(() => e);
        })
      );
  }

  getCliente(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.urlEndPointFind}/${id}`).pipe(
      catchError((e) => {
        this.router.navigate(['/clientes']);
        console.error(e.error.error);
        Swal.fire('Error en busqueda', e.error.error, 'error');
        return throwError(() => e);
      })
    );
  }

  update(cliente: Cliente): Observable<Cliente> {
    return this.http
      .put<Cliente>(`${this.urlEndPointUpdate}/${cliente.nmid}`, cliente, {
        headers: this.httpHeaders,
      })
      .pipe(
        catchError((e) => {
          console.log(e.error.error);
          Swal.fire('Error al actualizar', e.error.error, 'error');
          return throwError(() => e);
        })
      );
  }

  delete(id: number): Observable<Cliente> {
    return this.http
      .delete<Cliente>(`${this.urlEndPointDelete}/${id}`, {
        headers: this.httpHeaders,
      })
      .pipe(
        catchError((e) => {
          console.log(e.error.error);
          Swal.fire('Error al borrar', e.error.error, 'error');
          return throwError(() => e);
        })
      );
  }
}
