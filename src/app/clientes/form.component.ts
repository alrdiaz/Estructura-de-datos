import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
})
export class FormComponent implements OnInit {
  public tittle: string = 'Registro de clientes';
  public cliente: Cliente = new Cliente();
  public isExist: boolean = false;

  constructor(
    private clienteService: ClienteService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.loadCliente();
  }

  loadCliente(): void {
    this.activatedRoute.params.subscribe((params) => {
      let id = params['id'];
      if (id) {
        this.clienteService
          .getCliente(id)
          .subscribe((cliente) => (this.cliente = cliente));
        this.isExist = true;
      }
    });
  }

  create(): void {
    console.log(this.cliente);
    this.cliente.cus_feregistro = new Date();
    this.clienteService.create(this.cliente).subscribe((response) => {
      this.router.navigate(['/clientes']);
      swal.fire(
        'Nuevo Cliente',
        `Cliente ${this.cliente.cus_dsnombres} creado con éxito`,
        'success'
      );
    });
  }

  update(): void {
    console.log(this.cliente);
    this.clienteService.create(this.cliente).subscribe((cliente) => {
      this.router.navigate(['/clientes']);
      swal.fire(
        'Cliente Actualizado',
        `Cliente ${this.cliente.cus_dsnombres} actualizado con éxito`,
        'success'
      );
    });
  }
}
