import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente';
import { ClienteService } from '../cliente.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'detail-cliente',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css'],
})
export class DetailComponent implements OnInit {
  clientes: Cliente[];
  cliente: Cliente;
  public tittle: string = 'Detalle Cliente';
  constructor(
    private clienteService: ClienteService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.clienteService.getClientes().subscribe((clientes) => {
      this.clientes = clientes;
    });

    this.activatedRoute.paramMap.subscribe((params) => {
      let id: number = +params.get('id');
      if (id) {
        this.clienteService.getCliente(id).subscribe((cliente) => {
          this.cliente = cliente;
        });
      }
    });
  }

  delete(cliente: Cliente): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger',
      },
      buttonsStyling: false,
    });

    swalWithBootstrapButtons
      .fire({
        title: 'Está seguro?',
        text: `Eliminaras al cliente ${cliente.cus_dsnombres}`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'SI',
        cancelButtonText: 'No, cancel!',
        reverseButtons: true,
      })
      .then((result) => {
        if (result.value) {
          this.clienteService.delete(cliente.nmid).subscribe((response) => {
            this.router.navigate(['/clientes']);
            this.clientes = this.clientes.filter((cli) => cli !== cliente);

            swalWithBootstrapButtons.fire(
              'Borrado!',
              `El cliente ${cliente.cus_dsnombres} ha sido borrado.`,
              'success'
            );
          });
        }
      });
  }
}
